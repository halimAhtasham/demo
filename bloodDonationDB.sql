create database blood_donation;
use blood_donation;

CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_name VARCHAR(100) NOT NULL,
    password INT NOT NULL,
    city VARCHAR(100) NOT NULL,
    age FLOAT NOT NULL,
    phone VARCHAR(20) NOT NULL,
    blood_group VARCHAR(10) NOT NULL
);
ALTER TABLE users CHANGE COLUMN blood_group bloodGroup VARCHAR(10);
ALTER TABLE users MODIFY COLUMN id INT;
ALTER TABLE users MODIFY COLUMN id INT AUTO_INCREMENT PRIMARY KEY;
ALTER TABLE users DROP PRIMARY KEY;
RENAME TABLE users TO user;
DELETE FROM user WHERE id = 2;

ALTER TABLE user 
DROP COLUMN userName,
DROP COLUMN bloodGroup;

ALTER TABLE user ADD UNIQUE (user_name);

select * from user;
show tables;
DROP TABLE user;
INSERT INTO users (id,userName, password, city, age, phone, bloodGroup)
VALUES (2,'Alice', 123456, 'Los Angeles', 28, '01795420254', 'A+');