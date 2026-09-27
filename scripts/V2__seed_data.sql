-- ===================================================
-- SCRIPT DE CARGA INICIAL DE DATOS ACTUALIZADO
-- ===================================================

-- 1. Insertar Roles del sistema
INSERT INTO rol (nombre_rol, descripcion) VALUES
                                              ('ADMIN', 'Administrador total del sistema'),
                                              ('BIBLIOTECARIO', 'Gestor de préstamos y catálogo'),
                                              ('LECTOR', 'Usuario con acceso a lectura y reservas');

-- 2. Insertar Usuarios de prueba
INSERT INTO usuario (id_rol, nombre_completo, credenciales, estado) VALUES
                                                                        (1, 'Administrador Sistema', 'admin@bibliosmart.com:admin123', 'ACTIVO'),
                                                                        (2, 'Carlos Bibliotecario', 'carlos@bibliosmart.com:biblio123', 'ACTIVO'),
                                                                        (3, 'Ana Torres', 'ana.torres@gmail.com:user123', 'ACTIVO');

-- 3. Insertar Perfil de Lector
INSERT INTO lector (id_usuario, nro_lector, datos_contacto, fecha_registro) VALUES
    (3, 'LEC-2026-001', 'Tel: +591 70000000', '2026-01-15');

-- 4. Insertar Editoriales
INSERT INTO editorial (nombre) VALUES
                                   ('O''Reilly Media'),
                                   ('Pearson Education'),
                                   ('Editorial Planeta');

-- 5. Insertar Autores (Nueva tabla)
INSERT INTO autor (nombre, nacionalidad) VALUES
                                             ('Robert C. Martin', 'Estadounidense'),
                                             ('Andrew S. Tanenbaum', 'Estadounidense');

-- 6. Insertar Libros (Sin autor directo)
INSERT INTO libro (id_editorial, titulo, categoria, anio_publicacion) VALUES
                                                                          (2, 'Clean Code', 'Ingeniería de Software', 2008),
                                                                          (1, 'Sistemas Distribuidos', 'Tecnología', 2023);

-- 7. Vincular Libros con Autores
INSERT INTO libro_autor (id_libro, id_autor) VALUES
                                                 (1, 1),
                                                 (2, 2);

-- 8. Insertar Ejemplares Físicos (Nueva tabla)
INSERT INTO ejemplar (id_libro, codigo_barras, estado) VALUES
                                                           (1, 'CC-2008-001', 'DISPONIBLE'),
                                                           (1, 'CC-2008-002', 'PRESTADO'),
                                                           (2, 'SD-2023-001', 'DISPONIBLE');

-- 9. Insertar Reserva de prueba
INSERT INTO reserva (id_lector, id_libro, fecha_reserva, fecha_limite_retiro, estado) VALUES
    (1, 1, '2026-09-25', '2026-09-28', 'PENDIENTE');

-- 10. Insertar registro de Auditoría de prueba
INSERT INTO auditoria (entidad_afectada, id_registro, accion, usuario_responsable) VALUES
    ('libro', 1, 'CREACION', 'admin@bibliosmart.com');