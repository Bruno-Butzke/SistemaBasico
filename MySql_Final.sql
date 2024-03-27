

create database dbTec_Biblioteca_BrunoButzke;
use dbTec_Biblioteca_BrunoButzke;

create table cliente(
	codigo 	int(4) 			AUTO_INCREMENT,
    nome 	varchar(30)		NOT NULL,
    Fone		varchar(30) 	NOT NULL,
    Email		varchar(30) 	NOT NULL,
    Ende		varchar(100) 		NOT NULL,
    PRIMARY KEY (codigo)
    );
	
create table produto(
	codigo 	int(4) 			AUTO_INCREMENT,
    Descricao 	varchar(30)		NOT NULL,
    Quant		int(30) 	NOT NULL,
    Uni		varchar(30) 	NOT NULL,
    Valor		int(30) 		NOT NULL,
    PRIMARY KEY (codigo)
    );

create table fornecedor(
	codigo int(4) auto_increment,
    Empresa varchar(40) not null,
    Contato varchar(30) not null,
    Fone varchar(30) not null,
    Email varchar(50) not null,
    primary key(codigo)
    );
    
create table tbuser(
	id_user int(4) auto_increment,
	nm_user varchar(40) not null,
    pw_user varchar(30) not null,
    tp_user varchar(30) not null,
    pv_user varchar(1) not null,
    primary key(id_user)
);
    
    insert into tbuser(
    id_user, 	
    nm_user,	
    pw_user,		
    tp_user,		
    pv_user)
    values(null,'bruno','123','user',3);
    