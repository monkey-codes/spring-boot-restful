# spring-boot-restful

The sample code that accompanies the [Boot Your RESTful API Using Spring](https://johanzietsman.com/boot-your-restful-api-using-spring/) and [Actuator and Prometheus](https://johanzietsman.com/actuator-and-prometheus/) blog post.


# Running the sample code

```
$ ./gradlew buildDocker
$ cd src/main/docker
$ docker volume create --name spring-boot-restful-data-mongo
$ docker volume create --name spring-boot-restful-data-prometheus
$ docker volume create --name spring-boot-restful-data-grafana
$ docker volume create --name spring-boot-restful-data-logs
$ docker volume create --name spring-boot-restful-data-elasticsearch
$ docker-compose up
```
