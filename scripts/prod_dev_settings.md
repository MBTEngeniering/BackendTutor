Settings for Production and Development Environments
==================

## Production Environment

java -jar -Dspring.profiles.active=prod FILE.jar

docker-compose -p my-app-dev --env-file .env.dev up
docker-compose -p my-app-prod --env-file .env.prod up

