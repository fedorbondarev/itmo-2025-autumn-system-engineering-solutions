INSERT INTO ff_user (id, full_name, phone, role, created_at)
VALUES (1, 'Иван Петров', '+7-900-000-00-01', 'CLIENT', CURRENT_TIMESTAMP)
ON CONFLICT (id) DO NOTHING;

INSERT INTO ff_user (id, full_name, phone, role, created_at)
VALUES (2, 'Ольга Сидорова', '+7-900-000-00-02', 'STAFF', CURRENT_TIMESTAMP)
ON CONFLICT (id) DO NOTHING;

INSERT INTO dish (id, name, description, price, available)
VALUES (1, 'Бургер', 'Классический бургер', 250, true)
ON CONFLICT (id) DO NOTHING;

INSERT INTO dish (id, name, description, price, available)
VALUES (2, 'Картофель фри', 'Средняя порция', 120, true)
ON CONFLICT (id) DO NOTHING;

INSERT INTO dish (id, name, description, price, available)
VALUES (3, 'Кола', '0.5 л', 90, true)
ON CONFLICT (id) DO NOTHING;

INSERT INTO cart (id, user_id, updated_at)
VALUES (1, 1, CURRENT_TIMESTAMP)
ON CONFLICT (id) DO NOTHING;

SELECT setval('ff_user_id_seq', (SELECT COALESCE(MAX(id), 1) FROM ff_user));
SELECT setval('dish_id_seq', (SELECT COALESCE(MAX(id), 1) FROM dish));
SELECT setval('cart_id_seq', (SELECT COALESCE(MAX(id), 1) FROM cart));
