-- Insertar 40 pacientes (10 por cada sede)
INSERT INTO patient (DNI, firstName, lastName, age, phoneNumber, dateOfEntry, dateOfBirth, idLocation) VALUES
-- Pacientes en Los Olivos
('10000001', 'Juan', 'Pérez', 30, '912345678', NOW(), '1994-05-15', 'Los Olivos'),
('10000002', 'María', 'Gómez', 25, '923456789', NOW(), '1999-08-22', 'Los Olivos'),
('10000003', 'Carlos', 'Fernández', 40, '934567890', NOW(), '1984-11-10', 'Los Olivos'),
('10000004', 'Ana', 'Rodríguez', 35, '945678901', NOW(), '1989-07-05', 'Los Olivos'),
('10000005', 'Luis', 'García', 28, '956789012', NOW(), '1996-02-20', 'Los Olivos'),
('10000006', 'Elena', 'Martínez', 45, '967890123', NOW(), '1979-06-30', 'Los Olivos'),
('10000007', 'Miguel', 'Díaz', 50, '978901234', NOW(), '1974-09-12', 'Los Olivos'),
('10000008', 'Carmen', 'Hernández', 22, '989012345', NOW(), '2002-12-18', 'Los Olivos'),
('10000009', 'Ricardo', 'Castro', 33, '990123456', NOW(), '1991-04-07', 'Los Olivos'),
('10000010', 'Paula', 'Vega', 27, '901234567', NOW(), '1997-03-29', 'Los Olivos'),

-- Pacientes en Huánuco
('10000011', 'Andrés', 'Ríos', 31, '912345679', NOW(), '1993-08-11', 'Huanuco'),
('10000012', 'Lucía', 'Mendoza', 26, '923456780', NOW(), '1998-05-25', 'Huanuco'),
('10000013', 'Javier', 'Flores', 41, '934567891', NOW(), '1983-07-14', 'Huanuco'),
('10000014', 'Sofía', 'Torres', 37, '945678902', NOW(), '1987-10-19', 'Huanuco'),
('10000015', 'Eduardo', 'Jiménez', 29, '956789013', NOW(), '1995-06-09', 'Huanuco'),
('10000016', 'Natalia', 'Ruiz', 24, '967890124', NOW(), '2000-01-30', 'Huanuco'),
('10000017', 'Roberto', 'Ortega', 48, '978901235', NOW(), '1976-11-22', 'Huanuco'),
('10000018', 'Valeria', 'Luna', 32, '989012346', NOW(), '1992-09-04', 'Huanuco'),
('10000019', 'Gabriel', 'Peralta', 39, '990123457', NOW(), '1985-12-28', 'Huanuco'),
('10000020', 'Diana', 'Salazar', 36, '901234568', NOW(), '1988-07-15', 'Huanuco'),

-- Pacientes en Juliaca
('10000021', 'Francisco', 'Cárdenas', 34, '912345680', NOW(), '1990-05-10', 'Juliaca'),
('10000022', 'Carolina', 'Navarro', 23, '923456781', NOW(), '2001-02-14', 'Juliaca'),
('10000023', 'Hugo', 'Santos', 42, '934567892', NOW(), '1982-10-03', 'Juliaca'),
('10000024', 'Estefanía', 'Silva', 38, '945678903', NOW(), '1986-04-21', 'Juliaca'),
('10000025', 'Martín', 'Acosta', 44, '956789014', NOW(), '1980-09-07', 'Juliaca'),
('10000026', 'Isabela', 'Morales', 21, '967890125', NOW(), '2003-08-25', 'Juliaca'),
('10000027', 'Fernando', 'Pacheco', 47, '978901236', NOW(), '1977-05-19', 'Juliaca'),
('10000028', 'Victoria', 'Campos', 30, '989012347', NOW(), '1994-06-10', 'Juliaca'),
('10000029', 'Cristian', 'Fuentes', 33, '990123458', NOW(), '1991-01-08', 'Juliaca'),
('10000030', 'Beatriz', 'Mejía', 26, '901234569', NOW(), '1998-03-12', 'Juliaca'),

