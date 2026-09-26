-- ===================================================
-- SCRIPT DE CARGA INICIAL DE DATOS (DML SEED DATA)
-- Archivo: scripts/V2__seed_data.sql
-- ===================================================

-- 1. Insertar Roles del sistema
INSERT INTO rol (nombre_rol, descripcion) VALUES
                                              ('ADMIN', 'Administrador total del sistema'),
                                              ('BIBLIOTECARIO', 'Gestor de préstamos y catálogo'),
                                              ('LECTOR', 'Usuario con acceso a lectura y reservas');

-- 2. Insertar Usuarios de prueba (asociados a sus roles id_rol 1, 2 y 3)
INSERT INTO usuario (id_rol, nombre_completo, credenciales, estado) VALUES
                                                                        (1, 'Administrador Sistema', 'admin@bibliosmart.com:admin123', 'ACTIVO'),
                                                                        (2, 'Carlos Bibliotecario', 'carlos@bibliosmart.com:biblio123', 'ACTIVO'),
                                                                        (3, 'Ana Torres', 'ana.torres@gmail.com:user123', 'ACTIVO');

-- 3. Insertar Perfil de Lector (Asociado al usuario Ana Torres id_usuario 3)
INSERT INTO lector (id_usuario, nro_lector, datos_contacto, fecha_registro) VALUES
    (3, 'LEC-2026-001', 'Tel: +591 70000000', '2026-01-15');

-- 4. Insertar Editoriales
INSERT INTO editorial (nombre) VALUES
                                   ('O''Reilly Media'),
                                   ('Pearson Education'),
                                   ('Editorial Planeta');

-- 5. Insertar Libros (Asociados a sus editoriales id_editorial 2 y 1)
INSERT INTO libro (id_editorial,git add scripts/V2__seed_data.sql titulo, autor, categoria, anio_publicacion) VALUES
                                                                                 (2, 'Clean Code', 'Robert C. Martin', 'Ingeniería de Software', 2008),
                                                                                 (1, 'Sistemas Distribuidos', 'Andrew S. Tanenbaum', 'Tecnología', 2023);

-- 6. Insertar Reserva de prueba (Lector 1, Libro 1)
INSERT INTO reserva (id_lector, id_libro, fecha_reserva, fecha_limite_retiro, estado) VALUES
    (1, 1, '2026-09-25', '2026-09-28', 'PENDIENTE');