-- DATA DE PRUEBA

SET @ID_SUPER_ADMIN = UUID_TO_BIN('3bd9127c-1b92-11ee-be56-0242ac100001');

-- Roles

INSERT INTO rol (id, nombre, descripcion) VALUES
(UUID_TO_BIN('3bd9127c-1b92-11ee-be56-0242ac000001'), 'SUPER_ADMIN', NULL);

-- Usuarios

INSERT INTO usuario (id, nombres, apellidos, contrasenia, correo, id_rol, fecha_hora_creacion, creado_por, fecha_hora_actualizacion, actualizado_por) VALUES
(UUID_TO_BIN('3bd9127c-1b92-11ee-be56-0242ac100001'), 'Hugo', 'Rodriguez', '$2y$10$MCRzCrtO0EUoExXenXZwWOZfFUK3St8BVdnMTau.e6hs8mL5pU6TO', 'U201722123@upc.edu.pe', UUID_TO_BIN('3bd9127c-1b92-11ee-be56-0242ac000001'), SYSDATE(), @ID_SUPER_ADMIN, SYSDATE(), @ID_SUPER_ADMIN),
(UUID_TO_BIN('3bd9127c-1b92-11ee-be56-0242ac100002'), 'Sebastian', 'Diaz', '$2y$10$MCRzCrtO0EUoExXenXZwWOZfFUK3St8BVdnMTau.e6hs8mL5pU6TO', 'U201717471@upc.edu.pe', UUID_TO_BIN('3bd9127c-1b92-11ee-be56-0242ac000001'), SYSDATE(), @ID_SUPER_ADMIN, SYSDATE(), @ID_SUPER_ADMIN);
