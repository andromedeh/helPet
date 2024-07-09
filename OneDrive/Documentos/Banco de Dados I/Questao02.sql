create schema if not exists comercio;

create table if not exists comercio.Cliente(
	codCliente int not null,
	nomeCliente varchar(50) not null,
	enderecoCliente varchar(60),
	primary key (codCliente)
);

create table if not exists comercio.Vendedor(
	codVendedor int not null,
	nomeVendedor varchar(50) not null,
	enderecoVendedor varchar(60),
	primary key (codVendedor)
);

create table if not exists comercio.Pedido(
	codPedido int not null unique,
	codVendedor int,
	codCliente int,
	dataPedido date,
	qtdePedido float,
	valorPedido float,
	primary key (codPedido),
	foreign key (codVendedor) references comercio.Vendedor (codVendedor),
	foreign key (codCliente) references comercio.Cliente (codCliente)
);

create table if not exists comercio.LinhaProduto(
	codLinha int,
	descricaoLinha int,
	primary key (codLinha)
);

create table if not exists comercio.Produto(
	codProduto int not null unique,
	codLinha int,
	nomeProduto varchar(60),
	qtdeProduto float,
	primary key (codProduto),
	foreign key (codLinha) references comercio.LinhaProduto (codLinha)
);

create table if not exists comercio.Item(
	codPedido int,
	codProduto int,
	qtdeItem float,
	valorUnit float,
	valorParcial float,
	primary key (codPedido, codProduto),
	foreign key (codPedido) references comercio.Pedido (codPedido),
	foreign key (codProduto) references comercio.Produto (codProduto)
);


