-- Scripts para inicializar la base de datos de Sales Service (sales_db)
-- Tabla: ventas

USE sales_db;
GO

-- Insertar Ventas (Asumiendo que los carritos 1 y 2 existen en Shopping Cart Service)
INSERT INTO ventas (fecha, id_carrito) VALUES
(GETDATE(), 1),
(GETDATE(), 2);
GO
