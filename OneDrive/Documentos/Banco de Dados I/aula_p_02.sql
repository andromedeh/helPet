select * from empregado;
select * from departamento;
select * from dependente;
select * from localizacao_dep;
select * from projeto;
select * from trabalha_em;

alter table empregado add endereço varchar(100);
alter table dependente add dt_nascimento date;

insert into departamento (d_nome, cpf_ger, dt_inicio_ger) values ('COMPUTAÇÃO', 333445555, '22-05-1998');
insert into departamento (d_nome, cpf_ger, dt_inicio_ger) values ('ADMINISTRAÇÃO', 987654321, '01-01-1995');
insert into departamento (d_nome, cpf_ger, dt_inicio_ger) values ('COORDENAÇÃO', 888665555, '19-06-1981');

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
	values (123456789, 1, 32.5),
			(123456789, 2, 7.5),
			(666894444, 3, 40.0),
			(453453453, 1, 20.0),
			(453453453, 2, 20.0),
			(333445555, 2, 10.0),
			(333445555, 3, 10.0),
			(333445555, 4, 10.0),
			(333445555, 5, 10.0),
			(999887777, 6, 30.0),
			(999887777, 4, 10.0),
			(987987987, 4, 35.0),
			(987987987, 6, 5.0),
			(987654321, 6, 20.0),
			(987654321, 5, 15.0),
			(888665555, 5, null);

insert into dependente (e_cpf, dep_nome, sexo, dt_nascimento, parentesco)
	values (333445555,'Alice', 'F','05-04-1986','Filha'),
			(333445555,'Theodore', 'M','25-10-1983','Filho'),
			(333445555,'Joy', 'F','03-05-1958','Esposa'),
			(987654321,'Abner', 'M','28-02-1942','Esposa'),
			(123456789,'Michael', 'M','04-01-1986','Filho'),
			(123456789,'Alice', 'F','30-12-1988','Filha'),
			(123456789,'Elizabeth', 'F','05-05-1967','Esposa');


insert into empregado (nome, iniciais_meio, sobrenome, cpf, dt_nascimento, endereço, sexo, salario, cpf_super, d_num)
	values ('Robert', 'F', 'Scou', '943775543', '1952-06-21', 'Newcastle Rd, Bellaire, TX', 'M', 58000, '888665555', 5);
-- Chave (d_num)=(5) não está presente na tabela "departamento".inserção ou atualização em tabela "empregado"
-- viola restrição de chave estrangeira "empregado_d_num_fkey" 
-- (não existe o departamento 5)

insert into projeto (p_nome, p_numero, p_localizacao, d_num)
	values ('ProdutoA', 4, 'Bellaire', 2);
-- Chave (p_numero)=(4) já existe.duplicar valor da chave viola a restrição de unicidade "projeto_pkey"
-- (projeto tem como chave primaria o numero do projeto, então não pode inserir outro produto com o mesmo número)

insert into departamento (d_nome, d_numero, cpf_ger, dt_inicio_ger)
	values ('Produção', 4, '943775543', '1988-10-01');
-- Chave (cpf_ger)=(943775543) não está presente na tabela "empregado".inserção ou atualização em tabela "departamento" 
-- viola restrição de chave estrangeira "departamento_cpf_ger_fkey"
-- (como não consegui adicionar o empregado com esse cpf anteriormente, não existe nenhum empregado gerente com esse cpf)

insert into trabalha_em (e_cpf, p_num, hora)
	values ('677678989', null, '40.0');
-- Registro que falhou contém (null, 677678989, 40).o valor nulo na coluna "p_num" da relação "trabalha_em" 
-- viola a restrição de não-nulo 
-- (o p_numero não pode ser nulo)

insert into dependente (e_cpf, dep_nome, sexo, dt_nascimento, parentesco)
	values ('453453453', 'John', 'M', '1970-12-12', 'ESPOSA');
-- tudo certo

delete from trabalha_em where e_cpf=333445555;


delete from empregado where cpf=987654321;
-- Chave (cpf)=(987654321) ainda é referenciada pela tabela "empregado".atualização ou exclusão em tabela "empregado"
-- viola restrição de chave estrangeira "empregado_cpf_super_fkey" em "empregado"
-- (esse empregado é supervisor de outros empregados no bd, então a exclusão dele está relacionado com os empregados que ele superviosa)

delete from projeto where p_nome='ProdutoX';
-- Chave (p_numero)=(1) ainda é referenciada pela tabela "trabalha_em".atualização ou exclusão em tabela "projeto"
-- viola restrição de chave estrangeira "trabalha_em_p_num_fkey" em "trabalha_em"
-- (se deletar o ProdutoX dessa tabela, irá modificar a tabela de trabalha_em, que tem esse produto como chave estrangeira)

update departamento set cpf_ger=123456789, dt_inicio_ger='1999-10-01' where d_numero=1;


update empregado set cpf_super=943775543 where cpf_super=999887777;


update trabalha_em set hora='5.0' where e_cpf=999887777 and p_num=4;

-- não tinha certeza se era pra colocar em cascada então só fiz normal e alguns não apareceu nenhum erro, o que eu acho que aconteceria caso
-- eu tivesse colocado para fazer em cascada.
