# Docker Spring Boot
Repo which demonstrates Docker using a simple Spring Boot application.

## Build images
Build images for both Spring Boot and database containers:
```
mvn clean package
docker-compose build
```

## Run
Run containers using:
```
docker-compose up
```

If you only want to run the db container you can run:
```
docker-compose -f docker-compose-dbonly.yml up
```

## Test
Run the integration tests against the running containers using:
```
mvn clean verify
```

You can also run the integration tests in your IDE.

## Wiping the database
When running docker-compose, all data in the database will be persisted from before the container was shut down. To wipe the data just run:
```
docker container prune -f
```
