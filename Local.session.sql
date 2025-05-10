SELECT DATABASE();
USE databasedb;
SELECT DATABASE();
REVOKE ALL PRIVILEGES ON *.* FROM 'student1'@'localhost';
GRANT ALL PRIVILEGES ON databasedb.* TO 'student1'@'localhost';
FLUSH PRIVILEGES;
CREATE TABLE fans (
    ID INT PRIMARY KEY,
    firstname VARCHAR(25),
    lastname VARCHAR(25),
    favoriteteam VARCHAR(25),
);

INSERT INTO fans (ID, firstname, lastname, favoriteteam) VALUES (1, 'LeBron', 'James', 'Lakers');
INSERT INTO fans (ID, firstname, lastname, favoriteteem) VALUES (2, 'Stephen', 'Curry', 'Warriors');