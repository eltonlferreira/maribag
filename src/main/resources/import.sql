-- You can use this file to load seed data into the database using SQL statements

-- DROP TABLE IF EXISTS produto CASCADE;
-- DROP TABLE IF EXISTS grupo CASCADE;
-- DROP SEQUENCE IF EXISTS idgrupo_seq CASCADE ;
-- DROP SEQUENCE IF EXISTS idproduto_seq CASCADE ;
-- DROP SEQUENCE IF EXISTS hibernate_sequence CASCADE ;

INSERT INTO grupo(idgrupo, nome) VALUES (NEXTVAL('idgrupo_seq'), 'Compras do mercado');
INSERT INTO grupo(idgrupo, nome) VALUES (NEXTVAL('idgrupo_seq'), 'Compras saida de praia');
INSERT INTO grupo(idgrupo, nome) VALUES (NEXTVAL('idgrupo_seq'), 'Compras para computador novo');

INSERT INTO produto(idproduto, nome, quantidade, valor, id_grupo) VALUES (NEXTVAL('idproduto_seq'), 'Açucar refinado 500g.', 2, NULL, (SELECT idgrupo FROM grupo WHERE nome='Compras do mercado'));
INSERT INTO produto(idproduto, nome, quantidade, valor, id_grupo) VALUES (NEXTVAL('idproduto_seq'), 'Cerveja 350ml.', 12, NULL, (SELECT idgrupo FROM grupo WHERE nome='Compras do mercado'));
INSERT INTO produto(idproduto, nome, quantidade, valor, id_grupo) VALUES (NEXTVAL('idproduto_seq'), 'Café forte 500g.', 1, NULL, (SELECT idgrupo FROM grupo WHERE nome='Compras do mercado'));

INSERT INTO produto(idproduto, nome, quantidade, valor, id_grupo) VALUES (NEXTVAL('idproduto_seq'), 'Suco de laranja 1l.', 2, NULL, (SELECT idgrupo FROM grupo WHERE nome='Compras saida de praia'));
INSERT INTO produto(idproduto, nome, quantidade, valor, id_grupo) VALUES (NEXTVAL('idproduto_seq'), 'Pão de forma.', 2, NULL, (SELECT idgrupo FROM grupo WHERE nome='Compras saida de praia'));

INSERT INTO produto(idproduto, nome, quantidade, valor, id_grupo) VALUES (NEXTVAL('idproduto_seq'), 'Mouse sem fio.', 1, 50.00, (SELECT idgrupo FROM grupo WHERE nome='Compras para computador novo'));

