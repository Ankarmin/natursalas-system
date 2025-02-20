-- Insertar productos para cada local
INSERT INTO product (idProduct, category, productName, stock, price, idLocation) VALUES
-- Productos para Los Olivos
('P001', 'Suplemento', 'Vitamina C 500mg', 50, 25, 'Los Olivos'),
('P002', 'Suplemento', 'Omega 3', 30, 40, 'Los Olivos'),
('P003', 'Medicina Natural', 'Jarabe de Eucalipto', 20, 35, 'Los Olivos'),
('P004', 'Medicina Natural', 'Té de Manzanilla', 40, 15, 'Los Olivos'),
('P005', 'Suplemento', 'Colágeno Hidrolizado', 25, 55, 'Los Olivos'),

-- Productos para Huánuco
('P006', 'Suplemento', 'Vitamina D3', 60, 30, 'Huanuco'),
('P007', 'Suplemento', 'Magnesio', 45, 28, 'Huanuco'),
('P008', 'Medicina Natural', 'Té de Hierba Luisa', 35, 20, 'Huanuco'),
('P009', 'Medicina Natural', 'Extracto de Uña de Gato', 25, 50, 'Huanuco'),
('P010', 'Suplemento', 'Probióticos', 40, 45, 'Huanuco'),

-- Productos para Juliaca
('P011', 'Suplemento', 'Calcio + Vitamina D', 55, 38, 'Juliaca'),
('P012', 'Suplemento', 'Hierro', 35, 22, 'Juliaca'),
('P013', 'Medicina Natural', 'Maca en Polvo', 40, 60, 'Juliaca'),
('P014', 'Medicina Natural', 'Jarabe de Propóleo', 30, 45, 'Juliaca'),
('P015', 'Suplemento', 'Multivitamínico', 20, 50, 'Juliaca'),

-- Productos para Carabayllo
('P016', 'Suplemento', 'Vitamina B12', 70, 32, 'Carabayllo'),
('P017', 'Suplemento', 'Zinc', 50, 27, 'Carabayllo'),
('P018', 'Medicina Natural', 'Extracto de Matico', 45, 35, 'Carabayllo'),
('P019', 'Medicina Natural', 'Té de Boldo', 35, 18, 'Carabayllo'),
('P020', 'Suplemento', 'Omega 3 y 6', 30, 42, 'Carabayllo');







-- Insertar pacientes para cada local sin el campo de edad
INSERT INTO patient (DNI, firstName, lastName, phoneNumber, dateOfBirth, district, idLocation) VALUES
-- Pacientes en Los Olivos
('10000001', 'Carlos', 'Ramírez', '987654321', '1994-06-10', 'Los Olivos', 'Los Olivos'),
('10000002', 'María', 'Gómez', '987123456', '1999-08-23', 'Los Olivos', 'Los Olivos'),
('10000003', 'Javier', 'Pérez', '986543210', '1984-04-12', 'Los Olivos', 'Los Olivos'),
('10000004', 'Lucía', 'Fernández', '985678901', '1989-11-30', 'Los Olivos', 'Los Olivos'),
('10000005', 'José', 'Martínez', '984567890', '1996-03-15', 'Los Olivos', 'Los Olivos'),

-- Pacientes en Huánuco
('10000006', 'Ana', 'Torres', '983456789', '1992-07-25', 'Huanuco', 'Huanuco'),
('10000007', 'Ricardo', 'Castro', '982345678', '1997-02-05', 'Huanuco', 'Huanuco'),
('10000008', 'Elena', 'Sánchez', '981234567', '1974-09-10', 'Huanuco', 'Huanuco'),
('10000009', 'Miguel', 'Flores', '980123456', '1986-05-20', 'Huanuco', 'Huanuco'),
('10000010', 'Gabriela', 'Vargas', '979123456', '1995-12-08', 'Huanuco', 'Huanuco'),

-- Pacientes en Juliaca
('10000011', 'Hugo', 'Reyes', '978234567', '1979-10-18', 'Juliaca', 'Juliaca'),
('10000012', 'Rosa', 'Navarro', '977345678', '1991-06-22', 'Juliaca', 'Juliaca'),
('10000013', 'Fernando', 'López', '976456789', '1988-03-14', 'Juliaca', 'Juliaca'),
('10000014', 'Beatriz', 'Mendoza', '975567890', '1998-09-29', 'Juliaca', 'Juliaca'),
('10000015', 'Daniel', 'Herrera', '974678901', '1993-11-17', 'Juliaca', 'Juliaca'),

