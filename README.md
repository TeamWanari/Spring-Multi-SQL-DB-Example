# Spring MultiDB example

This is an example implementation for Spring MultiDB solution

## Installation

There is a Maven wrapper pushed to the repository so you can run the project with that file (`mvnw` or `mvnw.cmd`). All you need is to set up two local (Postgre) databases described in the `application.yml` file
```
spring:
  datasource:
    view:
      type: com.zaxxer.hikari.HikariDataSource
      url: jdbc:postgresql://stanlin.wanari.com:5432/spring-multidb-view
      username: spring-multidb-view
      password: spring-multidb-view
    prod:
      type: com.zaxxer.hikari.HikariDataSource
      url: jdbc:postgresql://stanlin.wanari.com:5432/spring-multidb-prod
      username: spring-multidb-prod
      password: spring-multidb-prod
```
Liquibase will insert the initial data for you when the application starts.
You can try it out for example with [Postman](https://www.getpostman.com/) by calling the [API](https://www.getpostman.com/collections/20a76f234b589856c005)