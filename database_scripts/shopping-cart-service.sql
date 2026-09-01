-- Scripts para inicializar la base de datos de Shopping Cart Service (shopping_cart_db)
-- Tablas: carritos_compras, items_carrito

USE shopping_cart_db;
GO

-- Insertar Carritos
INSERT INTO carritos_compras (precio_total) VALUES
(2148.99), -- Carrito 1: (799.99 * 1) + (1499.00 * 1)
(649.00);  -- Carrito 2: (649.00 * 1)
GO

-- Insertar Items del Carrito (Asumiendo que el Carrito 1 tiene ID 1 y el Carrito 2 tiene ID 2)
-- Carrito 1: Smartphone y Laptop
INSERT INTO items_carrito (carrito_id, codigo_producto, nombre_producto, marca_producto, precio_unitario, cantidad, subtotal) VALUES
(1, 'PROD000001', 'Smartphone Galaxy S23', 'Samsung', 799.99, 1, 799.99),
(1, 'PROD000003', 'Laptop ThinkPad X1', 'Lenovo', 1499.00, 1, 1499.00);

-- Carrito 2: Tablet
INSERT INTO items_carrito (carrito_id, codigo_producto, nombre_producto, marca_producto, precio_unitario, cantidad, subtotal) VALUES
(2, 'PROD000008', 'Tablet Galaxy Tab S8', 'Samsung', 649.00, 1, 649.00);
GO
