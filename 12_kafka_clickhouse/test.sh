#!/usr/bin/env bash
set -euo pipefail

COMPOSE="docker compose -f 12_kafka_clickhouse/docker-compose.yml"

$COMPOSE down -v
$COMPOSE up -d

sleep 5

$COMPOSE exec -T kafka kafka-topics --bootstrap-server localhost:9092 --create --topic numbers --partitions 1 --replication-factor 1 || true
$COMPOSE exec -T kafka kafka-topics --bootstrap-server localhost:9092 --create --topic numbers_dlq --partitions 1 --replication-factor 1 || true

printf '{"value": 10}\n{"value": -4}\n{"value": 7}\n' | \
  $COMPOSE exec -T kafka kafka-console-producer --bootstrap-server localhost:9092 --topic numbers

sleep 5

$COMPOSE exec -T clickhouse clickhouse-client --query "SELECT count() AS cnt FROM fastfood.numbers;"
$COMPOSE exec -T clickhouse clickhouse-client --query "SELECT sum(positive_sum) AS pos, sum(negative_sum) AS neg FROM fastfood.numbers_agg;"
