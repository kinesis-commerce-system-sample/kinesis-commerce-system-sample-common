
```
### postgresql起動
docker-compose -f docker-compose.yml build
docker-compose -f docker-compose.yml up -d

### psqlログイン
docker exec -it kinesis-commerce-system-sample-order-db psql -h 127.0.0.1 -p 5432 -U foo_user -d foo_db
docker exec -it kinesis-commerce-system-sample-purchase-db psql -h 127.0.0.1 -p 5432 -U foo_user -d foo_db
docker exec -it kinesis-commerce-system-sample-inventory-db psql -h 127.0.0.1 -p 5432 -U foo_user -d foo_db

```