-- Pacientes en Carabayllo
('10000031', 'José', 'Ramírez', 29, '912345681', NOW(), '1995-04-15', 'Carabayllo'),
('10000032', 'Teresa', 'Guzmán', 37, '923456782', NOW(), '1987-07-22', 'Carabayllo'),
('10000033', 'Emilio', 'Vásquez', 45, '934567893', NOW(), '1979-12-10', 'Carabayllo'),
('10000034', 'Raquel', 'Núñez', 32, '945678904', NOW(), '1992-03-05', 'Carabayllo'),
('10000035', 'David', 'Salas', 27, '956789015', NOW(), '1997-08-20', 'Carabayllo'),
('10000036', 'Patricia', 'Maldonado', 46, '967890126', NOW(), '1978-05-30', 'Carabayllo'),
('10000037', 'Manuel', 'Herrera', 51, '978901237', NOW(), '1973-09-12', 'Carabayllo'),
('10000038', 'Flor', 'Mendoza', 22, '989012348', NOW(), '2002-01-18', 'Carabayllo'),
('10000039', 'Joaquín', 'Aguilar', 33, '990123459', NOW(), '1991-06-07', 'Carabayllo'),
('10000040', 'Ximena', 'Ortega', 28, '901234570', NOW(), '1996-02-25', 'Carabayllo');

INSERT INTO product (idProduct, category, productName, price) VALUES
('P001', 'Natursalas', 'Paracetamol 500mg', 5),
('P002', 'Natursalas', 'Ibuprofeno 400mg', 7),
('P003', 'Natursalas', 'Omeprazol 20mg', 10),
('P004', 'Natursalas', 'Amoxicilina 500mg', 15),
('P005', 'Natursalas', 'Loratadina 10mg', 8),
('P006', 'Natursalas', 'Termómetro Digital', 25),
('P007', 'Natursalas', 'Tensiómetro', 50),
('P008', 'Natursalas', 'Oxímetro de Pulso', 35),
('P009', 'Natursalas', 'Glucómetro', 40),
('P010', 'Natursalas', 'Estetoscopio', 55),

('P011', 'Farmacia', 'Jabón Antibacterial', 3),
('P012', 'Farmacia', 'Gel Desinfectante 500ml', 12),
('P013', 'Farmacia', 'Toallitas Desinfectantes', 10),
('P014', 'Farmacia', 'Papel Higiénico (4 rollos)', 7),
('P015', 'Farmacia', 'Shampoo Anticaspa', 15),
('P016', 'Farmacia', 'Vitamina C 500mg', 18),
('P017', 'Farmacia', 'Omega 3', 25),
('P018', 'Farmacia', 'Colágeno Hidrolizado', 30),
('P019', 'Farmacia', 'Proteína en Polvo', 45),
('P020', 'Farmacia', 'Magnesio en Cápsulas', 22),

('P021', 'Otros', 'Agua Mineral 1L', 2),
('P022', 'Otros', 'Bebida Energética', 8),
('P023', 'Otros', 'Té Verde en Botella', 5),
('P024', 'Otros', 'Jugo Natural 500ml', 6),
('P025', 'Otros', 'Leche Deslactosada 1L', 10),
('P026', 'Otros', 'Barra de Granola', 4),
('P027', 'Otros', 'Chocolate Negro 70%', 9),
('P028', 'Otros', 'Galletas Integrales', 7),
('P029', 'Otros', 'Nueces Mixtas 250g', 20),
('P030', 'Otros', 'Chips de Plátano', 6);

