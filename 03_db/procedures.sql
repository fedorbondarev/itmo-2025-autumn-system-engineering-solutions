CREATE OR REPLACE FUNCTION create_order_from_cart(
  p_user_id BIGINT,
  p_requested_time TIMESTAMP
) RETURNS BIGINT AS $$
DECLARE
  v_cart_id BIGINT;
  v_order_id BIGINT;
  v_total NUMERIC(12,2) := 0;
BEGIN
  SELECT id INTO v_cart_id FROM cart WHERE user_id = p_user_id LIMIT 1;

  INSERT INTO ff_order(user_id, status, requested_time, total_amount)
  VALUES (p_user_id, 'CREATED', p_requested_time, 0)
  RETURNING id INTO v_order_id;

  INSERT INTO order_item(order_id, dish_id, qty, price_at_order)
  SELECT v_order_id, ci.dish_id, ci.qty, d.price
  FROM cart_item ci
  JOIN dish d ON d.id = ci.dish_id
  WHERE ci.cart_id = v_cart_id;

  SELECT COALESCE(SUM(oi.qty * oi.price_at_order),0)
  INTO v_total
  FROM order_item oi
  WHERE oi.order_id = v_order_id;

  UPDATE ff_order SET total_amount = v_total WHERE id = v_order_id;
  DELETE FROM cart_item WHERE cart_id = v_cart_id;
  UPDATE cart SET updated_at = CURRENT_TIMESTAMP WHERE id = v_cart_id;

  RETURN v_order_id;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION update_order_status(
  p_order_id BIGINT,
  p_status VARCHAR
) RETURNS VOID AS $$
BEGIN
  UPDATE ff_order
  SET status = p_status
  WHERE id = p_order_id;
END;
$$ LANGUAGE plpgsql;
