-- DATA DE PRUEBA

SET @ID_SUPER_ADMIN = UUID_TO_BIN('3bd9127c-1b92-11ee-be56-0242ac100001');

-- Roles

INSERT INTO rol (id, nombre, descripcion) VALUES
(UUID_TO_BIN('3bd9127c-1b92-11ee-be56-0242ac000001'), 'SUPER_ADMIN', NULL),
(UUID_TO_BIN('3bd9127c-1b92-11ee-be56-0242ac000002'), 'USUARIO', NULL);

-- Usuarios

INSERT INTO usuario (id, nombres, apellidos, contrasenia, correo, id_rol, fecha_hora_creacion, creado_por, fecha_hora_actualizacion, actualizado_por) VALUES
(UUID_TO_BIN('3bd9127c-1b92-11ee-be56-0242ac100001'), 'Hugo', 'Rodriguez', '$2y$10$MCRzCrtO0EUoExXenXZwWOZfFUK3St8BVdnMTau.e6hs8mL5pU6TO', 'U201722123@upc.edu.pe', UUID_TO_BIN('3bd9127c-1b92-11ee-be56-0242ac000001'), SYSDATE(), @ID_SUPER_ADMIN, SYSDATE(), @ID_SUPER_ADMIN),
(UUID_TO_BIN('3bd9127c-1b92-11ee-be56-0242ac100002'), 'Sebastian', 'Diaz', '$2y$10$MCRzCrtO0EUoExXenXZwWOZfFUK3St8BVdnMTau.e6hs8mL5pU6TO', 'U201717471@upc.edu.pe', UUID_TO_BIN('3bd9127c-1b92-11ee-be56-0242ac000001'), SYSDATE(), @ID_SUPER_ADMIN, SYSDATE(), @ID_SUPER_ADMIN),
(UUID_TO_BIN('3bd9127c-1b92-11ee-be56-0242ac100003'), 'Enrique', 'Flores', '$2y$10$tnAw7oUAcllMPZsKB6yNS.MWFypSiXjzZjW7qEkDUUKT1gjoe6s4S', 'eflores@gmail.com', UUID_TO_BIN('3bd9127c-1b92-11ee-be56-0242ac000002'), SYSDATE(), @ID_SUPER_ADMIN, SYSDATE(), @ID_SUPER_ADMIN);

-- Videos

INSERT INTO video (id, id_usuario, codigo, descripcion, fecha_hora_creacion, creado_por, fecha_hora_actualizacion, actualizado_por) VALUES
(UUID_TO_BIN('3bd9127c-1b92-11ee-be56-0242ac110001'), UUID_TO_BIN('3bd9127c-1b92-11ee-be56-0242ac100003'), 'aqn1aKxYCPQ', '{"items":[{"inicio":"3000","descripcion":"Dos hombres conversando en una calle de noche"},{"inicio":"37000","descripcion":"Un hombre cae sobre un charco sucio"},{"inicio":"70000","descripcion":"Un hombre observa un anillo en su mano"}]}', SYSDATE(), @ID_SUPER_ADMIN, SYSDATE(), @ID_SUPER_ADMIN);