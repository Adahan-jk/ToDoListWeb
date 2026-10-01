# ToDoListWebApplication
Пет-проект: список задач (ToDo) на Spring MVC + JSP + PostgreSQL. 
## Стек
- Java 17, Maven, Spring WebMVC 5.3.39, Hibernate 5.6 (javax), Tomcat 9, PostgreSQL 16   
- Тесты: JUnit5 + Mockito (`mvn test`, 8 тестов сервиса) 
- Docker: multi-stage сборка + `docker-compose` (app + db)
## Быстрый старт (Docker, рекомендовано)

''bash                                                                       
docker-compose up --build -d                                                  
Открыть: http://localhost:8080/home                                           
Проверка БД:                                                                  
docker exec -it tododb-postgres-compose psql -U todouser -d tododb -c         
"SELECT * FROM records;"                                                      
Остановка:                                                                    
docker-compose down
Локальный запуск (без Docker)
1. Поднять Postgres: docker start tododb-postgres (БД tododb, юзер todouser, пароль todopass, localhost:5432)                                          
2. Собрать: mvn package                                                       
3. Запустить в IntelliJ через Smart Tomcat (Tomcat Home: ~/tomcat) или положить war в ~/tomcat/webapps/                                           
4. Открыть http://localhost:8080/<context>/home Хост БД берется из env DB_HOST (дефолт localhost; в compose задан db).

Эндпоинты                                         

     Метод    URL                  Действие                                  

     GET      /, /home             список + фильтр ?filter=all/active/done      
     POST     /add-record          добавить (title)
     POST     /make-record-done    пометить DONE (id)                        
     OST     /delete-record       удалить (id)    
Тесты                                                                         
mvn test                                         
Unit-тесты RecordService (фильтр, статистика, save/update/delete) с моком     
RecordDao — базу не трогают.