INSERT INTO productsIncrease (idProduct, dateOfEntry, quantity, idLocation) VALUES
-- Incrementos en Los Olivos
('P001', NOW(), 50, 'Los Olivos'),
('P001', NOW(), 30, 'Los Olivos'),
('P002', NOW(), 40, 'Los Olivos'),
('P002', NOW(), 20, 'Los Olivos'),
('P003', NOW(), 60, 'Los Olivos'),
('P003', NOW(), 25, 'Los Olivos'),
('P004', NOW(), 35, 'Los Olivos'),
('P004', NOW(), 15, 'Los Olivos'),
('P005', NOW(), 45, 'Los Olivos'),
('P005', NOW(), 20, 'Los Olivos'),

-- Incrementos en Huánuco
('P006', NOW(), 20, 'Huanuco'),
('P006', NOW(), 10, 'Huanuco'),
('P007', NOW(), 15, 'Huanuco'),
('P007', NOW(), 5, 'Huanuco'),
('P008', NOW(), 25, 'Huanuco'),
('P008', NOW(), 10, 'Huanuco'),
('P009', NOW(), 30, 'Huanuco'),
('P009', NOW(), 10, 'Huanuco'),
('P010', NOW(), 15, 'Huanuco'),
('P010', NOW(), 10, 'Huanuco'),

-- Incrementos en Juliaca
('P011', NOW(), 100, 'Juliaca'),
('P011', NOW(), 50, 'Juliaca'),
('P012', NOW(), 80, 'Juliaca'),
('P012', NOW(), 40, 'Juliaca'),
('P013', NOW(), 70, 'Juliaca'),
('P013', NOW(), 30, 'Juliaca'),
('P014', NOW(), 50, 'Juliaca'),
('P014', NOW(), 25, 'Juliaca'),
('P015', NOW(), 60, 'Juliaca'),
('P015', NOW(), 30, 'Juliaca'),

-- Incrementos en Carabayllo
('P016', NOW(), 45, 'Carabayllo'),
('P016', NOW(), 25, 'Carabayllo'),
('P017', NOW(), 30, 'Carabayllo'),
('P017', NOW(), 15, 'Carabayllo'),
('P018', NOW(), 25, 'Carabayllo'),
('P018', NOW(), 10, 'Carabayllo'),
('P019', NOW(), 50, 'Carabayllo'),
('P019', NOW(), 20, 'Carabayllo'),
('P020', NOW(), 40, 'Carabayllo'),
('P020', NOW(), 20, 'Carabayllo'),

-- Más incrementos en distintas sedes
('P021', NOW(), 200, 'Los Olivos'),
('P021', NOW(), 150, 'Huanuco'),
('P022', NOW(), 100, 'Juliaca'),
('P022', NOW(), 80, 'Carabayllo'),
('P023', NOW(), 75, 'Los Olivos'),
('P023', NOW(), 50, 'Huanuco'),
('P024', NOW(), 90, 'Juliaca'),
('P024', NOW(), 40, 'Carabayllo'),
('P025', NOW(), 60, 'Los Olivos'),
('P025', NOW(), 30, 'Huanuco'),

-- Snacks
('P026', NOW(), 80, 'Juliaca'),
('P026', NOW(), 50, 'Carabayllo'),
('P027', NOW(), 70, 'Los Olivos'),
('P027', NOW(), 40, 'Huanuco'),
('P028', NOW(), 60, 'Juliaca'),
('P028', NOW(), 35, 'Carabayllo'),
('P029', NOW(), 50, 'Los Olivos'),
('P029', NOW(), 20, 'Huanuco'),
('P030', NOW(), 40, 'Juliaca'),
('P030', NOW(), 25, 'Carabayllo');

