# Обзор заданий

## Быстрый запуск

Проект (приложение + PostgreSQL):
```bash
docker compose -f 06_infra/docker/docker-compose.yml up --build
```

Мониторинг (Grafana + Prometheus + Loki):
```bash
docker network create preorder_net
docker compose -f 10_observability/grafana/docker-compose.yml up -d
```

UI‑тесты (Selenium, без установки Maven/ChromeDriver):
```bash
docker compose -f 11_tests_ui/docker-compose.yml up --build
```

Kafka + ClickHouse test:
```bash
12_kafka_clickhouse/test.sh
```

## Полезные ссылки
- Приложение: `http://localhost:8080/`
- Панель сотрудника: `http://localhost:8080/staff`
- Swagger UI: `http://localhost:8080/swagger-ui.html`
- OpenAPI JSON: `http://localhost:8080/v3/api-docs`
- Метрики (Prometheus): `http://localhost:8080/metrics`
- Grafana: `http://localhost:3000` (логин/пароль: `admin/admin`)
- Prometheus: `http://localhost:9090`

1. `01_tz/`
   - Краткое ТЗ в одном файле.

2. `03_db/`
   - Схема в формате dbdiagram.
   - SQL под PostgreSQL: таблицы, представления, функции, сиды.

3. `05_app/`
   - Реализация системы (Spring Boot + PostgreSQL).
   - Web UI и REST API.

4. `06_infra/`
   - Dockerfile и docker‑compose для приложения + PostgreSQL.
   - CI GitLab.

5. `07_docs/`
   - Единый файл документации проекта.

6. `08_tests_api/`
   - Postman коллекция для проверки API.

7. `09_sql/`
   - Сылка на мой профиль со сделанными заданиями - [https://platform.stratascratch.com/user/fedorbondarev](https://platform.stratascratch.com/user/fedorbondarev)

8. `10_observability/`
   - Grafana + Prometheus + Loki конфигурации.

9. `11_tests_ui/`
   - Selenium UI‑тесты + docker‑compose для запуска.

10. `12_kafka_clickhouse/`
    - Kafka + ClickHouse интеграция.
    - Скрипт проверки `check.sh`.
