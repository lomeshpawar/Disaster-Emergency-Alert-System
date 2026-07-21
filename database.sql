

USE disasterdb;

CREATE TABLE alerts(
    id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255),
    description VARCHAR(255),
    location VARCHAR(255),
    category VARCHAR(255)
);

CREATE TABLE reports(
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255),
    message VARCHAR(255)
);