-- Pacientes en Carabayllo
('10000016', 'Natalia', 'Ortega', '973789012', '2002-01-25', 'Carabayllo', 'Carabayllo'),
('10000017', 'Pedro', 'Cruz', '972890123', '1976-07-13', 'Carabayllo', 'Carabayllo'),
('10000018', 'Carmen', 'Jiménez', '971901234', '1985-12-05', 'Carabayllo', 'Carabayllo'),
('10000019', 'Andrés', 'Silva', '970012345', '1996-04-28', 'Carabayllo', 'Carabayllo'),
('10000020', 'Verónica', 'Delgado', '969123456', '1989-08-30', 'Carabayllo', 'Carabayllo');







-- ============================
-- 📌 Ventas y Detalles para Los Olivos
-- ============================
SET @sale1 = UUID();
SET @sale2 = UUID();
SET @sale3 = UUID();
SET @sale4 = UUID();
SET @sale5 = UUID();

INSERT INTO sale (idSale, DNI, diagnosis, category, idLocation) VALUES
(@sale1, '10000001', 'Resfriado común', 'Medicina Natural', 'Los Olivos'),
(@sale2, '10000002', 'Deficiencia vitamínica', 'Suplemento', 'Los Olivos'),
(@sale3, '10000003', 'Dolor muscular', 'Medicina Natural', 'Los Olivos'),
(@sale4, '10000004', 'Refuerzo inmunológico', 'Suplemento', 'Los Olivos'),
(@sale5, '10000005', 'Falta de energía', 'Suplemento', 'Los Olivos');

INSERT INTO salesDetail (idSale, idProduct, idLocation, quantity, price) VALUES
(@sale1, 'P001', 'Los Olivos', 2, 25), (@sale1, 'P003', 'Los Olivos', 1, 35),
(@sale2, 'P002', 'Los Olivos', 1, 40), (@sale2, 'P005', 'Los Olivos', 2, 55),
(@sale3, 'P004', 'Los Olivos', 3, 15), (@sale3, 'P003', 'Los Olivos', 2, 35),
(@sale4, 'P005', 'Los Olivos', 1, 55), (@sale4, 'P001', 'Los Olivos', 1, 25),
(@sale5, 'P002', 'Los Olivos', 2, 40), (@sale5, 'P004', 'Los Olivos', 2, 15);

-- ============================
-- 📌 Ventas y Detalles para Huánuco
-- ============================
SET @sale6 = UUID();
SET @sale7 = UUID();
SET @sale8 = UUID();
SET @sale9 = UUID();
SET @sale10 = UUID();

INSERT INTO sale (idSale, DNI, diagnosis, category, idLocation) VALUES
(@sale6, '10000006', 'Falta de calcio', 'Suplemento', 'Huanuco'),
(@sale7, '10000007', 'Sistema digestivo débil', 'Suplemento', 'Huanuco'),
(@sale8, '10000008', 'Resfriado frecuente', 'Medicina Natural', 'Huanuco'),
(@sale9, '10000009', 'Fatiga', 'Suplemento', 'Huanuco'),
(@sale10, '10000010', 'Deficiencia de minerales', 'Suplemento', 'Huanuco');

INSERT INTO salesDetail (idSale, idProduct, idLocation, quantity, price) VALUES
(@sale6, 'P006', 'Huanuco', 2, 30), (@sale6, 'P007', 'Huanuco', 1, 28),
(@sale7, 'P010', 'Huanuco', 1, 45), (@sale7, 'P009', 'Huanuco', 1, 50),
(@sale8, 'P008', 'Huanuco', 3, 20), (@sale8, 'P009', 'Huanuco', 1, 50),
(@sale9, 'P007', 'Huanuco', 1, 28), (@sale9, 'P006', 'Huanuco', 2, 30),
(@sale10, 'P010', 'Huanuco', 2, 45), (@sale10, 'P008', 'Huanuco', 2, 20);

-- ============================
-- 📌 Ventas y Detalles para Juliaca
-- ============================
SET @sale11 = UUID();
SET @sale12 = UUID();
SET @sale13 = UUID();
SET @sale14 = UUID();
SET @sale15 = UUID();

INSERT INTO sale (idSale, DNI, diagnosis, category, idLocation) VALUES
(@sale11, '10000011', 'Osteoporosis', 'Suplemento', 'Juliaca'),
(@sale12, '10000012', 'Anemia', 'Suplemento', 'Juliaca'),
(@sale13, '10000013', 'Sistema inmune débil', 'Medicina Natural', 'Juliaca'),
(@sale14, '10000014', 'Dolores articulares', 'Medicina Natural', 'Juliaca'),
(@sale15, '10000015', 'Cansancio extremo', 'Suplemento', 'Juliaca');

