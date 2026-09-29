-- ROLES
INSERT INTO rol (nombre_rol, descripcion)
SELECT 'LECTOR', 'Usuario lector de la biblioteca'
    WHERE NOT EXISTS (
    SELECT 1
    FROM rol
    WHERE nombre_rol = 'LECTOR'
);

INSERT INTO rol (nombre_rol, descripcion)
SELECT 'BIBLIOTECARIO', 'Gestiona préstamos, devoluciones y reservas'
    WHERE NOT EXISTS (
    SELECT 1
    FROM rol
    WHERE nombre_rol = 'BIBLIOTECARIO'
);

INSERT INTO rol (nombre_rol, descripcion)
SELECT 'ADMINISTRADOR', 'Administra el sistema'
    WHERE NOT EXISTS (
    SELECT 1
    FROM rol
    WHERE nombre_rol = 'ADMINISTRADOR'
);


-- EDITORIAL
INSERT INTO editorial (nombre)
SELECT 'Editorial Demo'
    WHERE NOT EXISTS (
    SELECT 1
    FROM editorial
    WHERE nombre = 'Editorial Demo'
);


-- AUTOR
INSERT INTO autor (nombre)
SELECT 'Autor Demo'
    WHERE NOT EXISTS (
    SELECT 1
    FROM autor
    WHERE nombre = 'Autor Demo'
);


-- LIBRO
INSERT INTO libro (
    titulo,
    isbn,
    categoria,
    anio_publicacion,
    id_editorial
)
SELECT
    'Libro Demo BiblioSmart',
    '9780000000001',
    'Tecnologia',
    2026,
    e.id_editorial
FROM editorial e
WHERE e.nombre = 'Editorial Demo'
  AND NOT EXISTS (
    SELECT 1
    FROM libro
    WHERE isbn = '9780000000001'
);


-- RELACION LIBRO - AUTOR
INSERT INTO libro_autor (
    id_libro,
    id_autor
)
SELECT
    l.id_libro,
    a.id_autor
FROM libro l
         CROSS JOIN autor a
WHERE l.isbn = '9780000000001'
  AND a.nombre = 'Autor Demo'
  AND NOT EXISTS (
    SELECT 1
    FROM libro_autor la
    WHERE la.id_libro = l.id_libro
      AND la.id_autor = a.id_autor
);


-- EJEMPLAR
INSERT INTO ejemplar (
    codigo,
    estado,
    id_libro
)
SELECT
    'DEMO-001',
    'DISPONIBLE',
    l.id_libro
FROM libro l
WHERE l.isbn = '9780000000001'
  AND NOT EXISTS (
    SELECT 1
    FROM ejemplar
    WHERE codigo = 'DEMO-001'
);