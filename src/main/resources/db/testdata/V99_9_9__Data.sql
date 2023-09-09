-- DATA DE PRUEBA

SET @ID_SUPER_ADMIN = UUID_TO_BIN('3bd9127c-1b92-11ee-be56-0242ac100001');

-- Videos

INSERT INTO video (id, id_usuario, codigo, descripcion, fecha_hora_creacion, creado_por, fecha_hora_actualizacion, actualizado_por) VALUES
(UUID_TO_BIN('3bd9127c-1b92-11ee-be56-0242ac110001'), UUID_TO_BIN('3bd9127c-1b92-11ee-be56-0242ac100003'), 'aqn1aKxYCPQ', '{"items":[{"inicio":"3000","descripcion":"Dos hombres conversando en una calle de noche"},{"inicio":"37000","descripcion":"Un hombre cae sobre un charco sucio"},{"inicio":"70000","descripcion":"Un hombre observa un anillo en su mano"}]}', SYSDATE(), @ID_SUPER_ADMIN, SYSDATE(), @ID_SUPER_ADMIN);

-- Imágenes

INSERT INTO imagen (id, id_usuario, hash, descripcion, fecha_hora_creacion, creado_por, fecha_hora_actualizacion, actualizado_por) VALUES
(UUID_TO_BIN('3bd9127c-1b92-11ee-be56-0242ac120001'), UUID_TO_BIN('3bd9127c-1b92-11ee-be56-0242ac100003'), '44b654b67711857201de7396c2fac48a3d118b8db7f424e84b03d3d767e47f69', 'Una planta con gotas en sus hojas', SYSDATE(), @ID_SUPER_ADMIN, SYSDATE(), @ID_SUPER_ADMIN);