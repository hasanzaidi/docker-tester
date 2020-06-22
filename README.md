# Docker Spring Boot
Repo which demonstrates Docker using a simple Spring Boot application.

## Steps
1. Build app then build Docker images for both Spring Boot and database containers (this command will also run `docker-compose build`):
   ```
   mvn clean package
   ```

1. Run containers using:
    ```
    docker-compose up
    ```

1. Run the integration tests against the running containers using:
    ```
    mvn verify -Pintegration
    ```

You can also run the integration tests in your IDE.

## DB container
If you only want to run the db container, e.g. you are running the web app in the IDE, you can run:
```
docker-compose -f docker-compose-dbonly.yml up
```
But if you do this you need to update the host in the DB connection String from `db` to `localhost`.

## Wiping the database
When running docker-compose, all data in the database will be persisted after shutting down container. To wipe the data just run:
```
docker container prune -f
```
