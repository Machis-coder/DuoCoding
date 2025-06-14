INSERT INTO subject (name, description) VALUES ('Java', 'Asignatura de java for noobs');

INSERT INTO test (id, name, description, subject_id) VALUES (1, 'Examen 1', 'Este es el mejor examen', 1);

INSERT INTO question (id, type, description, answer) VALUES (1, 'MONOSELECTION', '¿Qué es Java?', '3');
INSERT INTO test_question (id, question_id, test_id) VALUES (1, 1, 1);
INSERT INTO response (id, description, response_order, question_id) VALUES (1, 'una lavadora', 1, 1);
INSERT INTO response (id, description, response_order, question_id) VALUES (2, 'un coche', 2, 1);
INSERT INTO response (id, description, response_order, question_id) VALUES (3, 'un lenguaje de programación', 3, 1);
INSERT INTO response (id, description, response_order, question_id) VALUES (4, 'un perro', 4, 1);

INSERT INTO question (id, type, description, answer) VALUES (2, 'MULTISELECTION', 'SELECCIONE LAS RESPUESTAS CORRECTAS', '2,3');
INSERT INTO test_question (id, question_id, test_id) VALUES (2, 2, 1);
INSERT INTO response (id, description, response_order, question_id) VALUES (5, 'Java genera código máquina', 1, 2);
INSERT INTO response (id, description, response_order, question_id) VALUES (6, 'Java genera código objeto', 2, 2);
INSERT INTO response (id, description, response_order, question_id) VALUES (7, 'Java todos los objetos son punteros', 3, 2);
INSERT INTO response (id, description, response_order, question_id) VALUES (8, 'Java se ejecuta en el hardware de la máquina', 4, 2);

INSERT INTO question (id, type, description, answer) VALUES (3, 'GAPS', 'Spring es un {0}, y springboot es un tipo de {1}', 'framework,arquitectura');
INSERT INTO test_question (id, question_id, test_id) VALUES (3, 3, 1);

INSERT INTO question (id, type, description, answer) VALUES (4, 'FREETEXT', '¿Cual fue la primera plataforma en la que se usó java?', 'lavadora');
INSERT INTO test_question (id, question_id, test_id) VALUES (4, 4, 1);

INSERT INTO question (id, type, description, answer) VALUES (5, 'CODE', 'Implemente el algoritmo que calcula la letra de un dni, ejecútelo para el dni 12345678', 'z');
