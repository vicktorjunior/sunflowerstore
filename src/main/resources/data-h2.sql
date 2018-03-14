-- EMPLOYEE PHOTOS
insert into file(id, content) values
(0,  FILE_READ('./src/main/resources/static/photos/face.jpg'));

--AUTHENTICATION
INSERT into user(id, username, password, name, email, experience, skill, active, picture_id) VALUES
(1, 'user', '$2a$10$Qji2/icFWIGGQEAv8bbwNuKGrSZyiUUPqE/0SNO2M.BpU.NA6xPhW', 'Master Yoda','yoda@stars.wars','Masters Unidentified Jedi, Garro, Qui-Gon Jinn', 'Deflect Force Lightning, Strategic Mastery, Acting Skills, Indomitable Will, Battle Meditation, Sensing Death And Force-aided Acrobatics.',  TRUE, 0);

--ROLES
insert into role(id, role) values
(1, 'ROLE_USER'),
(2, 'ROLE_ADMIN');

-- USER_ROLES
insert into user_roles (user_id, roles_id) values
(1, 1);

-- SUPPLIER
insert into fornecedores (nome) values
  ('teste fornecedor');

-- PRODUCT
insert into produtos (apelido, nome, descricao, data_compra, category, codigo_fornecedor, origin, preco_compra, preco_venda, percentual, quantidade_estoque) values
  ('Arma','Arma','aaa', TO_DATE('17/12/2015', 'DD/MM/YYYY'),'BRINQUEDO', 1, 1, 1.11,1.11,1,11);

insert into produtos (apelido, nome, descricao, data_compra, category, codigo_fornecedor, origin, preco_compra, preco_venda, percentual, quantidade_estoque) values
('Bola','Bola','aaa', TO_DATE('17/12/2015', 'DD/MM/YYYY'),'BRINQUEDO', 1, 1, 1.11,1.11,1,11);

insert into produtos (apelido, nome, descricao, data_compra, category, codigo_fornecedor, origin, preco_compra, preco_venda, percentual, quantidade_estoque) values
('Carrinho','Carrinho','aaa', TO_DATE('17/12/2015', 'DD/MM/YYYY'),'BRINQUEDO', 1, 1, 1.11,1.11,1,11);

insert into produtos (apelido, nome, descricao, data_compra, category, codigo_fornecedor, origin, preco_compra, preco_venda, percentual, quantidade_estoque) values
('Dado','Dado','aaa', TO_DATE('17/12/2015', 'DD/MM/YYYY'),'BRINQUEDO', 1, 1, 1.11,1.11,1,11);


