-- Tabla que almacena las ubicaciones o locales
CREATE TABLE location (
    idLocation VARCHAR(50) PRIMARY KEY,
    locationName VARCHAR(100) NOT NULL,
    address VARCHAR(100),
    city VARCHAR(50)
);

-- Tabla que almacena los usuarios y su rol asociado
CREATE TABLE user (
    idUser INT AUTO_INCREMENT PRIMARY KEY,
    userName VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(50) NOT NULL,
    role ENUM('admin', 'local') NOT NULL DEFAULT 'local',
    idLocation VARCHAR(50) UNIQUE,
    FOREIGN KEY (idLocation) REFERENCES location(idLocation)
);

-- Tabla que almacena los pacientes y sus datos personales
CREATE TABLE patient (
    idPatient CHAR(8) PRIMARY KEY,
    firstName VARCHAR(50) NOT NULL,
    lastName VARCHAR(50) NOT NULL,
    age INT NOT NULL CHECK (age > 0),
    phoneNumber VARCHAR(9) NOT NULL CHECK (phoneNumber REGEXP '^[0-9]{9}$'),
    dateOfConsultation DATE NOT NULL,
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
    dateOfEntry DATE NOT NULL,
    quantity INT NOT NULL CHECK (quantity > 0),
    idLocation VARCHAR(50) NOT NULL,
    FOREIGN KEY (idProduct, idLocation) REFERENCES product(idProduct, idLocation),
    FOREIGN KEY (idLocation) REFERENCES location(idLocation)
);

-- Tabla que almacena las ventas realizadas
CREATE TABLE sale (
    idSale INT AUTO_INCREMENT PRIMARY KEY,
    idPatient CHAR(8),
    diagnosis TEXT NOT NULL,
    category VARCHAR(30) NOT NULL,
    saleDate TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    idLocation VARCHAR(50) NOT NULL,
    idUser INT NOT NULL,
    FOREIGN KEY (idPatient) REFERENCES patient(idPatient),
    FOREIGN KEY (idLocation) REFERENCES location(idLocation),
    FOREIGN KEY (idUser) REFERENCES user(idUser)
);

-- Tabla para almacenar los detalles de los productos vendidos en cada venta
CREATE TABLE salesDetail (
    idSale INT NOT NULL,
    idProduct VARCHAR(20) NOT NULL,
    idLocation VARCHAR(50) NOT NULL,
    quantity INT NOT NULL CHECK (quantity > 0),
    price INT NOT NULL CHECK (price >= 0),
    PRIMARY KEY (idSale, idProduct, idLocation),
    FOREIGN KEY (idSale) REFERENCES sale(idSale),
    FOREIGN KEY (idProduct, idLocation) REFERENCES product(idProduct, idLocation)
);

-- Trigger para actualizar el stock al incrementar productos
DELIMITER $$
CREATE TRIGGER afterProductsIncrease
AFTER INSERT ON productsIncrease
FOR EACH ROW
BEGIN
    UPDATE products
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
    UPDATE products
    SET stock = stock - NEW.quantity
    WHERE idProduct = NEW.idProduct AND idLocation = NEW.idLocation;
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
    FROM products
    WHERE idProduct = NEW.idProduct AND idLocation = NEW.idLocation;

    IF currentStock < NEW.quantity THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Stock insuficiente para realizar la venta';
    END IF;
END$$
DELIMITER ;

-- Trigger para validar que la fecha de nacimiento sea anterior a la fecha actual
DELIMITER $$
CREATE TRIGGER beforeInsertPatients
BEFORE INSERT ON patient
FOR EACH ROW
BEGIN
    IF NEW.dateOfBirth >= CURRENT_DATE THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'dateOfBirth debe ser anterior a la fecha actual';
    END IF;
END$$
DELIMITER ;

DELIMITER $$
CREATE TRIGGER beforeUpdatePatients
BEFORE UPDATE ON patient
FOR EACH ROW
BEGIN
    IF NEW.dateOfBirth >= CURRENT_DATE THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'dateOfBirth debe ser anterior a la fecha actual';
    END IF;
END$$
DELIMITER ;