ALTER TABLE products
    ADD created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    ADD updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
        ON UPDATE CURRENT_TIMESTAMP;

ALTER TABLE warehouses
    ADD created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP;

ALTER TABLE suppliers
    ADD created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP;

ALTER TABLE stock_movements
    ADD created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP;


ALTER TABLE products
ADD CONSTRAINT chk_products_price_positive CHECK (price >= 0);

ALTER TABLE stock_movements
ADD CONSTRAINT chk_stock_quantity_non_zero CHECK (quantity <> 0);

ALTER TABLE stock_movements
MODIFY status ENUM ('PENDING', 'IN', 'OUT' , 'CANCELLED') NOT NULL;