-- ============================
-- 📌 Generar UUIDs para las ventas en cada sede
-- ============================
SET @sale1 = UUID(); SET @sale2 = UUID(); SET @sale3 = UUID(); SET @sale4 = UUID(); SET @sale5 = UUID();
SET @sale6 = UUID(); SET @sale7 = UUID(); SET @sale8 = UUID(); SET @sale9 = UUID(); SET @sale10 = UUID();
SET @sale11 = UUID(); SET @sale12 = UUID(); SET @sale13 = UUID(); SET @sale14 = UUID(); SET @sale15 = UUID();
SET @sale16 = UUID(); SET @sale17 = UUID(); SET @sale18 = UUID(); SET @sale19 = UUID(); SET @sale20 = UUID();

-- ============================
-- 📌 Detalles de ventas (se insertan antes que las ventas)
-- ============================
INSERT INTO salesDetail (idSale, idProduct, idLocation, quantity, price) VALUES
-- 📌 Los Olivos
(@sale1, 'P001', 'Los Olivos', 3, 5), (@sale1, 'P002', 'Los Olivos', 2, 7),
(@sale2, 'P003', 'Los Olivos', 1, 10), (@sale2, 'P005', 'Los Olivos', 4, 8),
(@sale3, 'P004', 'Los Olivos', 2, 15), (@sale3, 'P003', 'Los Olivos', 3, 10),
(@sale4, 'P005', 'Los Olivos', 2, 8), (@sale4, 'P001', 'Los Olivos', 1, 5),
(@sale5, 'P021', 'Los Olivos', 5, 2), (@sale5, 'P023', 'Los Olivos', 3, 5),

-- 📌 Huánuco
(@sale6, 'P021', 'Huanuco', 4, 2), (@sale6, 'P023', 'Huanuco', 3, 5),
(@sale7, 'P025', 'Huanuco', 2, 10), (@sale7, 'P027', 'Huanuco', 1, 9),
(@sale8, 'P006', 'Huanuco', 3, 25), (@sale8, 'P007', 'Huanuco', 1, 50),
(@sale9, 'P008', 'Huanuco', 1, 35), (@sale9, 'P009', 'Huanuco', 2, 40),
(@sale10, 'P010', 'Huanuco', 1, 55), (@sale10, 'P009', 'Huanuco', 1, 40),

-- 📌 Juliaca
(@sale11, 'P011', 'Juliaca', 2, 3), (@sale11, 'P013', 'Juliaca', 1, 10),
(@sale12, 'P012', 'Juliaca', 1, 12), (@sale12, 'P014', 'Juliaca', 2, 7),
(@sale13, 'P015', 'Juliaca', 2, 15), (@sale13, 'P011', 'Juliaca', 3, 3),
(@sale14, 'P012', 'Juliaca', 1, 12), (@sale14, 'P015', 'Juliaca', 2, 15),
(@sale15, 'P014', 'Juliaca', 1, 7), (@sale15, 'P013', 'Juliaca', 2, 10),

-- 📌 Carabayllo
(@sale16, 'P016', 'Carabayllo', 2, 18), (@sale16, 'P017', 'Carabayllo', 1, 25),
(@sale17, 'P018', 'Carabayllo', 1, 30), (@sale17, 'P019', 'Carabayllo', 1, 45),
(@sale18, 'P020', 'Carabayllo', 2, 22), (@sale18, 'P019', 'Carabayllo', 1, 45),
(@sale19, 'P022', 'Carabayllo', 1, 8), (@sale19, 'P024', 'Carabayllo', 2, 6),
(@sale20, 'P026', 'Carabayllo', 1, 4), (@sale20, 'P028', 'Carabayllo', 2, 7);

-- Actualizar las primeras 10 ventas con la categoría "Venta"
UPDATE sale SET category = 'Venta' WHERE idSale IN 
(@sale1, @sale2, @sale3, @sale4, @sale5, @sale6, @sale7, @sale8, @sale9, @sale10);

-- Actualizar las siguientes 10 ventas con la categoría "Zero"
UPDATE sale SET category = 'Zero' WHERE idSale IN 
(@sale11, @sale12, @sale13, @sale14, @sale15, @sale16, @sale17, @sale18, @sale19, @sale20);