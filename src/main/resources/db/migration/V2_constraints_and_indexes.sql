-- make the product name unique
ALTER TABLE products
ADD CONSTRAINT uk_products_name UNIQUE (name);

-- create indexes on stock_movements for product_id and warehouse_id
CREATE INDEX idx_stock_product ON stock_movements (product_id);
CREATE INDEX idx_stock_warehouse ON stock_movements (warehouse_id);





