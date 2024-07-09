create table if not exists Cliente(
	cod_cliente int not null,
	nome_cliente varchar(50),
	endereco_cliente varchar(60),
	primary key (cod_cliente)
);

create table if not exists Vendedor(
	cod_vendedor int not null,
	nome_vendedor varchar(50),
	endereco_vendedor varchar(60),
	primary key (cod_vendedor)
);

create table if not exists Pedido(
	cod_pedido int not null primary key,
	cod_cliente int,
	cod_vendedor int,
	data_pedido date,
	qtde_pedido float,
	valor_pedido float,
	foreign key (cod_cliente) references Cliente (cod_cliente),
	foreign key (cod_vendedor) references Vendedor (cod_vendedor)
);

create table if not exists Linha_Produto(
	cod_linha int not null primary key,
	descricao_linha int
);

create table if not exists Produto (
	cod_produto int not null primary key,
	cod_linha int,
	nome_produto varchar(60),
	qtde_produto float,
	foreign key (cod_linha) references Linha_Produto (cod_linha)
);

create table if not exists Item (
	cod_pedido int,
	cod_produto int,
	qtde_item float,
	valor_unit float,
	valor_parcial float,
	foreign key (cod_pedido) references Pedido (cod_pedido),
	foreign key (cod_produto) references Produto (cod_produto),
	primary key (cod_pedido, cod_produto)
);

alter table Linha_Produto alter column descricao_linha type varchar(50);
alter table Cliente alter column cod_cliente

INSERT INTO Cliente (cod_cliente, nome_cliente, endereco_cliente)
VALUES (1, 'JOSÉ DA SILVA', 'RUA A, 100 - CENTRO');

INSERT INTO Cliente (cod_cliente, nome_cliente, endereco_cliente)
VALUES (2, 'ANTONIO CABRAL', 'AV. SAO JOSE, 73 - CANDEIAS');

INSERT INTO Vendedor (cod_vendedor, nome_vendedor, endereco_vendedor)
VALUES (1, 'MANOEL CAXIAS', 'RUA TRINDADE, 347 - BRASIL');

INSERT INTO Linha_Produto (cod_linha, descricao_linha)
VALUES (1, 'CALÇADOS');

INSERT INTO Linha_Produto (cod_linha, descricao_linha)
VALUES (2, 'CONFECÇÕES');

INSERT INTO Produto (cod_produto, cod_linha, nome_produto, qtde_produto)
VALUES (1, 2, 'CALÇA JEANS', 109);

INSERT INTO Produto (cod_produto, cod_linha, nome_produto, qtde_produto)
VALUES (2, 1, 'SAPATO MASCULINO', 500);

INSERT INTO Pedido (cod_pedido, cod_vendedor, cod_cliente, data_pedido, qtde_pedido, valor_pedido)
VALUES (1, 1, 2, '18-05-2019', 3, 170);

INSERT INTO Item (cod_pedido, cod_produto, qtde_item, valor_unit, valor_parcial)
VALUES (1, 1, 2, 50, 100);

INSERT INTO Item (cod_pedido, cod_produto, qtde_item, valor_unit, valor_parcial)
VALUES (1, 2, 1, 70, 70);
	
DELETE FROM Linha_Produto WHERE Cod_Linha=1;
-- "Chave (cod_linha)=(1) ainda é referenciada pela tabela "produto".atualização ou exclusão em tabela "linha_produto"
-- viola restrição de chave estrangeira "produto_cod_linha_fkey" em "produto""
-- cod_linha é chave estrangeira na tabela produto, caso eu exclua irá interferir na outra tabela

UPDATE Pedido SET Cod_Cliente =2 WHERE Cod_Pedido=1;
-- foi atualizado com sucesso o código do cliente no pedido

UPDATE Item SET Cod_Produto =3 WHERE Cod_Pedido=1;
-- "Chave (cod_pedido, cod_produto)=(1, 3) já existe.duplicar valor da chave viola a restrição de unicidade "item_pkey""
-- cod_produto e cod_pedido são chaves primárias de item, então o sistema impede a duplicação da chave primária

DELETE FROM Item WHERE Cod_Produto=1;
-- foi deletado com sucesso o item que tem o código do produto=1

select nome_cliente, endereco_cliente
from Cliente

select cod_produto, nome_produto, qtde_produto
from Produto pr, Linha_Produto lp
where descricao_linha='CONFECÇÕES' and pr.cod_linha=lp.cod_linha

select nome_cliente, endereco_cliente
from (Cliente cl join Pedido ped on cl.cod_cliente=ped.cod_cliente)
where data_pedido>='2019-05-01' and data_pedido<='2019-05-31';

select nome_vendedor, count(cod_pedido), sum (valor_pedido)
from Vendedor join Pedido on Vendedor.cod_vendedor = Pedido.cod_vendedor
group by nome_vendedor;

select nome_vendedor
from Vendedor 
where cod_vendedor = (
	select cod_vendedor
	from Pedido
	group by cod_vendedor
	order by count(cod_pedido) desc limit 1
);

create view desempenho_produto(nome, total_vendido) as
select nome_produto, sum(qtde_item)
from Produto join Item on produto.cod_produto = item.cod_produto
group by nome_produto;

create view notinha(pedido, cliente, vendedor, valor_total) as
select cod_pedido,nome_cliente,nome_vendedor, valor_pedido
from (Pedido join Cliente on Cliente.cod_cliente = Pedido.cod_cliente) join Vendedor on Vendedor.cod_vendedor = Pedido.cod_vendedor









