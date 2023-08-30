Запустить папку с проектом в среде разработке.
В файле pom.xml подгрузить все зависимости.

Создать базу данный в PostgreSQL
Файл с SQL для создания таблиц размещен в файле init.sql

В файле application.properties исправить следующие поля под вашу БД и параметры пользователя
spring.datasource.url=jdbc:postgresql://localhost:5432/test1
spring.datasource.username=postgres
spring.datasource.password=admin

Запустить сервер, по адресу http://localhost:8080/ будет доступно приложение