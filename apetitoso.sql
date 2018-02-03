CREATE DATABASE IF NOT EXISTS apetitoso;
USE apetitoso;

CREATE TABLE IF NOT EXISTS address(
  id_address int not null auto_increment,
  street varchar(255) not null,
  number int not null,
  neighborhood varchar(255) not null,
  cep varchar(8) not null,
  CONSTRAINT PRK_ID_ADDRESS PRIMARY KEY (id_address) 
);


CREATE TABLE IF NOT EXISTS person( 
  id_person int not null auto_increment,
  id_address int not null,
  name_person varchar(255) not null,
  status boolean not null,
  CONSTRAINT PRK_ID_PERSON PRIMARY KEY (id_person),
  CONSTRAINT FRK_ID_PERSON_ADDRESS FOREIGN KEY (id_address) REFERENCES address(id_address)
);



CREATE TABLE IF NOT EXISTS phone( 
  id_phone int not null auto_increment,
  phone varchar(12) not null,
  id_person int not null,
  CONSTRAINT PRK_ID_PHONE PRIMARY KEY (id_phone), 
  CONSTRAINT FRK_ID_PHONE_PERSON_PERSON FOREIGN KEY (id_person) REFERENCES person(id_person)
);


CREATE TABLE IF NOT EXISTS costumer(  
  rg varchar(10),
  cpf varchar(11),
  id_person int not null,
  CONSTRAINT UNK_CPF_COSTUMER UNIQUE KEY (cpf),
  CONSTRAINT UNK_RG_COSTUMER UNIQUE KEY (rg),
  CONSTRAINT FRK_ID_COSTUMER FOREIGN KEY (id_person) REFERENCES person(id_person)
);

CREATE TABLE IF NOT EXISTS supplier(
  cnpj varchar(14) not null,
  id_person int not null,
  CONSTRAINT UNK_CNPJ_SUPPLIER UNIQUE KEY (cnpj),
  CONSTRAINT FRK_ID_SUPPLIER FOREIGN KEY (id_person) REFERENCES person(id_person)
);

CREATE TABLE IF NOT EXISTS employee(
  id_employee int not null auto_increment,
  role varchar(255) not null,
  id_person int not null,
  CONSTRAINT PRK_ID_EMPLOYEE PRIMARY KEY (id_employee),
  CONSTRAINT FRK_ID_EMPLOYEE FOREIGN KEY (id_person) REFERENCES person(id_person)
);


/* 4- operador 5- administrador*/
CREATE TABLE IF NOT EXISTS user( 
  login varchar(255) not null,
  password varchar(255) not null,
  id_employee int not null,
  status boolean,
  level int,
  CONSTRAINT UNK_LOGIN_USER UNIQUE KEY (login),
  CONSTRAINT FRK_ID_USER FOREIGN KEY (id_employee) REFERENCES employee(id_employee)
);

CREATE TABLE IF NOT EXISTS product_type(
  id_product_type int not null auto_increment,
  name_product_type varchar(255) not null,
  CONSTRAINT PRK_ID_PRODUCT_TYPE PRIMARY KEY (id_product_type)
);

CREATE TABLE IF NOT EXISTS ingredient(
  id_ingredient int not null AUTO_INCREMENT,
  name_ingredient varchar(255) not null,
  status_ingredient boolean not null,
  price float not null,
  CONSTRAINT PRK_ID_INGREDIENT PRIMARY KEY (id_ingredient)
);

CREATE TABLE IF NOT EXISTS product(
  id_product int not null auto_increment,
  name_product varchar(255) not null,
  final_price_product decimal(15,2) not null,
  weight_product float not null,
  status_product boolean not null,
  id_product_type int not null,
  CONSTRAINT PRK_ID_PRODUCT PRIMARY KEY (id_product),
  CONSTRAINT FRK_PRODUCT_TYPE FOREIGN KEY (id_product_type) REFERENCES product_type(id_product_type)
);

