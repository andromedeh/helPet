--1
create table if not exists Cliente(
	Cod_Cliente int primary key,
	Nome_Cliente varchar(50),
	Endereco_Cliente varchar(60)
);
create table if not exists Vendedor(
	Cod_Vendedor int primary key,
	Nome_Vendedor varchar(50),
	Endereco_Vendedor varchar(60)
);
create table if not exists Linha_Produto(
	Cod_Linha int primary key,
	Descricao_Linha varchar
);
create table if not exists Produto(
	Cod_Produto int primary key,
	Cod_Linha int references Linha_Produto(Cod_Linha),
	Nome_Produto varchar(60),
	QTDE_Produto float
);
create table if not exists Pedido(
	Cod_Pedido int primary key,
	Cod_Vendedor int references Vendedor(Cod_Vendedor),
	Cod_Cliente int references Cliente(Cod_Cliente),
	Data_Pedido date,
	QTDE_Pedido float,
	Valor_Pedido float
);
create table if not exists Item(
	Cod_Pedido int,
	Cod_Produto int references Produto(Cod_Produto),
	QTDE_Item float,
	Valor_Unit float,
	Valor_Parcial float,
	primary key(Cod_Pedido,Cod_Produto)
);
--2
insert into Cliente (Cod_Cliente,Nome_Cliente,Endereco_Cliente)
	values	(1, 'JOSÉ DA SILVA', 'RUA A, 100 – CENTRO'),
			(2,'ANTONIO CABRAL' ,'AV. SÃO JOSE, 73 – CANDEIAS' );
insert into Vendedor (Cod_Vendedor,Nome_Vendedor,Endereco_Vendedor)
	values	(1,'MANOEL CAXIAS' ,'RUA TRINDADE, 347 – BRASIL' );
insert into Linha_Produto (Cod_Linha, Descricao_Linha)
	values	(1,'CALÇADOS'),
			(2,'CONFECÇÕES');
insert into Produto (Cod_Produto, Cod_Linha, Nome_Produto, QTDE_Produto)
	values 	(1,2,'CALÇA JEANS',109),
			(2,1,'SAPATO MASCULINO',500);
insert into Pedido (Cod_Pedido,Cod_Vendedor,Cod_Cliente,Data_Pedido,QTDE_Pedido,Valor_Pedido)
	values	(1, 1, 2,'18-05-2019' ,  3, 170);
insert into Item (Cod_Pedido,Cod_Produto,QTDE_Item, Valor_Unit, Valor_Parcial)
	values	(1, 1, 2, 50, 100),
			(1, 2, 1, 70, 70);
--3
	--a)
DELETE FROM Linha_Produto WHERE Cod_Linha=1;
/*
Como essa chave (especificamente a 1) é referenciada
em outra instancia da tabela produto, deleta-la quebraria 
a restrição de chave estrangeira
*/
	--b)
UPDATE Pedido SET Cod_Cliente =1 WHERE Cod_Pedido=1;
/*
O update ocorreu normamelmente pois não feriu nenhuma restrição de integridade
*/
	--c)
UPDATE Item SET Cod_Produto =3 WHERE Cod_Pedido=1;
/*
O update não ocorreu pois a chave primária de Item é
composta por cod_pedido e cod_produto, logo como as duas
instâncias de item possuem cod_pedido = 1 esse update
estaria fazendo com que duas instancias de Item tivessem
chaves primárias iguais, o que viola a restrição de unicidade.
*/
	--d)
DELETE FROM Item WHERE Cod_Produto=1;
/*
O delete ocorreu normalmente, como na letra c) o update não ocorreu
por conta da restrição de unicidade, apenas uma instacia foi deletada.
*/

--4

select nome_cliente, endereco_cliente from cliente;
select cod_produto, nome_produto, qtde_produto
from produto
where cod_linha = (
	select cod_linha 
	from linha_produto
	where descricao_linha = 'CONFECÇÕES'
	);

select nome_cliente, endereco_cliente
from cliente
where cod_cliente =any (
	select cod_cliente
	from pedido
	where data_pedido >= '2019-05-01' AND data_pedido < '2019-06-01'
);


select nome_vendedor, count(cod_pedido), sum (valor_pedido)
from vendedor join pedido on vendedor.cod_vendedor = pedido.cod_vendedor
group by nome_vendedor;


select nome_vendedor
from vendedor 
where cod_vendedor = (
	select cod_vendedor
	from pedido
	group by cod_vendedor
	order by count(cod_pedido) desc limit 1
);


create view desempenho_produto(nome, total_vendido) as
select nome_produto, sum(qtde_item)
from produto join item on produto.cod_produto = item.cod_produto
group by nome_produto;

create view notinha(pedido, cliente, vendedor, valor_total) as
select cod_pedido,nome_cliente,nome_vendedor, valor_pedido
from (pedido join cliente on cliente.cod_cliente = pedido.cod_cliente) join vendedor on vendedor.cod_vendedor = pedido.cod_vendedor