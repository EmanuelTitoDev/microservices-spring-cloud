-- Scripts para inicializar la base de datos de Products Service (products_db)
-- Tabla: productos

USE products_db;
GO

INSERT INTO productos (codigo, nombre, marca, precio_individual) VALUES
('PROD000001', 'Smartphone Galaxy S23', 'Samsung', 799.99),
('PROD000002', 'iPhone 15 Pro', 'Apple', 999.99),
('PROD000003', 'Laptop ThinkPad X1', 'Lenovo', 1499.00),
('PROD000004', 'Monitor 27 Pulgadas 4K', 'LG', 349.50),
('PROD000005', 'Teclado Mecanico MX', 'Logitech', 109.99),
('PROD000006', 'Auriculares Inalambricos', 'Sony', 199.90),
('PROD000007', 'Smartwatch Series 8', 'Apple', 399.00),
('PROD000008', 'Tablet Galaxy Tab S8', 'Samsung', 649.00),
('PROD000009', 'Raton Inalambrico', 'Microsoft', 29.99),
('PROD000010', 'Camara Web 1080p', 'Logitech', 59.99);
GO
