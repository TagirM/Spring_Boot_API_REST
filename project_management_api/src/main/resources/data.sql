INSERT INTO users (username, email, password, role)
VALUES ('John', 'john@gmail.com', '123456', 'ADMIN');
INSERT INTO users (username, email, password, role)
VALUES ('Jane', 'jane@gmail.com', '654321', 'USER');
INSERT INTO projects (name, description, created_date)
VALUES ('mobile_bank', 'project to create new mobile_bank', NOW());
INSERT INTO projects (name, description, created_date)
VALUES ('note', 'convenient notebook for everyday tasks', NOW());