--criando e usando banco de dados
CREATE DATABASE agenda
go
USE agenda

--exclusão do banco dados
use master
go
drop database agenda

--limpeza da tabela
truncate table contato;

--deletando tabela
drop table contato

--criando a tabela contato
CREATE TABLE contato(
	id int identity(0, 1) NOT NULL,
	nome varchar(30) NOT NULL,
	telefone varchar(20),
	email varchar(30),
	nascimento date,

	primary key(id)
);

--mostrando os dados da tabela
select * from contato