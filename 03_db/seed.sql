INSERT INTO ff_user(full_name, phone, role) VALUES ('Иван Петров', '+7-900-000-00-01', 'CLIENT');
INSERT INTO ff_user(full_name, phone, role) VALUES ('Ольга Сидорова', '+7-900-000-00-02', 'STAFF');

INSERT INTO dish(name, description, price, available) VALUES ('Бургер', 'Классический бургер', 250, TRUE);
INSERT INTO dish(name, description, price, available) VALUES ('Картофель фри', 'Средняя порция', 120, TRUE);
INSERT INTO dish(name, description, price, available) VALUES ('Кола', '0.5 л', 90, TRUE);

INSERT INTO cart(user_id) VALUES (1);
INSERT INTO cart_item(cart_id, dish_id, qty) VALUES (1, 1, 2);
INSERT INTO cart_item(cart_id, dish_id, qty) VALUES (1, 2, 1);
