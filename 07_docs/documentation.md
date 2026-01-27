# Документация

## Архитектура
Система — монолитное веб‑приложение для предварительного заказа в одном ресторане. Роли: клиент и сотрудник.

Состав:
- Web UI (клиент, сотрудник)
- REST API (меню, корзина, заказы)
- Сервисный слой (логика создания заказа, статусы)
- PostgreSQL

Инфраструктура:
- Docker Compose
- Метрики Prometheus и визуализация в Grafana

## Сервисы и интерфейсы
REST API:
- `GET /api/dishes` — список блюд.
- `GET /api/cart/{userId}` — корзина.
- `POST /api/cart/{userId}/items?dishId={id}&qty={n}` — добавить блюдо.
- `PUT /api/cart/{userId}/items?dishId={id}&qty={n}` — изменить количество.
- `POST /api/orders?userId={id}&requestedTime={iso}` — создать заказ.
- `GET /api/orders` — список заказов.
- `GET /api/orders/{id}` — детали заказа.
- `PATCH /api/orders/{id}/status?status=CREATED|ACCEPTED|COOKING|READY|ISSUED` — смена статуса.

Swagger:
- `http://localhost:8080/swagger-ui.html`
- `http://localhost:8080/v3/api-docs`

## UI
Клиент:
- Меню и поиск.
- Фильтры по категориям.
- Корзина с количеством.
- Время выдачи.
- Кнопка оформления и результат.

Сотрудник:
- Фильтры статусов.
- Список заказов.
- Смена статуса.
- Отображение состава заказа.
