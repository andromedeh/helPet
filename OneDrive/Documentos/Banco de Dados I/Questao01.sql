create schema if not exists empresa;
--create domain empresa.tipoCpf as numeric(11);

create table if not exists empresa.Empregado(
	cpf empresa.tipoCpf not null unique,
	nome varchar(50) not null,
	iniciais_meio varchar(3),
	sobrenome varchar(50),
	dt_nascimento date,
	sexo character(1),
	salario float, 
	cpf_super empresa.tipoCpf,
	d_num int,
	foreign key (cpf_super) references empresa.Empregado (cpf),
	primary key (cpf)
);

create table if not exists empresa.Departamento(
	d_numero serial not null,
	d_nome varchar(20) not null,
	cpf_ger numeric(11),
	dt_inicio_ger date,
	foreign key (cpf_ger) references empresa.Empregado (cpf),
	primary key (d_numero)
);

create table if not exists empresa.Projeto(
	p_numero serial not null,
	p_nome varchar(20) not null,
	d_num int,
	p_localizacao varchar(50),
	primary key (p_numero),
	foreign key (d_num) references empresa.Departamento (d_numero)
);

create table if not exists empresa.Trabalha_em(
	p_num int,
	e_cpf empresa.tipoCpf,
	hora time,
	primary key (p_num, e_cpf),
	foreign key (p_num) references empresa.Projeto (p_numero),
	foreign key (e_cpf) references empresa.Empregado (cpf)
);

create table if not exists empresa.Localizacao_dep(
	d_num int not null,
	localizacao varchar(20),
	foreign key (d_num) references empresa.Departamento (d_numero),
	primary key (d_num, localizacao)
);

create table if not exists empresa.Dependente(
	e_cpf empresa.tipoCpf not null unique,
	dep_nome varchar(50) not null,
	parentesco varchar(20),
	foreign key (e_cpf) references empresa.Empregado (cpf),
	primary key (e_cpf, dep_nome)
);

alter table empresa.Empregado add foreign key (d_num) references empresa.Departamento (d_numero);
alter table empresa.Empregado add if not exists endereco varchar(100);
alter table empresa.Dependente add if not exists sexo char check ('F');
alter table empresa.Dependente add if not exists dt_nascimento date;
alter table empresa.Dependente drop sexo