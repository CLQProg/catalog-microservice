BEGIN;

DELETE FROM prices;
ALTER TABLE prices ALTER COLUMN price_list RESTART WITH 1;

DELETE FROM brands;
ALTER TABLE brands ALTER COLUMN id RESTART WITH 1;

INSERT INTO brands(name) VALUES ('ZARA');

INSERT INTO prices (brand_id, start_date, product_id, priority, end_date, price, curr)
VALUES (
           1,
           '2020-06-14 00:00:00+00:00',
           35455,
           0,
           '2020-12-31 23:59:59+00:00',
           10.99,
           'EUR'
       ),
       (
           1,
           '2020-06-14 15:00:00+00:00',
           35455,
           1,
           '2020-06-14 18:30:00+00:00',
           15.99,
           'EUR'
       ),
       (
           1,
           '2020-06-15 00:00:00+00:00',
           35455,
           1,
           '2020-06-15 11:00:00+00:00',
           20.99,
           'EUR'
       ),
       (
           1,
           '2020-06-15 16:00:00+00:00',
           35455,
           1,
           '2020-12-31 23:59:59+00:00',
           12.99,
           'EUR'
       );

COMMIT;