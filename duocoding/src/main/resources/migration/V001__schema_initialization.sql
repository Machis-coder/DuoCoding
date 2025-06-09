CREATE DATABASE IF NOT EXISTS duocoding;
USE duocoding;

CREATE TABLE Subject (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    description TEXT
);

CREATE TABLE User (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    surname VARCHAR(255),
    email VARCHAR(255) UNIQUE NOT NULL,
    username VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    birthday DATETIME,
    dni VARCHAR(20),
    role VARCHAR(50),
    active BOOLEAN
);

CREATE TABLE Question (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    type VARCHAR(50) NOT NULL,
    description TEXT NOT NULL,
    answer TEXT
);

CREATE TABLE Response (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    description TEXT NOT NULL,
    `order` INT NOT NULL,
    question_id BIGINT,
    FOREIGN KEY (question_id) REFERENCES Question(id) ON DELETE CASCADE
);

CREATE TABLE Test (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    subject_id BIGINT,
    FOREIGN KEY (subject_id) REFERENCES Subject(id) ON DELETE SET NULL
);

CREATE TABLE TestExecution (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    test_id BIGINT,
    user_id BIGINT,
    date DATETIME,
    timeStart TIME,
    timeFinish TIME,
    result FLOAT,
    notes TEXT,
    FOREIGN KEY (test_id) REFERENCES Test(id) ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES User(id) ON DELETE CASCADE
);

CREATE TABLE TestExecutionResponse (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    execution_id BIGINT,
    question_id BIGINT,
    answer TEXT,
    correct BOOLEAN,
    notes TEXT,
    FOREIGN KEY (execution_id) REFERENCES TestExecution(id) ON DELETE CASCADE,
    FOREIGN KEY (question_id) REFERENCES Question(id) ON DELETE CASCADE
);

CREATE TABLE TestQuestion (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    test_id BIGINT,
    question_id BIGINT,
    FOREIGN KEY (test_id) REFERENCES Test(id) ON DELETE CASCADE,
    FOREIGN KEY (question_id) REFERENCES Question(id) ON DELETE CASCADE
);

CREATE TABLE TestSubject (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    test_id BIGINT,
    subject_id BIGINT,
    FOREIGN KEY (test_id) REFERENCES Test(id) ON DELETE CASCADE,
    FOREIGN KEY (subject_id) REFERENCES Subject(id) ON DELETE CASCADE
);

CREATE TABLE UserSubject (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id BIGINT,
    subject_id BIGINT,
    FOREIGN KEY (user_id) REFERENCES User(id) ON DELETE CASCADE,
    FOREIGN KEY (subject_id) REFERENCES Subject(id) ON DELETE CASCADE
);