INSERT INTO salesDetail (idSale, idProduct, idLocation, quantity, price) VALUES
(@sale11, 'P011', 'Juliaca', 1, 38), (@sale11, 'P013', 'Juliaca', 1, 60),
(@sale12, 'P012', 'Juliaca', 2, 22), (@sale12, 'P015', 'Juliaca', 1, 50),
(@sale13, 'P014', 'Juliaca', 2, 45), (@sale13, 'P011', 'Juliaca', 1, 38),
(@sale14, 'P013', 'Juliaca', 1, 60), (@sale14, 'P012', 'Juliaca', 1, 22),
(@sale15, 'P015', 'Juliaca', 2, 50), (@sale15, 'P014', 'Juliaca', 1, 45);

-- ============================
-- 📌 Ventas y Detalles para Carabayllo
-- ============================
SET @sale16 = UUID();
SET @sale17 = UUID();
SET @sale18 = UUID();
SET @sale19 = UUID();
SET @sale20 = UUID();

INSERT INTO sale (idSale, DNI, diagnosis, category, idLocation) VALUES
(@sale16, '10000016', 'Déficit de vitamina B12', 'Suplemento', 'Carabayllo'),
(@sale17, '10000017', 'Sistema inmunológico debilitado', 'Medicina Natural', 'Carabayllo'),
(@sale18, '10000018', 'Deficiencia de Zinc', 'Suplemento', 'Carabayllo'),
(@sale19, '10000019', 'Problemas gástricos', 'Medicina Natural', 'Carabayllo'),
(@sale20, '10000020', 'Refuerzo del sistema nervioso', 'Suplemento', 'Carabayllo');

INSERT INTO salesDetail (idSale, idProduct, idLocation, quantity, price) VALUES
(@sale16, 'P016', 'Carabayllo', 1, 32), (@sale16, 'P017', 'Carabayllo', 1, 27),
(@sale17, 'P018', 'Carabayllo', 1, 35), (@sale17, 'P019', 'Carabayllo', 2, 18),
(@sale18, 'P017', 'Carabayllo', 2, 27), (@sale18, 'P016', 'Carabayllo', 1, 32),
(@sale19, 'P019', 'Carabayllo', 2, 18), (@sale19, 'P018', 'Carabayllo', 1, 35),
(@sale20, 'P020', 'Carabayllo', 1, 42), (@sale20, 'P016', 'Carabayllo', 1, 32);







-- ============================
-- 📌 Incrementos para Los Olivos
-- ============================
INSERT INTO productsIncrease (idProduct, quantity, idLocation) VALUES
('P001', 10, 'Los Olivos'), ('P001', 5, 'Los Olivos'),
('P002', 8, 'Los Olivos'), ('P002', 7, 'Los Olivos'),
('P003', 12, 'Los Olivos'), ('P003', 10, 'Los Olivos'),
('P004', 15, 'Los Olivos'), ('P004', 5, 'Los Olivos'),
('P005', 6, 'Los Olivos'), ('P005', 4, 'Los Olivos');

-- ============================
-- 📌 Incrementos para Huánuco
-- ============================
INSERT INTO productsIncrease (idProduct, quantity, idLocation) VALUES
('P006', 9, 'Huanuco'), ('P006', 5, 'Huanuco'),
('P007', 11, 'Huanuco'), ('P007', 7, 'Huanuco'),
('P008', 13, 'Huanuco'), ('P008', 6, 'Huanuco'),
('P009', 7, 'Huanuco'), ('P009', 9, 'Huanuco'),
('P010', 10, 'Huanuco'), ('P010', 8, 'Huanuco');

-- ============================
-- 📌 Incrementos para Juliaca
-- ============================
INSERT INTO productsIncrease (idProduct, quantity, idLocation) VALUES
('P011', 14, 'Juliaca'), ('P011', 6, 'Juliaca'),
('P012', 8, 'Juliaca'), ('P012', 7, 'Juliaca'),
('P013', 11, 'Juliaca'), ('P013', 9, 'Juliaca'),
('P014', 10, 'Juliaca'), ('P014', 5, 'Juliaca'),
('P015', 9, 'Juliaca'), ('P015', 4, 'Juliaca');

-- ============================
-- 📌 Incrementos para Carabayllo
-- ============================
INSERT INTO productsIncrease (idProduct, quantity, idLocation) VALUES
('P016', 10, 'Carabayllo'), ('P016', 7, 'Carabayllo'),
('P017', 12, 'Carabayllo'), ('P017', 6, 'Carabayllo'),
('P018', 9, 'Carabayllo'), ('P018', 8, 'Carabayllo'),
('P019', 11, 'Carabayllo'), ('P019', 5, 'Carabayllo'),
('P020', 8, 'Carabayllo'), ('P020', 6, 'Carabayllo');