-- Inserción de localidades
INSERT INTO location (idLocation, address)
VALUES 
    ('Los Olivos', 'Av. Universitaria, Los Olivos'),
    ('Huanuco', 'Av. Alameda, Huánuco'),
    ('Juliaca', 'Jr. San Martín, Juliaca'),
    ('Carabayllo', 'Av. Túpac Amaru, Carabayllo');

-- Inserción de usuarios de cada sede con el rol "user"
INSERT INTO user (email, password, role, idLocation)
VALUES 
    ('sedelosolivos@natursalas.pe', 'natursalaslosolivos', 'user', 'Los Olivos'),
    ('sedehuanuco@natursalas.pe', 'natursalashuanuco', 'user', 'Huanuco'),
    ('sedejuliaca@natursalas.pe', 'natursalasjuliaca', 'user', 'Juliaca'),
    ('sedecarabayllo@natursalas.pe', 'natursalascarabayllo', 'user', 'Carabayllo');

-- Inserción de usuario administrador con acceso a todas las localidades
INSERT INTO user (email, password, role, idLocation)
VALUES ('admin@natursalas.pe', 'admin', 'admin', NULL);