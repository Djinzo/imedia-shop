CREATE TABLE stock (
                       id bigint PRIMARY KEY,
                       sku VARCHAR(16) NOT NULL,
                       quantity INT NOT NULL,
                       warehouse varchar(16),
                       FOREIGN KEY (sku) REFERENCES products(sku)
);
