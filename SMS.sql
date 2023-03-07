USE master
GO

DROP DATABASE SMS
GO

CREATE DATABASE SMS
GO

USE SMS
GO

CREATE TABLE Customer (
	customer_id		INT				PRIMARY KEY,
	customer_name	NVARCHAR(50)							NOT NULL,
);

CREATE TABLE Employee (
	employee_id		INT				PRIMARY KEY,
	employee_name	NVARCHAR(50)							NOT NULL,
	salary			MONEY			CHECK(salary > 0)		NOT NULL,
	supervisor_id	INT										NOT NULL,
);

CREATE TABLE Product (
	product_id		INT				PRIMARY KEY,
	product_name	NVARCHAR(50)							NOT NULL,
	list_price		MONEY			CHECK(list_price > 0)	NOT NULL,
);

CREATE TABLE Orders (
	order_id		INT				PRIMARY KEY,
	order_date		DATE									NOT NULL,
	customer_id		INT				FOREIGN KEY				REFERENCES	Customer(customer_id),
	employee_id		INT				FOREIGN KEY				REFERENCES	Employee(employee_id),
	total			INT										NOT NULL,
);

CREATE TABLE LineItem (
	order_id		INT				FOREIGN KEY				REFERENCES	Orders(order_id),
	product_id		INT				FOREIGN KEY				REFERENCES	Product(product_id),
	quantity		INT										NOT NULL,
	price			MONEY			CHECK(price> 0)			NOT NULL,
	CONSTRAINT		PK_LineItem		PRIMARY KEY (order_id, product_id),
);
GO