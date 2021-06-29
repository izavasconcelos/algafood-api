create table city (id bigint not null auto_increment, name varchar(255) not null, state_id bigint not null, primary key (id)) engine=InnoDB
create table grupo_permissao (grupo_id bigint not null, permissao_id bigint not null) engine=InnoDB
create table kitchen (id bigint not null auto_increment, name varchar(255), primary key (id)) engine=InnoDB
create table restaurante_forma_pagamento (restaurante_id bigint not null, forma_pagamento_id bigint not null) engine=InnoDB
create table state (id bigint not null auto_increment, name varchar(255) not null, primary key (id)) engine=InnoDB
create table tab_grupo (id bigint not null auto_increment, name varchar(255) not null, primary key (id)) engine=InnoDB
create table tab_pagamentos (id bigint not null auto_increment, descricao varchar(255) not null, primary key (id)) engine=InnoDB
create table tab_permissoes (id bigint not null auto_increment, descricao varchar(255) not null, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table tab_produtos (id bigint not null auto_increment, active bit not null, description varchar(255) not null, name varchar(255) not null, price decimal(19,2) not null, restaurante_id bigint not null, primary key (id)) engine=InnoDB
create table tab_restaurantes (id bigint not null auto_increment, data_atualizacao datetime not null, data_cadastro datetime not null, endereco_bairro varchar(255), endereco_cep varchar(255), endereco_complemento varchar(255), endereco_logradouro varchar(255), endereco_numero varchar(255), nome varchar(255), taxa_frete decimal(19,2) not null, endereco_cidade_id bigint, cozinha_id bigint, primary key (id)) engine=InnoDB
create table tab_usuario (id bigint not null auto_increment, data_cadastro datetime not null, email varchar(255) not null, name varchar(255) not null, password varchar(255) not null, primary key (id)) engine=InnoDB
create table usuario_grupo (usuario_id bigint not null, grupo_id bigint not null) engine=InnoDB
alter table city add constraint FK6p2u50v8fg2y0js6djc6xanit foreign key (state_id) references state (id)
alter table grupo_permissao add constraint FKdk13wj1yfp1xd5uuec5h8kd5i foreign key (permissao_id) references tab_permissoes (id)
alter table grupo_permissao add constraint FKsqigirdln0pk9yjude7d8fjev foreign key (grupo_id) references tab_grupo (id)
alter table restaurante_forma_pagamento add constraint FK45e3e3flkgbwycygrptlysmk7 foreign key (forma_pagamento_id) references tab_pagamentos (id)
alter table restaurante_forma_pagamento add constraint FKf0irem6krpop4i70f8nkybnt1 foreign key (restaurante_id) references tab_restaurantes (id)
alter table tab_produtos add constraint FK5yvrr2rvfygyxfroq15y60571 foreign key (restaurante_id) references tab_restaurantes (id)
alter table tab_restaurantes add constraint FKffrlw0yx9nf1ytu1pxqm4s4ja foreign key (endereco_cidade_id) references city (id)
alter table tab_restaurantes add constraint FK6vg8y0uhejvmf4at3es2xo8ye foreign key (cozinha_id) references kitchen (id)
alter table usuario_grupo add constraint FKkgqukhil46thvx3mvgjls73c0 foreign key (grupo_id) references tab_grupo (id)
alter table usuario_grupo add constraint FKhgy6npots9lv9yclufie4riip foreign key (usuario_id) references tab_usuario (id)
insert into tab_cozinhas (nome)  values ('Tailandesa')
insert into tab_cozinhas (nome) values ('Indiana')
insert into tab_cozinhas (nome) values ('Argentina')
insert into tab_cozinhas (nome) values ('Brasileira')
insert into tab_estados (nome) values ('RS')
insert into tab_estados (nome) values ('SP')
insert into tab_cidades (nome, estado_id) values ('City 1', 1)
insert into tab_cidades (nome, estado_id) values ('City 2', 2)
insert into tab_cidades (nome, estado_id) values ('City 33', 1)
insert into tab_restaurantes (nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, endereco_cidade_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_bairro) values ('Thai Gourmet', 10, 1, utc_timestamp, utc_timestamp, 1, '38400-999', 'Rua João Pinheiro', '1000', 'Centro')
insert into tab_restaurantes (nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values ('Thai Delivery', 9.50, 1, utc_timestamp, utc_timestamp)
insert into tab_restaurantes (nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values ('Tuk Tuk Comida Indiana', 15, 2, utc_timestamp, utc_timestamp)
insert into tab_restaurantes (nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values ('Petisqueira', 15, 3, utc_timestamp, utc_timestamp)
insert into tab_restaurantes (nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values ('Outback', 1, 3, utc_timestamp, utc_timestamp)
insert into tab_pagamentos (descricao) values ('Cartão de crédito')
insert into tab_pagamentos (descricao) values ('Cartão de débito')
insert into tab_pagamentos (descricao) values ('Dinheiro')
insert into tab_permissoes (nome, descricao) values ('CONSULTAR_COZINHAS', 'Permite consultar cozinhas')
insert into tab_permissoes (nome, descricao) values ('EDITAR_COZINHAS', 'Permite editar cozinhas')
insert into tab_grupo (name) values ('Grupo 1')
insert into tab_grupo (name) values ('Grupo 2')
insert into tab_usuario (name, email, password, data_cadastro) values ('usuario 1', 'email', 'senhasecreta', utc_timestamp)
insert into tab_usuario (name, email, password, data_cadastro) values ('usuario 2', 'email', 'senhasecreta', utc_timestamp)
insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1, 1), (1, 2), (1, 3), (2, 3), (3, 2), (3, 3), (4, 1), (4, 2), (5, 1), (5, 2)
insert into grupo_permissao (grupo_id, permissao_id) values (1, 1), (1, 2)
insert into usuario_grupo (usuario_id, grupo_id) values (1, 1), (1, 2)
insert into tab_produtos (name, description, price, active, restaurante_id) values ('Porco com molho agridoce', 'Deliciosa carne suína ao molho especial', 78.90, 1, 1)
insert into tab_produtos (name, description, price, active, restaurante_id) values ('Camarão tailandês', '16 camarões grandes ao molho picante', 110, 1, 1)
insert into tab_produtos (name, description, price, active, restaurante_id) values ('Salada picante com carne grelhada', 'Salada de folhas com cortes finos de carne bovina grelhada e nosso molho especial de pimenta vermelha', 87.20, 1, 2)
insert into tab_produtos (name, description, price, active, restaurante_id) values ('Garlic Naan', 'Pão tradicional indiano com cobertura de alho', 21, 1, 3)
insert into tab_produtos (name, description, price, active, restaurante_id) values ('Murg Curry', 'Cubos de frango preparados com molho curry e especiarias', 43, 1, 3)
insert into tab_produtos (name, description, price, active, restaurante_id) values ('Bife Ancho', 'Corte macio e suculento, com dois dedos de espessura, retirado da parte dianteira do contrafilé', 79, 1, 4)
insert into tab_produtos (name, description, price, active, restaurante_id) values ('T-Bone', 'Corte muito saboroso, com um osso em formato de T, sendo de um lado o contrafilé e do outro o filé mignon', 89, 1, 4)
insert into tab_produtos (name, description, price, active, restaurante_id) values ('Sanduíche X-Tudo', 'Sandubão com muito queijo, hamburger bovino, bacon, ovo, salada e maionese', 19, 1, 5)
