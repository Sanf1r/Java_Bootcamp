DROP SCHEMA IF EXISTS Products CASCADE;

CREATE SCHEMA Products;

CREATE TABLE IF NOT EXISTS Products.Inventory (
    ID integer PRIMARY KEY NOT NULL,
	Name varchar(50) NOT NULL,
	Price numeric NOT NULL
);
