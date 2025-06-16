CREATE TABLE subject (
                         id BIGINT PRIMARY KEY AUTO_INCREMENT,
                         name VARCHAR(255) NOT NULL,
                         description TEXT,
                         active boolean
);

CREATE TABLE user (
                      id BIGINT PRIMARY KEY AUTO_INCREMENT,
                      name VARCHAR(255) NOT NULL,
                      surname VARCHAR(255),
                      email VARCHAR(255) UNIQUE NOT NULL,
                      username VARCHAR(255) UNIQUE NOT NULL,
                      password VARCHAR(255) NOT NULL,
                      birthday DATE,
                      dni VARCHAR(20),
                      role VARCHAR(50),
                      active BOOLEAN
);

CREATE TABLE question (
                          id BIGINT PRIMARY KEY AUTO_INCREMENT,
                          type VARCHAR(50) NOT NULL,
                          description TEXT NOT NULL,
                          answer TEXT,
                          active boolean
);

CREATE TABLE response (
                          id BIGINT PRIMARY KEY AUTO_INCREMENT,
                          description TEXT NOT NULL,
                          response_order INT NOT NULL,
                          question_id BIGINT,
                          active booolean,
                          FOREIGN KEY (question_id) REFERENCES question(id) ON DELETE CASCADE
);

CREATE TABLE test (
                      id BIGINT PRIMARY KEY AUTO_INCREMENT,
                      name VARCHAR(255) NOT NULL,
                      description TEXT,
                      subject_id BIGINT,
                      active boolean,
                      FOREIGN KEY (subject_id) REFERENCES subject(id) ON DELETE SET NULL
);

CREATE TABLE test_execution (
                                id BIGINT PRIMARY KEY AUTO_INCREMENT,
                                test_id BIGINT,
                                user_id BIGINT,
                                date DATE,
                                start_time DATETIME,
                                finish_time DATETIME,
                                result FLOAT,
                                notes TEXT,
                                active boolean
                                FOREIGN KEY (test_id) REFERENCES test(id) ON DELETE CASCADE,
                                FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE CASCADE
);

CREATE TABLE test_execution_response (
                                         id BIGINT PRIMARY KEY AUTO_INCREMENT,
                                         test_execution_id BIGINT,
                                         question_id BIGINT,
                                         response_id BIGINT,
                                         answer TEXT,
                                         correct BOOLEAN,
                                         notes TEXT,
                                         boolean active,
                                         FOREIGN KEY (test_execution_id) REFERENCES test_execution(id) ON DELETE CASCADE,
                                         FOREIGN KEY (question_id) REFERENCES question(id) ON DELETE CASCADE,
                                         FOREIGN KEY (response_id) REFERENCES response(id) ON DELETE SET NULL
);

CREATE TABLE test_question (
                               id BIGINT PRIMARY KEY AUTO_INCREMENT,
                               test_id BIGINT,
                               question_id BIGINT,
                               FOREIGN KEY (test_id) REFERENCES test(id) ON DELETE CASCADE,
                               FOREIGN KEY (question_id) REFERENCES question(id) ON DELETE CASCADE
);

CREATE TABLE test_subject (
                              id BIGINT PRIMARY KEY AUTO_INCREMENT,
                              test_id BIGINT,
                              subject_id BIGINT,
                              FOREIGN KEY (test_id) REFERENCES test(id) ON DELETE CASCADE,
                              FOREIGN KEY (subject_id) REFERENCES subject(id) ON DELETE CASCADE
);

CREATE TABLE user_subject (
                              id BIGINT PRIMARY KEY AUTO_INCREMENT,
                              user_id BIGINT,
                              subject_id BIGINT,
                              FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE CASCADE,
                              FOREIGN KEY (subject_id) REFERENCES subject(id) ON DELETE CASCADE
);
