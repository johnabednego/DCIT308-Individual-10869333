CREATE DATABASE inventory_db;
USE inventory_db;

CREATE TABLE items (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    category VARCHAR(100) NOT NULL,
    quantity INT NOT NULL,
    vendor VARCHAR(100) NOT NULL
);

CREATE TABLE vendors (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    address VARCHAR(200) NOT NULL,
    contact VARCHAR(100) NOT NULL
);
CREATE USER 'abednego'@'localhost' IDENTIFIED BY '10869333';
GRANT ALL PRIVILEGES ON inventory_db.* TO 'abednego'@'localhost';
FLUSH PRIVILEGES;

SELECT @@hostname;
SHOW GLOBAL VARIABLES LIKE 'PORT';




