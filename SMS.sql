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

/*
INSERT INTO Customer(customer_id, customer_name) VALUES (1, 'Jonah Aguilar');
INSERT INTO Customer(customer_id, customer_name) VALUES (2, 'Leanna Collier');
INSERT INTO Customer(customer_id, customer_name) VALUES (3, 'Felicity Griffith');
INSERT INTO Customer(customer_id, customer_name) VALUES (4, 'Evelyn Saunders');
INSERT INTO Customer(customer_id, customer_name) VALUES (5, 'Khloe Deleon');
INSERT INTO Customer(customer_id, customer_name) VALUES (6, 'Terrance Daniel');
GO
*/

/*
INSERT INTO Employee(employee_id, employee_name, salary, supervisor_id) VALUES (001, 'Erika Sellers', 1000, 1001);
INSERT INTO Employee(employee_id, employee_name, salary, supervisor_id) VALUES (002, 'Dahlia Cooley', 1000, 1002);
INSERT INTO Employee(employee_id, employee_name, salary, supervisor_id) VALUES (003, 'Ana Clark', 1000, 1003);
INSERT INTO Employee(employee_id, employee_name, salary, supervisor_id) VALUES (004, 'Krista Mcmahon', 1000, 1004);
INSERT INTO Employee(employee_id, employee_name, salary, supervisor_id) VALUES (005, 'Roselyn Khan', 1000, 1005);
GO
*/

/*
INSERT INTO Product(product_id, product_name, list_price) VALUES (01, 'Allure Kit', 300);
INSERT INTO Product(product_id, product_name, list_price) VALUES (02, 'Swish Wallet', 100);
INSERT INTO Product(product_id, product_name, list_price) VALUES (03, 'Uno Wear', 200);
INSERT INTO Product(product_id, product_name, list_price) VALUES (04, 'Mono', 230);
INSERT INTO Product(product_id, product_name, list_price) VALUES (05, 'Handy Mop', 120);
INSERT INTO Product(product_id, product_name, list_price) VALUES (06, 'Villafy', 111);
INSERT INTO Product(product_id, product_name, list_price) VALUES (07, 'Gymr Kit', 320);
GO
*/

/*
INSERT INTO Orders(order_id, order_date, customer_id, employee_id, total) VALUES (301, '2022-03-02', 1, 001, 0);
INSERT INTO Orders(order_id, order_date, customer_id, employee_id, total) VALUES (302, '2022-02-01', 2, 002, 0);
INSERT INTO Orders(order_id, order_date, customer_id, employee_id, total) VALUES (303, '2022-04-12', 3, 002, 0);
INSERT INTO Orders(order_id, order_date, customer_id, employee_id, total) VALUES (304, '2022-12-02', 4, 003, 0);
INSERT INTO Orders(order_id, order_date, customer_id, employee_id, total) VALUES (305, '2022-04-01', 5, 004, 0);
INSERT INTO Orders(order_id, order_date, customer_id, employee_id, total) VALUES (306, '2022-11-12', 6, 004, 0);
GO
*/

/*
INSERT INTO LineItem(order_id, product_id, quantity, price) VALUES (301, 01, 2, 300);
INSERT INTO LineItem(order_id, product_id, quantity, price) VALUES (302, 02, 3, 100);
INSERT INTO LineItem(order_id, product_id, quantity, price) VALUES (303, 03, 1, 200);
INSERT INTO LineItem(order_id, product_id, quantity, price) VALUES (304, 04, 2, 230);
INSERT INTO LineItem(order_id, product_id, quantity, price) VALUES (305, 05, 4, 120);
INSERT INTO LineItem(order_id, product_id, quantity, price) VALUES (306, 06, 5, 111);
GO
*/

/*
SELECT * FROM Orders;
GO

SELECT * FROM Orders WHERE customer_id = 1;
GO

SELECT SUM(price * quantity) AS OrderTotal FROM LineItem WHERE order_id = 301;
GO
*/

--UPDATE Orders SET total = (SELECT SUM(price * quantity) AS OrderTotal FROM LineItem WHERE order_id = 301 GROUP BY order_id) WHERE order_id = 301
--GO