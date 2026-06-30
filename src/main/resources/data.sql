INSERT INTO usuario
(correo, nombre, telefono, profesional, contrasena, edad, sexo, pareja, familia, situacion_economica)
VALUES
    ('juan.perez@gmail.com', 'Juan Pérez', '600123456', false, '1234abcd', 28, 'M', 'Soltero', 'Vive con padres', 'Media');


INSERT INTO usuario
(correo, nombre, telefono, profesional, contrasena, edad, sexo, pareja, familia, situacion_economica)
VALUES
    ('maria.garcia@gmail.com', 'María García', '611987654', true, 'maria2025', 35, 'F', 'Casada', 'Tiene 2 hijos', 'Alta');


INSERT INTO usuario
(correo, nombre, telefono, profesional, contrasena, edad, sexo, pareja, familia, situacion_economica)
VALUES
    ('alex.lopez@gmail.com', 'Alex Lopez', '622555444', false, 'alexpass', 22, 'M', 'Pareja estable', 'Independiente', 'Baja');


INSERT INTO sentimiento
(nombre, icono, valor)
VALUES
    ('Feliz', '😊', 5);


INSERT INTO sentimiento
(nombre, icono, valor)
VALUES
    ('Contento', '🙂', 4);


INSERT INTO sentimiento
(nombre, icono, valor)
VALUES
    ('Neutral', '😐', 3);


INSERT INTO sentimiento
(nombre, icono, valor)
VALUES
    ('Triste', '😢', 2);


INSERT INTO sentimiento
(nombre, icono, valor)
VALUES
    ('Enfadado', '😡', 1);


INSERT INTO dia
(sentimiento_id, fecha, nota)
VALUES
    (1, '2026-06-20', 'He tenido un buen dia');


INSERT INTO dia
(sentimiento_id, fecha, nota)
VALUES
    (2, '2026-06-19', 'Estoy bastante contento hoy');


INSERT INTO dia
(sentimiento_id, fecha, nota)
VALUES
    (3, '2026-06-18', 'Día normal, sin cambios importantes');


INSERT INTO dia
(sentimiento_id, fecha, nota)
VALUES
    (4, '2026-06-17', 'He estado algo cansado y triste');


INSERT INTO dia
(sentimiento_id, fecha, nota)
VALUES
    (5, '2026-06-16', 'Me he enfadado por un problema en el trabajo');


