INSERT INTO user (name, surname, email, username, password, birthday, dni, role, active)
VALUES ('SuperAdmin', 'SuperAdmin', 'super@admin.com', 'super', SHA2('super', 256), '1990-01-01 00:00:00', '12345678X', 'SUPER', TRUE);

INSERT INTO user (name, surname, email, username, password, birthday, dni, role, active)
VALUES ('Admin', 'Admin', 'admin@admin.com', 'admin', SHA2('admin', 256), '1990-01-01 00:00:00', '12345678X', 'ADMIN', TRUE);


INSERT INTO user (name, surname, email, username, password, birthday, dni, role, active)
VALUES ('Juan', 'Pérez', 'juan.perez@example.com', 'juanp', SHA2('1234', 256), '1990-01-01 00:00:00', '12345678X', 'PUPIL', TRUE);
