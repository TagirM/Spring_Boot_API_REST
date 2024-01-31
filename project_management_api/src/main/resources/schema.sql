drop table if exists users;
create table users
(
    id           INT         NOT NULL AUTO_INCREMENT PRIMARY KEY,
    username  VARCHAR(50) NOT NULL,
    email       VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(50) NOT NULL,
    role VARCHAR(50) NOT NULL
);
drop table if exists projects;
create table projects
(
    id           INT         NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE ,
    description  VARCHAR(500) NOT NULL,
    created_date DATE
);
drop table if exists user_project;
create table user_project
(
    id           INT         NOT NULL AUTO_INCREMENT PRIMARY KEY,
    related_entity_id INT NOT NULL
--     projectId  INT         NOT NULL,
--     userId       INT NOT NULL
);