CREATE TABLE lectors (
                         id BIGSERIAL PRIMARY KEY,
                         name VARCHAR(100) NOT NULL,
                         salary DOUBLE PRECISION NOT NULL,
                         degree VARCHAR(50) NOT NULL
);

CREATE TABLE departments (
                             id BIGSERIAL PRIMARY KEY,
                             name VARCHAR(100) NOT NULL UNIQUE,
                             head_of_department_id BIGINT,
                             CONSTRAINT fk_head_of_department
                                 FOREIGN KEY (head_of_department_id)
                                     REFERENCES lectors(id)
);

CREATE TABLE department_lector (
                                   department_id BIGINT NOT NULL,
                                   lector_id BIGINT NOT NULL,
                                   PRIMARY KEY (department_id, lector_id),
                                   CONSTRAINT fk_department
                                       FOREIGN KEY (department_id)
                                           REFERENCES departments(id),
                                   CONSTRAINT fk_lector
                                       FOREIGN KEY (lector_id)
                                           REFERENCES lectors(id)
);

INSERT INTO lectors (name, salary, degree) VALUES ('John Doe', 1200.00, 'PROFESSOR');
INSERT INTO lectors (name, salary, degree) VALUES ('Jane Smith', 1100.00, 'ASSOCIATE_PROFESSOR');
INSERT INTO lectors (name, salary, degree) VALUES ('Michael Johnson', 1000.00, 'ASSISTANT');
INSERT INTO lectors (name, salary, degree) VALUES ('Emily Davis', 950.00, 'PROFESSOR');
INSERT INTO lectors (name, salary, degree) VALUES ('William Brown', 1300.00, 'PROFESSOR');
INSERT INTO lectors (name, salary, degree) VALUES ('Emma Wilson', 1250.00, 'ASSOCIATE_PROFESSOR');
INSERT INTO lectors (name, salary, degree) VALUES ('Oliver Taylor', 900.00, 'ASSISTANT');
INSERT INTO lectors (name, salary, degree) VALUES ('Sophia Moore', 980.00, 'PROFESSOR');
INSERT INTO lectors (name, salary, degree) VALUES ('Liam Harris', 1150.00, 'ASSOCIATE_PROFESSOR');
INSERT INTO lectors (name, salary, degree) VALUES ('Isabella Martin', 1020.00, 'ASSISTANT');

INSERT INTO departments (name, head_of_department_id) VALUES ('Engineering', 1);
INSERT INTO departments (name, head_of_department_id) VALUES ('Mathematics', 2);
INSERT INTO departments (name, head_of_department_id) VALUES ('Physics', 3);
INSERT INTO departments (name, head_of_department_id) VALUES ('Chemistry', 4);
INSERT INTO departments (name, head_of_department_id) VALUES ('Biology', 5);
INSERT INTO departments (name, head_of_department_id) VALUES ('Computer Science', 6);
INSERT INTO departments (name, head_of_department_id) VALUES ('Philosophy', 7);
INSERT INTO departments (name, head_of_department_id) VALUES ('Literature', 8);
INSERT INTO departments (name, head_of_department_id) VALUES ('History', 9);
INSERT INTO departments (name, head_of_department_id) VALUES ('Economics', 10);


INSERT INTO department_lector (department_id, lector_id) VALUES (1, 1);
INSERT INTO department_lector (department_id, lector_id) VALUES (1, 2);
INSERT INTO department_lector (department_id, lector_id) VALUES (2, 3);
INSERT INTO department_lector (department_id, lector_id) VALUES (3, 4);
INSERT INTO department_lector (department_id, lector_id) VALUES (4, 5);
INSERT INTO department_lector (department_id, lector_id) VALUES (5, 6);
INSERT INTO department_lector (department_id, lector_id) VALUES (6, 7);
INSERT INTO department_lector (department_id, lector_id) VALUES (7, 8);
INSERT INTO department_lector (department_id, lector_id) VALUES (8, 9);
INSERT INTO department_lector (department_id, lector_id) VALUES (9, 10);
INSERT INTO department_lector (department_id, lector_id) VALUES (10, 1);
INSERT INTO department_lector (department_id, lector_id) VALUES (10, 2);
INSERT INTO department_lector (department_id, lector_id) VALUES (10, 3);
INSERT INTO department_lector (department_id, lector_id) VALUES (10, 4);
INSERT INTO department_lector (department_id, lector_id) VALUES (10, 5);
