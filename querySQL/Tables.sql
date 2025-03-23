-- ==============================
-- 📌 Estructura de la Base de Datos
-- ==============================

-- Ubicaciones
CREATE TABLE location (
    idLocation VARCHAR(50) PRIMARY KEY,
    address VARCHAR(100)
);

-- Usuarios
CREATE TABLE user (
    email VARCHAR(50) PRIMARY KEY,
    password VARCHAR(50) NOT NULL,
    role ENUM('admin', 'user') NOT NULL DEFAULT 'user',
    idLocation VARCHAR(50) NULL,
    FOREIGN KEY (idLocation) REFERENCES location(idLocation)
);

-- Pacientes
CREATE TABLE patient (
    DNI CHAR(8) PRIMARY KEY,
    firstName VARCHAR(50) NOT NULL,
    lastName VARCHAR(50) NOT NULL,
    age INT NOT NULL CHECK (age > 0),
    phoneNumber VARCHAR(9) NOT NULL CHECK (phoneNumber REGEXP '^[0-9]{9}$'),
    dateOfEntry TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    dateOfBirth DATE NOT NULL,
    idLocation VARCHAR(50) NOT NULL,
    FOREIGN KEY (idLocation) REFERENCES location(idLocation)
);

-- Productos
CREATE TABLE product (
    idProduct VARCHAR(20) PRIMARY KEY,
    category VARCHAR(30) NOT NULL,
    productName VARCHAR(50) NOT NULL,
    price INT NOT NULL CHECK (price >= 0)
);

-- Productos en ubicaciones con stock
CREATE TABLE productsForLocation (
    idProduct VARCHAR(20),
    idLocation VARCHAR(50),
    stock INT NOT NULL DEFAULT 0 CHECK (stock >= 0),
    PRIMARY KEY (idProduct, idLocation),
    FOREIGN KEY (idProduct) REFERENCES product(idProduct),
    FOREIGN KEY (idLocation) REFERENCES location(idLocation)
);

-- Incremento de stock
CREATE TABLE productsIncrease (
    idProductIncrease INT AUTO_INCREMENT PRIMARY KEY,
    idProduct VARCHAR(20) NOT NULL,
    dateOfEntry TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    quantity INT NOT NULL CHECK (quantity > 0),
    idLocation VARCHAR(50) NOT NULL,
    FOREIGN KEY (idProduct, idLocation) REFERENCES productsForLocation(idProduct, idLocation)
);

-- Detalles de ventas
CREATE TABLE salesDetail (
    idSale CHAR(36),
    idProduct VARCHAR(20) NOT NULL,
    idLocation VARCHAR(50) NOT NULL,
    quantity INT NOT NULL CHECK (quantity > 0),
    price INT NOT NULL CHECK (price >= 0),
    subtotal INT GENERATED ALWAYS AS (quantity * price) STORED,
    PRIMARY KEY (idSale, idProduct, idLocation),
    FOREIGN KEY (idProduct, idLocation) REFERENCES productsForLocation(idProduct, idLocation)
);

-- Ventas
CREATE TABLE sale (
    idSale CHAR(36) PRIMARY KEY,
    DNI CHAR(8) NOT NULL,
    diagnosis TEXT NOT NULL,
    category VARCHAR(30) NOT NULL,
    saleDate TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    idLocation VARCHAR(50) NOT NULL,
    subtotal INT NOT NULL,
    FOREIGN KEY (idSale) REFERENCES salesDetail(idSale) ON DELETE CASCADE,
    FOREIGN KEY (DNI) REFERENCES patient(DNI),
    FOREIGN KEY (idLocation) REFERENCES location(idLocation)
);

-- ==============================
-- 📌 Triggers para consistencia de datos
-- ==============================
DELIMITER $$

-- ✅ Insertar productos en cada ubicación al agregar un nuevo producto
CREATE TRIGGER afterInsertProduct
AFTER INSERT ON product
FOR EACH ROW
BEGIN
    INSERT INTO productsForLocation (idProduct, idLocation, stock)
    SELECT NEW.idProduct, idLocation, 0 FROM location;
