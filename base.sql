CREATE DATABASE IF NOT EXISTS banco_testes;
USE banco_testes;

CREATE TABLE IF NOT EXISTS people(
	id_people int not null auto_increment,
    name_people varchar(255) not null,
    phone_people varchar(11) not null,
    CONSTRAINT PRK_ID_PEOPLE PRIMARY KEY (id_people) 
);

CREATE TABLE IF NOT EXISTS costumer(
	rg varchar(10) not null,
    cpf varchar(11) not null,
    id_people int not null,
    CONSTRAINT UNK_CPF_COSTUMER UNIQUE KEY (cpf),
    CONSTRAINT UNK_RG_COSTUMER UNIQUE KEY (rg),
    CONSTRAINT FRK_ID_COSTUMER FOREIGN KEY (id_people) REFERENCES people(id_people)
);

CREATE TABLE IF NOT EXISTS supplier(
	cnpj varchar(14) not null,
    id_people int not null,
    CONSTRAINT UNK_CNPJ_SUPPLIER UNIQUE KEY (cnpj),
    CONSTRAINT FRK_ID_SUPPLIER FOREIGN KEY (id_people) REFERENCES people(id_people)
);

CREATE TABLE IF NOT EXISTS employee(
	role varchar(255) not null,
    id_people int not null,
    CONSTRAINT FRK_ID_EMPLOYEE FOREIGN KEY (id_people) REFERENCES people(id_people)
);

CREATE TABLE IF NOT EXISTS user(
	login varchar(255) not null,
    password varchar(255) not null,
    id_employee int not null,
    CONSTRAINT UNK_LOGIN_USER UNIQUE KEY (login),
    CONSTRAINT FRK_ID_USER FOREIGN KEY (id_employee) REFERENCES employee(id_people)
);

INSERT INTO people (name_people, phone_people) VALUES ('Administrador', '199XXXXYYYY');
INSERT INTO employee VALUES('Gerente', 1);
INSERT INTO user VALUES ('admin', MD5('admin'), 1);
