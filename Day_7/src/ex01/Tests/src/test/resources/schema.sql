DROP SCHEMA IF EXISTS Products CASCADE;

CREATE SCHEMA Products;

CREATE TABLE IF NOT EXISTS Products.Inventory (
    ID integer PRIMARY KEY,
	Name varchar(50),
	Price numeric
);
