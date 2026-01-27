CREATE OR REPLACE VIEW v_order_summary AS
SELECT o.id AS order_id,
       o.status,
       o.requested_time,
       o.total_amount,
       o.created_at,
       u.full_name AS client_name,
       u.phone AS client_phone
FROM ff_order o
JOIN ff_user u ON u.id = o.user_id;

CREATE OR REPLACE VIEW v_order_items AS
SELECT oi.order_id,
       d.name AS dish_name,
       oi.qty,
       oi.price_at_order,
       (oi.qty * oi.price_at_order) AS line_total
FROM order_item oi
JOIN dish d ON d.id = oi.dish_id;
