CREATE DATABASE banco_testes;
USE banco_testes;

CREATE TABLE IF NOT EXISTS pessoa(
	id_pessoa int not null auto_increment,
    nome_pessoa varchar(255) not null,
    telefone_pessoa varchar(11) not null,
    CONSTRAINT PRK_ID_PESSOA PRIMARY KEY (id_pessoa) 
);

CREATE TABLE IF NOT EXISTS pessoa_fisica(
	rg varchar(8) not null,
    cpf varchar(11) not null,
    id_pessoa int not null,
    CONSTRAINT UNK_CPF_PESSOA_FISICA UNIQUE KEY (cpf),
    CONSTRAINT UNK_RG_PESSOA_FISICA UNIQUE KEY (rg),
    CONSTRAINT FRK_ID_PESSOA_FISICA FOREIGN KEY (id_pessoa) REFERENCES pessoa(id_pessoa)
);