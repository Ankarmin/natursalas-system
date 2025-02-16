-- Mostrar todos los pacientes si es admin o solo los de su ubicación si es usuario normal
SELECT * FROM patient 
WHERE idLocation = (SELECT idLocation FROM user WHERE email = 'admin@natursalas.pe')
   OR (SELECT role FROM user WHERE email = 'admin@natursalas.pe') = 'admin'
   OR (SELECT idLocation FROM user WHERE email = 'admin@natursalas.pe') IS NULL;

-- Mostrar todas las ventas si es admin o solo las de su ubicación si es usuario normal
SELECT * FROM sale 
WHERE idLocation = (SELECT idLocation FROM user WHERE email = 'admin@natursalas.pe')
   OR (SELECT role FROM user WHERE email = 'admin@natursalas.pe') = 'admin'
   OR (SELECT idLocation FROM user WHERE email = 'admin@natursalas.pe') IS NULL;

-- Ver detalles de ventas
SELECT 
    sd.idProduct, 
    pr.productName, 
    sd.quantity, 
    sd.price, 
    sd.subtotal
FROM sale s
JOIN salesDetail sd ON s.idSale = sd.idSale
JOIN patient p ON s.DNI = p.DNI
JOIN product pr ON sd.idProduct = pr.idProduct
WHERE s.idLocation = (SELECT idLocation FROM user WHERE email = 'admin@natursalas.pe')
   OR (SELECT role FROM user WHERE email = 'admin@natursalas.pe') = 'admin'
   OR (SELECT idLocation FROM user WHERE email = 'admin@natursalas.pe') IS NULL;

-- Mostrar productos disponibles en la ubicación del usuario o todos si es admin
SELECT * FROM product 
WHERE idLocation = (SELECT idLocation FROM user WHERE email = 'admin@natursalas.pe')
   OR (SELECT role FROM user WHERE email = 'admin@natursalas.pe') = 'admin'
   OR (SELECT idLocation FROM user WHERE email = 'admin@natursalas.pe') IS NULL;