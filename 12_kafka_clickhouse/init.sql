CREATE DATABASE IF NOT EXISTS fastfood;

CREATE TABLE IF NOT EXISTS fastfood.kafka_numbers (
  value Int64
) ENGINE = Kafka
SETTINGS
  kafka_broker_list = 'kafka:9092',
  kafka_topic_list = 'numbers',
  kafka_group_name = 'ch_numbers',
  kafka_format = 'JSONEachRow',
  kafka_skip_broken_messages = 1000;

CREATE TABLE IF NOT EXISTS fastfood.kafka_numbers_dlq (
  raw String
) ENGINE = Kafka
SETTINGS
  kafka_broker_list = 'kafka:9092',
  kafka_topic_list = 'numbers_dlq',
  kafka_group_name = 'ch_numbers_dlq',
  kafka_format = 'JSONEachRow';

CREATE TABLE IF NOT EXISTS fastfood.numbers (
  value Int64
) ENGINE = MergeTree
ORDER BY tuple();

CREATE TABLE IF NOT EXISTS fastfood.numbers_agg (
  positive_sum Int64,
  negative_sum Int64
) ENGINE = SummingMergeTree
ORDER BY tuple();

CREATE MATERIALIZED VIEW IF NOT EXISTS fastfood.mv_numbers
TO fastfood.numbers AS
SELECT value FROM fastfood.kafka_numbers;

CREATE MATERIALIZED VIEW IF NOT EXISTS fastfood.mv_numbers_sum
TO fastfood.numbers_agg AS
SELECT
  if(value > 0, value, 0) AS positive_sum,
  if(value < 0, value, 0) AS negative_sum
FROM fastfood.kafka_numbers;
