CREATE TABLE brands (
    id BIGINT GENERATED ALWAYS AS IDENTITY (START WITH 1 INCREMENT BY 1 MINVALUE 1),
    name VARCHAR,
    CONSTRAINT pk_brands PRIMARY KEY (id)
);

CREATE TABLE prices (
    price_list BIGINT GENERATED ALWAYS AS IDENTITY (START WITH 1 INCREMENT BY 1 MINVALUE 1),
    brand_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    start_date TIMESTAMP WITH TIME ZONE NOT NULL,
    end_date TIMESTAMP WITH TIME ZONE NOT NULL,
    price NUMERIC(20, 2) NOT NULL,
    curr CHAR(3) NOT NULL,
    priority INT NOT NULL DEFAULT 0,
    CONSTRAINT pk_prices PRIMARY KEY (price_list),
    CONSTRAINT fk_prices_brands FOREIGN KEY (brand_id) REFERENCES brands (id),
    CONSTRAINT chk_prices_dates CHECK (end_date > start_date),
    CONSTRAINT chk_prices_price_positive CHECK (price > 0),
    CONSTRAINT chk_prices_priority_non_negative CHECK (priority >= 0),
    CONSTRAINT chk_prices_curr_uppercase CHECK (curr = UPPER(curr))
);

CREATE INDEX idx_prices_product_dates ON prices (product_id, start_date, end_date);
CREATE INDEX idx_prices_brand_dates ON prices (brand_id, start_date, end_date);
CREATE INDEX idx_prices_priority ON prices (priority DESC);