1. Check JDK 21
2. Check Mysql Server 8.0.40 
3. if you want to change DB to H2 to run test below, or you can use mysql because i configed ddl-auto create-drop 
 please change file application.properties 
spring.h2.console.enabled=true
spring.h2.console.path=/h2
spring.datasource.url=jdbc:h2:mem:crititer 
5. I also change some item in collection of postman for suitable my project.
4. run app.