CREATE TABLE IF NOT EXISTS product_ingredient(
  id_product_ingredient int not null AUTO_INCREMENT,
  id_product int not null,
  id_ingredient int not null,
  CONSTRAINT PRK_ID_PRODUCT_INGREDIENT PRIMARY KEY (id_product_ingredient),
  CONSTRAINT FRK_INGREDIENT FOREIGN KEY (id_ingredient) REFERENCES ingredient(id_ingredient),
  CONSTRAINT FRK_PRODUCT FOREIGN KEY (id_product) REFERENCES product(id_product)
);

CREATE TABLE IF NOT EXISTS sale(
  id_sale int not null AUTO_INCREMENT,
  id_user int not null,
  id_costumer int not null,
  sale_time datetime,
  sale_date date,
  sale_time_estimate int not null,
  sale_total decimal(10,2),
  CONSTRAINT PRK_ID_sale PRIMARY KEY (id_sale), 
  CONSTRAINT FRK_ID_COSTUMER_SALE FOREIGN KEY (id_costumer) REFERENCES costumer(id_person),
  CONSTRAINT FRK_ID_EMPLOYEE_SALE FOREIGN KEY (id_user) REFERENCES user(id_employee)
);

CREATE TABLE IF NOT EXISTS items_sale(
  id_items_sale int not null AUTO_INCREMENT,
  id_sale int not null,
  id_product int not null,
  CONSTRAINT PRK_ID_ITEMS_SALE PRIMARY KEY (id_items_sale),
  CONSTRAINT FRK_ID_ITEMS_SALE_SALE FOREIGN KEY (id_sale) REFERENCES sale(id_sale),
  CONSTRAINT FRK_ID_ITEMS_SALE_PRODUCT FOREIGN KEY (id_product) REFERENCES product(id_product)
);

insert into address values(1,'rua1',123,'mataatrlanticac','12345678');
insert into person values(1,1,'Jorge',true);
insert into employee values(1,'Manager',1);
insert into user values('admin','admin',1,true,5);
insert into costumer values(1,1,1);

insert into address values(2,'rua1',123,'mataatrlanticac','12345678');
insert into person values(2,1,'Antonio');
insert into employee values(2,'Manager',2);
insert into user values('admin','admin',1,true,5);
insert into costumer values(1,1,1);

update employee set role = 'Caixa' where id_person = 2;

insert into sale(id_sale,id_user,id_costumer,sale_time,sale_date,sale_time_estimate,sale_total) values (1,1,1,'9999-12-31 23:59:59','3000-12-31',2,10);
insert into sale(id_sale,id_user,id_costumer,sale_time,sale_date,sale_time_estimate,sale_total) values (2,1,1,'9999-12-31 23:59:59','3000-12-31',2,10);
insert into sale(id_sale,id_user,id_costumer,sale_time,sale_date,sale_time_estimate,sale_total) values (3,1,1,'9999-12-31 23:59:59','3000-12-31',2,10);


select * from phone where id_phone = 2;

insert into product_type(name_product_type) values('Lanches');
insert into product_type(name_product_type) values('Bebidas');

select * from product_type;

insert into product (id_product,name_product,final_price_product,weight_product,status_product,id_product_type) values (1,'x-salada',10.10,15,1,1);

insert into ingredient values (1,"tomate",1,0.5);

insert into product_ingredient values (1,1,1);



insert into phone (id_phone,phone,id_person) values (1,'1235-6532',1);
insert into phone (id_phone,phone,id_person) values (2,'1235-7532',2);
insert into phone (id_phone,phone,id_person) values (3,'2322-1532',1);




delete from phone;
delete from employee;
delete from supplier;
delete from user;
delete from address;
delete from person;
delete from costumer;
delete from sale;
delete from product;
delete from product_ingredient;
delete from ingredient;


select * from phone;

select * from person;

select * from employee;

select * from address;

select * from user;

select * from supplier;

select * from costumer;

select * from sale;

select * from product;

select * from product_ingredient;

select * from ingredient;



