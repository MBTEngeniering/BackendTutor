Settings for Production and Development Environments
==================

## Production Environment

```bash
$ java -jar -Dspring.profiles.active=prod FILE.jar
```

docker compose -p my-app-dev --env-file .env.dev up

docker compose -p my-app-prod --env-file .env.prod up

```bash
java -jar demoapp.jar --spring.config.location=/Users/home/conf.properties
```

Or via Maven command:

```bash
mvn spring-boot:run -Dspring.config.location="file:///Users/home/jdbc.properties"
```