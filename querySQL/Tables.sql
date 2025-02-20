-- Tabla que almacena las ubicaciones o locales
CREATE TABLE location (
    idLocation VARCHAR(50) PRIMARY KEY,
    address VARCHAR(100)
);

-- Tabla que almacena los usuarios y su rol asociado
CREATE TABLE user (
    email VARCHAR(50) PRIMARY KEY,
    password VARCHAR(50) NOT NULL,
    role ENUM('admin', 'user') NOT NULL DEFAULT 'user',
    idLocation VARCHAR(50) NULL,
    FOREIGN KEY (idLocation) REFERENCES location(idLocation)
);

-- Tabla que almacena los pacientes y sus datos personales
CREATE TABLE patient (
    DNI CHAR(8) PRIMARY KEY,
    firstName VARCHAR(50) NOT NULL,
    lastName VARCHAR(50) NOT NULL,
    age INT NOT NULL CHECK (age > 0),
    phoneNumber VARCHAR(9) NOT NULL CHECK (phoneNumber REGEXP '^[0-9]{9}$'),
    dateOfEntry TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    dateOfBirth DATE NOT NULL,
    district VARCHAR(50) NOT NULL,
    idLocation VARCHAR(50) NOT NULL,
    FOREIGN KEY (idLocation) REFERENCES location(idLocation)
);

-- Tabla que almacena los productos y su relación con cada local
CREATE TABLE product (
    idProduct VARCHAR(20),
    category VARCHAR(30) NOT NULL,
    productName VARCHAR(50) NOT NULL,
    stock INT NOT NULL CHECK (stock >= 0),
    price INT NOT NULL CHECK (price >= 0),
    idLocation VARCHAR(50),
    PRIMARY KEY (idProduct, idLocation),
    FOREIGN KEY (idLocation) REFERENCES location(idLocation)
);

-- Tabla que registra los incrementos de productos en el inventario
CREATE TABLE productsIncrease (
    idProductIncrease INT AUTO_INCREMENT PRIMARY KEY,
    idProduct VARCHAR(20) NOT NULL,
    dateOfEntry TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    quantity INT NOT NULL CHECK (quantity > 0),
    idLocation VARCHAR(50) NOT NULL,
    FOREIGN KEY (idProduct, idLocation) REFERENCES product(idProduct, idLocation)
);

-- Tabla que almacena las ventas realizadas
CREATE TABLE sale (
    idSale CHAR(36) DEFAULT (UUID()) PRIMARY KEY,
    DNI CHAR(8),
    diagnosis TEXT NOT NULL,
    category VARCHAR(30) NOT NULL,
    saleDate TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    idLocation VARCHAR(50) NOT NULL,
    FOREIGN KEY (DNI) REFERENCES patient(DNI),
    FOREIGN KEY (idLocation) REFERENCES location(idLocation)
);

-- Tabla para almacenar los detalles de los productos vendidos en cada venta
CREATE TABLE salesDetail (
    idSale VARCHAR(36) NOT NULL,
    idProduct VARCHAR(20) NOT NULL,
    idLocation VARCHAR(50) NOT NULL,
    quantity INT NOT NULL CHECK (quantity > 0),
    price INT NOT NULL CHECK (price >= 0),
    subtotal INT GENERATED ALWAYS AS (quantity * price) STORED,
    PRIMARY KEY (idSale, idProduct),
    FOREIGN KEY (idSale) REFERENCES sale(idSale),
    FOREIGN KEY (idProduct, idLocation) REFERENCES product(idProduct, idLocation)
);

-- Trigger para actualizar el stock al incrementar productos
DELIMITER $$
CREATE TRIGGER afterProductsIncrease
AFTER INSERT ON productsIncrease
FOR EACH ROW
BEGIN
    UPDATE product
    SET stock = stock + NEW.quantity
    WHERE idProduct = NEW.idProduct AND idLocation = NEW.idLocation;
END$$
DELIMITER ;

-- Trigger para actualizar el stock al registrar ventas
DELIMITER $$
CREATE TRIGGER afterSalesDetailsInsert
AFTER INSERT ON salesDetail
FOR EACH ROW
BEGIN
    DECLARE saleLocation VARCHAR(50);
    
    -- Obtener la ubicación desde la tabla sale
    SELECT idLocation INTO saleLocation
    FROM sale
    WHERE idSale = NEW.idSale;

    -- Actualizar el stock del producto en la ubicación correspondiente
    UPDATE product
    SET stock = stock - NEW.quantity
    WHERE idProduct = NEW.idProduct AND idLocation = saleLocation;
END$$
DELIMITER ;

-- Trigger para validar el stock antes de registrar ventas
DELIMITER $$
CREATE TRIGGER beforeSalesDetailsInsert
BEFORE INSERT ON salesDetail
FOR EACH ROW
BEGIN
    DECLARE currentStock INT;
    SELECT stock INTO currentStock
    FROM product
    WHERE idProduct = NEW.idProduct AND idLocation = (SELECT idLocation FROM sale WHERE idSale = NEW.idSale);

    IF currentStock < NEW.quantity THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Stock insuficiente para realizar la venta';
    END IF;
END$$
DELIMITER ;

-- Trigger para calcular la edad al insertar un paciente
DELIMITER $$
CREATE TRIGGER beforeInsertPatient
BEFORE INSERT ON patient
FOR EACH ROW
BEGIN
    SET NEW.age = TIMESTAMPDIFF(YEAR, NEW.dateOfBirth, CURDATE());
END$$
DELIMITER ;

DELIMITER $$
CREATE TRIGGER beforeUpdatePatient
BEFORE UPDATE ON patient
FOR EACH ROW
BEGIN
    -- Verifica si la fecha de nacimiento ha sido modificada
    IF NEW.dateOfBirth <> OLD.dateOfBirth THEN
        SET NEW.age = TIMESTAMPDIFF(YEAR, NEW.dateOfBirth, CURDATE());
    END IF;
END$$
DELIMITER ;

DELIMITER $$
CREATE TRIGGER afterUpdateLocation
AFTER UPDATE ON location
FOR EACH ROW
BEGIN
    -- Actualizar en la tabla user
    UPDATE user
    SET idLocation = NEW.idLocation
    WHERE idLocation = OLD.idLocation;

    -- Actualizar en la tabla patient
    UPDATE patient
    SET idLocation = NEW.idLocation
    WHERE idLocation = OLD.idLocation;

    -- Actualizar en la tabla product
    UPDATE product
    SET idLocation = NEW.idLocation
    WHERE idLocation = OLD.idLocation;

    -- Actualizar en la tabla productsIncrease
    UPDATE productsIncrease
    SET idLocation = NEW.idLocation
    WHERE idLocation = OLD.idLocation;

    -- Actualizar en la tabla sale
    UPDATE sale
    SET idLocation = NEW.idLocation
    WHERE idLocation = OLD.idLocation;

    -- Actualizar en la tabla salesDetail
    UPDATE salesDetail
    SET idLocation = NEW.idLocation
    WHERE idLocation = OLD.idLocation;

END$$
DELIMITER ;

-- Habilitar el programador de eventos si no está activado
SET GLOBAL event_scheduler = ON;

-- Crear evento para actualizar la edad diariamente
DELIMITER $$
CREATE EVENT update_patient_age_daily
ON SCHEDULE EVERY 1 DAY
STARTS CURRENT_TIMESTAMP
DO
BEGIN
    UPDATE patient
    SET age = TIMESTAMPDIFF(YEAR, dateOfBirth, CURDATE());
END$$
DELIMITER ;