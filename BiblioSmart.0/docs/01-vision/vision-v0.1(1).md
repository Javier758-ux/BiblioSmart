# Visión v0.1 — BiblioSmart

## 1. Contexto

Una biblioteca académica desea digitalizar la gestión de su catálogo bibliográfico, ejemplares físicos, usuarios, préstamos, devoluciones, reservas y sanciones operativas mediante una plataforma única.

## 2. Problema

Actualmente se confunden los títulos bibliográficos con los ejemplares físicos y no existe trazabilidad fiable de quién tiene cada copia. Esto dificulta mantener una representación clara y confiable de la disponibilidad y de la situación de los préstamos.

## 3. Objetivo

Construir un sistema web y móvil que gestione el catálogo bibliográfico y los ejemplares de forma individual, controle disponibilidad, préstamos, renovaciones y reservas, y entregue a cada usuario una vista clara de su situación.

## 4. Actores

### Bibliotecario

Administra catálogo, ejemplares, préstamos y devoluciones.

### Administrador

Gestiona parámetros, usuarios y reportes.

### Lector

Consulta catálogo, reserva y revisa sus préstamos.

### Supervisor

Consulta mora, sanciones y estadísticas.

## 5. Alcance del MVP

1. Gestión de usuarios.
2. Gestión de autores y editoriales.
3. Catálogo bibliográfico.
4. Gestión de ejemplares.
5. Préstamos.
6. Devoluciones.
7. Reservas.
8. Renovaciones.
9. Sanciones.
10. Dashboard y auditoría.

El equipo puede mejorar la experiencia de usuario o agregar facilidades, pero no puede eliminar componentes del alcance funcional cerrado sin autorización docente.

## 6. Exclusiones

La ficha oficial de PA-12 no establece una lista concreta de exclusiones funcionales. Para evitar inventar requisitos, esta versión v0.1 considera fuera del MVP cualquier funcionalidad adicional que no forme parte del alcance funcional cerrado, salvo autorización docente.

Las exclusiones adicionales que el equipo proponga deberán documentarse como supuestos o decisiones del proyecto y validarse con el docente. El uso de Spring AI queda limitado a resumir novedades del catálogo o responder preguntas sobre disponibilidad con datos internos; no puede inventar títulos ni estados ni sustituir reglas deterministas.

## 7. Éxito inicial del proyecto

El proyecto será exitoso cuando pueda demostrar, con datos persistidos, un flujo coherente en el que el bibliotecario registra un título y sus ejemplares, el lector consulta disponibilidad y realiza una reserva, el bibliotecario entrega un ejemplar y se crea el préstamo, el lector consulta su fecha de devolución desde móvil, el bibliotecario registra la devolución y el sistema libera el ejemplar o lo asigna según la cola de reservas. La aplicación web y la aplicación móvil deberán consumir el mismo backend.
