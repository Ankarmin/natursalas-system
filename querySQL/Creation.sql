-- Inserción de localidades
INSERT INTO location (idLocation, address)
VALUES 
    ('Los Olivos', 'Los Olivos'),
    ('Huanuco', 'Huanuco'),
    ('Juliaca', 'Juliaca'),
    ('Carabayllo', 'Carabayllo');

-- Inserción de usuarios de cada sede con el rol "user"
INSERT INTO user (userName, password, role, idLocation)
VALUES 
    ('sedelosolivos@natursalas.pe', 'natursalaslosolivos', 'user', 'Los Olivos'),
    ('sedehuanuco@natursalas.pe', 'natursalashuanuco', 'user', 'Huanuco'),
    ('sedejuliaca@natursalas.pe', 'natursalasjuliaca', 'user', 'Juliaca'),
    ('sedecarabayllo@natursalas.pe', 'natursalascarabayllo', 'user', 'Carabayllo');

SELECT * FROM location;
SELECT * FROM user;

-- Inserción de usuario administrador con acceso a todas las localidades
INSERT INTO user (userName, password, role, idLocation)
VALUES ('admin@natursalas.pe', 'supersecurepassword', 'admin', NULL);

-- Consulta para obtener pacientes con validación de admin
SELECT * FROM patient 
WHERE idLocation = (SELECT idLocation FROM user WHERE userName = 'usuario_actual')
   OR (SELECT role FROM user WHERE userName = 'usuario_actual') = 'admin';

-- Consulta para obtener ventas con validación de admin
SELECT * FROM sale 
WHERE idLocation = (SELECT idLocation FROM user WHERE userName = 'usuario_actual')
   OR (SELECT role FROM user WHERE userName = 'usuario_actual') = 'admin';

-- Consulta para obtener productos con validación de admin
SELECT * FROM product 
WHERE idLocation = (SELECT idLocation FROM user WHERE userName = 'usuario_actual')
   OR (SELECT role FROM user WHERE userName = 'usuario_actual') = 'admin';