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

## Test
Run the integration tests against the running containers using:
```
mvn clean verify
```

You can also run the integration tests in your IDE.

