CREATE DATABASE IF NOT EXISTS banco_testes;
USE banco_testes;

CREATE TABLE IF NOT EXISTS pessoa(
	id_pessoa int not null auto_increment,
    nome_pessoa varchar(255) not null,
    telefone_pessoa varchar(11) not null,
    CONSTRAINT PRK_ID_PESSOA PRIMARY KEY (id_pessoa) 
);

CREATE TABLE IF NOT EXISTS pessoa_fisica(
	rg varchar(10) not null,
    cpf varchar(11) not null,
    id_pessoa int not null,
    CONSTRAINT UNK_CPF_PESSOA_FISICA UNIQUE KEY (cpf),
    CONSTRAINT UNK_RG_PESSOA_FISICA UNIQUE KEY (rg),
    CONSTRAINT FRK_ID_PESSOA_FISICA FOREIGN KEY (id_pessoa) REFERENCES pessoa(id_pessoa)
);

CREATE TABLE IF NOT EXISTS pessoa_juridica(
	cnpj varchar(14) not null,
    id_pessoa int not null,
    CONSTRAINT UNK_CNPJ_PESSOA_JURIDICA UNIQUE KEY (cnpj),
    CONSTRAINT FRK_ID_PESSOA_JURIDICA FOREIGN KEY (id_pessoa) REFERENCES pessoa(id_pessoa)
);

CREATE TABLE IF NOT EXISTS funcionario(
	cargo varchar(255) not null,
    id_pessoa int not null,
    CONSTRAINT FRK_ID_FUNCIONARIO FOREIGN KEY (id_pessoa) REFERENCES pessoa(id_pessoa)
);
