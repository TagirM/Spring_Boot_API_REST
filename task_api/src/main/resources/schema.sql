drop table if exists tasks;
create table tasks (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    description VARCHAR(50) NOT NULL,
    status VARCHAR(50) NOT NULL,
    created_time Timestamp
);