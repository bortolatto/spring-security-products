insert into domain_user values (nextval('user_seq'), 'bcrypt', '$2a$12$3MjwojEvaFR5tJhzVtXqIOFdgB7d.tao0EjaGe9eWErcOLSrhu18K', 'john');
insert into product (id,name,price,currency) values (nextval('product_seq'), 'Mac Book Air', 7.999, 'BRL');
insert into product (id,name,price,currency) values (nextval('product_seq'), 'iPhone 14 Pro', 8.599, 'BRL');
insert into product (id,name,price,currency) values (nextval('product_seq'), 'Design Patterns Book', 60, 'USD');
insert into authority (id, user_id, name) values (nextval('authority_seq'), currval('user_seq'), 'READ');
insert into authority (id, user_id, name) values (nextval('authority_seq'), currval('user_seq'), 'WRITE');