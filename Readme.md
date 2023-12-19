## Build and run backend with DB
1. Build backend application jar
```shell
./gradlew build
```
2. Build and tag docker image
```shell
docker build -t book-shop .
```
3. Run application along with Postgres using docker compose
```shell
docker compose up
```

## Clean flyway migration by deleting volume created by postgres container
1. Bring down docker compose containers
```shell
docker compose down
```
2. List the volumes
```shell
docker volume ls
```
3. Remove the volume
```shell
docker volume rm book-shop_pgdata
```
