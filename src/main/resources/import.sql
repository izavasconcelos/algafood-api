insert into tab_cozinhas (nome)  values ('Tailandesa');
insert into tab_cozinhas (nome) values ('Indiana');

insert into tab_restaurantes (nome, taxa_frete, cozinha_id)  values ('Outback', 5.00, 1);
insert into tab_restaurantes (nome, taxa_frete, cozinha_id) values ('Petisqueira', 10.00, 1);
insert into tab_restaurantes (nome, taxa_frete, cozinha_id) values ('Thai Gourmet', 10, 2);
insert into tab_restaurantes (nome, taxa_frete, cozinha_id) values ('Thai Delivery', 9.50, 1);
insert into tab_restaurantes (nome, taxa_frete, cozinha_id) values ('Tuk Tuk Comida Indiana', 15, 2);

insert into tab_pagamentos (descricao, restaurante_id) values ('Cartão de crédito', 2);
insert into tab_pagamentos (descricao, restaurante_id) values ('Cartão de débito', 3);
insert into tab_pagamentos (descricao, restaurante_id) values ('Dinheiro', 1);

insert into tab_permissoes (nome, descricao) values ('CONSULTAR_COZINHAS', 'Permite consultar cozinhas');
insert into tab_permissoes (nome, descricao) values ('EDITAR_COZINHAS', 'Permite editar cozinhas');
