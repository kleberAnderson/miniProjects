CREATE DATABASE agenda
GO
USE agenda

GO
CREATE TABLE contato(
	id int identity(0, 1) NOT NULL,
	nome varchar(30) NOT NULL,
	telefone varchar(20),
	email varchar(30),
	nascimento date,

	primary key(id)
);

select * from contato