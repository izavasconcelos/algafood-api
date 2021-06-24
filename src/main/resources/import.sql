insert into tab_cozinhas (nome)  values ('Tailandesa');
insert into tab_cozinhas (nome) values ('Indiana');
insert into tab_cozinhas (nome) values ('Argentina');
insert into tab_cozinhas (nome) values ('Brasileira');

insert into tab_estados (nome) values ('RS');
insert into tab_estados (nome) values ('SP');

insert into tab_cidades (nome, estado_id) values ('City 1', 1);
insert into tab_cidades (nome, estado_id) values ('City 2', 2);
insert into tab_cidades (nome, estado_id) values ('City 33', 1);

insert into tab_restaurantes (nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, endereco_cidade_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_bairro) values ('Thai Gourmet', 10, 1, utc_timestamp, utc_timestamp, 1, '38400-999', 'Rua João Pinheiro', '1000', 'Centro');
insert into tab_restaurantes (nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values ('Thai Delivery', 9.50, 1, utc_timestamp, utc_timestamp);
insert into tab_restaurantes (nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values ('Tuk Tuk Comida Indiana', 15, 2, utc_timestamp, utc_timestamp);
insert into tab_restaurantes (nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values ('Petisqueira', 15, 3, utc_timestamp, utc_timestamp);
insert into tab_restaurantes (nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values ('Outback', 1, 3, utc_timestamp, utc_timestamp);


insert into tab_pagamentos (descricao) values ('Cartão de crédito');
insert into tab_pagamentos (descricao) values ('Cartão de débito');
insert into tab_pagamentos (descricao) values ('Dinheiro');

insert into tab_permissoes (nome, descricao) values ('CONSULTAR_COZINHAS', 'Permite consultar cozinhas');
insert into tab_permissoes (nome, descricao) values ('EDITAR_COZINHAS', 'Permite editar cozinhas');

insert into tab_grupo (name) values ('Grupo 1');
insert into tab_grupo (name) values ('Grupo 2');

insert into tab_usuario (name, email, password, data_cadastro) values ('usuario 1', 'email', 'senhasecreta', utc_timestamp);
insert into tab_usuario (name, email, password, data_cadastro) values ('usuario 2', 'email', 'senhasecreta', utc_timestamp);

insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1, 1), (1, 2), (1, 3), (2, 3), (3, 2), (3, 3), (4, 1), (4, 2), (5, 1), (5, 2);

insert into grupo_permissao (grupo_id, permissao_id) values (1, 1), (1, 2);

insert into usuario_grupo (usuario_id, grupo_id) values (1, 1), (1, 2);

insert into tab_produtos (name, description, price, active, restaurante_id) values ('Porco com molho agridoce', 'Deliciosa carne suína ao molho especial', 78.90, 1, 1);
insert into tab_produtos (name, description, price, active, restaurante_id) values ('Camarão tailandês', '16 camarões grandes ao molho picante', 110, 1, 1);
insert into tab_produtos (name, description, price, active, restaurante_id) values ('Salada picante com carne grelhada', 'Salada de folhas com cortes finos de carne bovina grelhada e nosso molho especial de pimenta vermelha', 87.20, 1, 2);
insert into tab_produtos (name, description, price, active, restaurante_id) values ('Garlic Naan', 'Pão tradicional indiano com cobertura de alho', 21, 1, 3);
insert into tab_produtos (name, description, price, active, restaurante_id) values ('Murg Curry', 'Cubos de frango preparados com molho curry e especiarias', 43, 1, 3);
insert into tab_produtos (name, description, price, active, restaurante_id) values ('Bife Ancho', 'Corte macio e suculento, com dois dedos de espessura, retirado da parte dianteira do contrafilé', 79, 1, 4);
insert into tab_produtos (name, description, price, active, restaurante_id) values ('T-Bone', 'Corte muito saboroso, com um osso em formato de T, sendo de um lado o contrafilé e do outro o filé mignon', 89, 1, 4);
insert into tab_produtos (name, description, price, active, restaurante_id) values ('Sanduíche X-Tudo', 'Sandubão com muito queijo, hamburger bovino, bacon, ovo, salada e maionese', 19, 1, 5);

