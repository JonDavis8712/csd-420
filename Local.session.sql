SELECT DATABASE();
USE databasedb;
SELECT DATABASE();
REVOKE ALL PRIVILEGES ON *.* FROM 'student1'@'localhost';
GRANT ALL PRIVILEGES ON databasedb.* TO 'student1'@'localhost';
FLUSH PRIVILEGES;