END$$

-- ✅ Insertar productos en una nueva locación con stock = 0
CREATE TRIGGER afterInsertLocation
AFTER INSERT ON location
FOR EACH ROW
BEGIN
    INSERT INTO productsForLocation (idProduct, idLocation, stock)
    SELECT idProduct, NEW.idLocation, 0 FROM product;
END$$

-- ✅ Actualizar stock al incrementar productos
CREATE TRIGGER afterProductsIncrease
AFTER INSERT ON productsIncrease
FOR EACH ROW
BEGIN
    UPDATE productsForLocation
    SET stock = stock + NEW.quantity
    WHERE idProduct = NEW.idProduct AND idLocation = NEW.idLocation;
END$$

-- ✅ Validar stock antes de registrar ventas
CREATE TRIGGER beforeSalesDetailsInsert
BEFORE INSERT ON salesDetail
FOR EACH ROW
BEGIN
    DECLARE currentStock INT;
    SELECT stock INTO currentStock 
    FROM productsForLocation 
    WHERE idProduct = NEW.idProduct AND idLocation = NEW.idLocation;
    
    IF currentStock < NEW.quantity THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Stock insuficiente para la venta';
    END IF;
END$$

-- ✅ Crear `sale` automáticamente al agregar detalles de venta
CREATE TRIGGER afterInsertSalesDetail
AFTER INSERT ON salesDetail
FOR EACH ROW
BEGIN
    IF NOT EXISTS (SELECT 1 FROM sale WHERE idSale = NEW.idSale) THEN
        INSERT INTO sale (idSale, DNI, diagnosis, category, idLocation, subtotal)
        SELECT 
            NEW.idSale,
            (SELECT DNI FROM patient WHERE idLocation = NEW.idLocation ORDER BY RAND() LIMIT 1),
            'Pendiente',
            'Pendiente',
            NEW.idLocation,
            0;
    END IF;

    -- Actualizar subtotal de la venta
    UPDATE sale
    SET subtotal = (SELECT COALESCE(SUM(subtotal), 0) FROM salesDetail WHERE idSale = NEW.idSale)
    WHERE idSale = NEW.idSale;
END$$

-- ✅ Actualizar subtotal en venta tras cambios en `salesDetail`
CREATE TRIGGER afterUpdateSalesDetail
AFTER UPDATE ON salesDetail
FOR EACH ROW
BEGIN
    UPDATE sale
    SET subtotal = (SELECT COALESCE(SUM(subtotal), 0) FROM salesDetail WHERE idSale = NEW.idSale)
    WHERE idSale = NEW.idSale;
END$$

-- ✅ Evitar insertar ventas sin detalles
CREATE TRIGGER beforeInsertSale
BEFORE INSERT ON sale
FOR EACH ROW
BEGIN
    IF (SELECT COUNT(*) FROM salesDetail WHERE idSale = NEW.idSale) = 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'No se puede insertar una venta sin detalles.';
    END IF;
END$$

-- ✅ Disminuir stock en `productsForLocation` al registrar una venta
CREATE TRIGGER afterInsertSalesDetail_UpdateStock
AFTER INSERT ON salesDetail
FOR EACH ROW
BEGIN
    UPDATE productsForLocation
    SET stock = stock - NEW.quantity
    WHERE idProduct = NEW.idProduct AND idLocation = NEW.idLocation;
END$$

-- ==============================
-- 📌 Evento para actualizar edad de pacientes
-- ==============================
CREATE EVENT update_patient_age_daily
ON SCHEDULE EVERY 1 DAY STARTS CURRENT_TIMESTAMP
DO BEGIN
    UPDATE patient 
    SET age = TIMESTAMPDIFF(YEAR, dateOfBirth, CURDATE())
    WHERE age != TIMESTAMPDIFF(YEAR, dateOfBirth, CURDATE());
END$$

DELIMITER ;