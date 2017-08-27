CREATE DATABASE IF NOT EXISTS apetitoso;
USE apetitoso;

CREATE TABLE IF NOT EXISTS people(
	id_people int not null auto_increment,
    name_people varchar(255) not null,
    cellphone_people varchar(11) not null,
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

CREATE TABLE IF NOT EXISTS product_type(
	id_product_type int not null auto_increment,
    name_product_type varchar(255) not null,
    CONSTRAINT PRK_ID_PRODUCT_TYPE PRIMARY KEY (id_product_type)
);

CREATE TABLE IF NOT EXISTS ingredient(
	id_ingredient int not null,
    name_ingredient varchar(255) not null,
    status_ingredient char(1) not null,
	CONSTRAINT PRK_ID_INGREDIENT PRIMARY KEY (id_ingredient)
);

CREATE TABLE IF NOT EXISTS product(
	id_product int not null auto_increment,
    name_product varchar(255) not null,
    final_price_product decimal(15,2) not null,
    weight_product float not null,
    status_product char(1) not null,
	id_product_type int not null,
    CONSTRAINT PRK_ID_PRODUCT PRIMARY KEY (id_product),
	CONSTRAINT FRK_PRODUCT_TYPE FOREIGN KEY (id_product_type) REFERENCES product_type(id_product_type)
);

CREATE TABLE IF NOT EXISTS product_ingredient(
	id_product_ingredient int not null,
    id_product int not null,
	id_ingredient int not null,
    CONSTRAINT PRK_ID_PRODUCT_INGREDIENT PRIMARY KEY (id_product_ingredient),
	CONSTRAINT FRK_INGREDIENT FOREIGN KEY (id_ingredient) REFERENCES ingredient(id_ingredient),
    CONSTRAINT FRK_PRODUCT FOREIGN KEY (id_product) REFERENCES product(id_product)
);

/* CREATE TABLE IF NOT EXISTS order_(
	id_product_ingredient int not null
); */

INSERT INTO people (name_people, cellphone_people) VALUES ('Administrador', '199XXXXYYYY');
INSERT INTO employee VALUES('Gerente', 1);
INSERT INTO user VALUES ('admin', MD5('admin'), 1);
