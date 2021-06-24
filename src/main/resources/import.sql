insert into tab_cozinhas (nome)  values ('Tailandesa');
insert into tab_cozinhas (nome) values ('Indiana');

insert into tab_estados (nome) values ('RS');
insert into tab_estados (nome) values ('SP');

insert into tab_cidades (nome, estado_id) values ('City 1', 1);
insert into tab_cidades (nome, estado_id) values ('City 2', 2);
insert into tab_cidades (nome, estado_id) values ('City 33', 1);

insert into tab_restaurantes (nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, endereco_cidade_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_bairro) values ('Thai Gourmet', 10, 1, utc_timestamp, utc_timestamp, 1, '38400-999', 'Rua João Pinheiro', '1000', 'Centro');
insert into tab_restaurantes (nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values ('Thai Delivery', 9.50, 1, utc_timestamp, utc_timestamp);
insert into tab_restaurantes (nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values ('Tuk Tuk Comida Indiana', 15, 2, utc_timestamp, utc_timestamp);


insert into tab_pagamentos (descricao) values ('Cartão de crédito');
insert into tab_pagamentos (descricao) values ('Cartão de débito');
insert into tab_pagamentos (descricao) values ('Dinheiro');

insert into tab_permissoes (nome, descricao) values ('CONSULTAR_COZINHAS', 'Permite consultar cozinhas');
insert into tab_permissoes (nome, descricao) values ('EDITAR_COZINHAS', 'Permite editar cozinhas');

insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1, 1), (1, 2), (1, 3), (2, 3), (3, 2), (3, 3);
