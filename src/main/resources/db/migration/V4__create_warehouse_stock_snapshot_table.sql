-- 1. Create the Snapshot Table
CREATE TABLE warehouse_stock (
                                 id INT AUTO_INCREMENT PRIMARY KEY,
                                 product_id INT NOT NULL,
                                 warehouse_id INT NOT NULL,
                                 available_quantity INT NOT NULL DEFAULT 0,
                                 last_updated TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

                                 UNIQUE KEY uk_product_warehouse (product_id, warehouse_id),
                                 CONSTRAINT fk_stock_product FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE CASCADE,
                                 CONSTRAINT fk_stock_warehouse FOREIGN KEY (warehouse_id) REFERENCES warehouses(id) ON DELETE CASCADE
);

-- 2. Performance Index
CREATE INDEX idx_stock_lookup ON warehouse_stock(product_id, warehouse_id);

-- 3. Initial Sync (Seed data based on history) with a strong foreign key constraints
INSERT INTO warehouse_stock (product_id, warehouse_id, available_quantity)
SELECT
    product_id,
    warehouse_id,
    SUM(CASE WHEN status = 'IN' THEN quantity ELSE -quantity END)
FROM stock_movements
WHERE status IN ('IN', 'OUT')
GROUP BY product_id, warehouse_id;