CREATE DATABASE banco_testes;
USE banco_testes;

CREATE TABLE IF NOT EXISTS pessoa(
	id_pessoa int not null auto_increment,
    nome_pessoa varchar(255) not null,
    telefone_pessoa varchar(11) not null,
    CONSTRAINT PRK_ID_PESSOA PRIMARY KEY (id_pessoa) 
);