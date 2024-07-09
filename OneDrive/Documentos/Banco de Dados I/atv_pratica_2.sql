alter table empregado add endereço varchar(100);
alter table dependente add dt_nascimento date;

insert into departamento(d_nome, cpf_ger, dt_inicio_ger)
	values('COMPUTAÇÃO', 333445555, '22-05-1998');
insert into departamento(d_nome, cpf_ger, dt_inicio_ger)
	values('ADMINISTRAÇÃO', 987654321, '01-01-1995');
insert into departamento(d_nome, cpf_ger, dt_inicio_ger)
	values('COORDENAÇÃO', 888665555, '19-06-1981');
	
update empregado set endereço = '731 Fondren, Houston, TX', cpf_super = 333445555, d_num = 1 where cpf =123456789; 
update empregado set endereço = '638 Voss, Houston, TX', cpf_super = 888665555, d_num = 2 where cpf =333445555; 
update empregado set endereço = '3321 Castle, Spring, TX', cpf_super = 987654321, d_num = 2 where cpf =999887777; 
update empregado set endereço = '291 Berry, Bellaire, TX', cpf_super = 888665555, d_num = 2 where cpf =987654321; 
update empregado set endereço = '975 Flre Oak, Humble, TX', cpf_super = 333445555, d_num = 1 where cpf =666894444; 
update empregado set endereço = '5631 Rice, Houston, TX', cpf_super = 333445555, d_num = 1 where cpf =453453453; 
update empregado set endereço = '980 Dallas, Houston, TX', cpf_super = 987654321, d_num = 2 where cpf =987987987; 
update empregado set endereço = '450 Stone, Houston, TX', cpf_super = null, d_num = 3 where cpf =888665555; 


insert into localizacao_dep(d_num, localizacao)
	values  (3, 'Houston'),
			(2,'Stafbrd'),
			(1, 'Beliaire'),
			(1, 'Sugartard'),
			(1, 'Houston');
			
insert into projeto (p_nome, p_localizacao, p_numero, d_num)
	values  ('ProdutoX','Bellaire',1,1 ),
			('ProdutoY','Sugarland',2,1 ),
			('ProdutoZ','Houston',3,1 ),
			('Informatizacão','Stafford',4,2 ),
			('ReorQanizacão','Houston',5,3 ),
			('NovosBeneficios','Stafford',6,2 );

insert into trabalha_em(e_cpf, p_num, hora)
	values(123456789, 1, 32.5);
insert into trabalha_em(e_cpf, p_num, hora)
	values(123456789, 2, 7.5);
insert into trabalha_em(e_cpf, p_num, hora)
	values(666894444, 3, 40.0);
insert into trabalha_em(e_cpf, p_num, hora)
	values(453453453, 1, 20.0);
insert into trabalha_em(e_cpf, p_num, hora)
	values(453453453, 2, 20.0);
insert into trabalha_em(e_cpf, p_num, hora)
	values(333445555, 2, 10.0);
insert into trabalha_em(e_cpf, p_num, hora)
	values(333445555, 3, 10.0);
insert into trabalha_em(e_cpf, p_num, hora)
	values(333445555, 4, 10.0);
insert into trabalha_em(e_cpf, p_num, hora)
	values(333445555, 5, 10.0);
insert into trabalha_em(e_cpf, p_num, hora)
	values(999887777, 6, 30.0);
insert into trabalha_em(e_cpf, p_num, hora)
	values(999887777, 4, 10.0);
insert into trabalha_em(e_cpf, p_num, hora)
	values(987987987, 4, 35.0);
insert into trabalha_em(e_cpf, p_num, hora)
	values(987987987, 6, 5.0);
insert into trabalha_em(e_cpf, p_num, hora)
	values(987654321, 6, 20.0);
insert into trabalha_em(e_cpf, p_num, hora)
	values(987654321, 5, 15.0);
insert into trabalha_em(e_cpf, p_num, hora)
	values(888665555, 5, null);
	
insert into dependente (e_cpf, dep_nome, sexo, dt_nascimento, parentesco)
	values (333445555,'Alice', 'F','05-04-1986','Filha'),
			(333445555,'Theodore', 'M','25-10-1983','Filho'),
			(333445555,'Joy', 'F','03-05-1958','Esposa'),
			(987654321,'Abner', 'M','28-02-1942','Esposa'),
			(123456789,'Michael', 'M','04-01-1986','Filho'),
			(123456789,'Alice', 'F','30-12-1988','Filha'),
			(123456789,'Elizabeth', 'F','05-05-1967','Esposa');
--Questão 4
INSERT INTO empregado(
            cpf, nome, iniciais_meio, sobrenome, dt_nascimento, sexo, cpf_super, 
            salario)
    VALUES (&lt;&#39;Robert&#39; , &#39;F&#39;, &#39;Scou&#39;, &#39;943775543&#39;, &#39;1952-06-21&#39;, &#39;2365 Newcastle Rd, Bellaire, TX&#39;,
M,58000, &#39;888665555&#39;. 5&gt;);