# Base application

### Build
```bash
mvn package
```

### Run
```bash
java -jar target/base-1.0-SNAPSHOT.jar
Для проверки запущенного проекта - http://localhost:8080/hello/world
```

### Examples
```bash
Примеры REST API располагаются в классе com.simbirsoft.controllers.HelloController
Пример клиентского запроса к URI - http://localhost:8080/hello/param располагается в этом же классе в методе sendClientRequestToYourself()
```


## Links
* Swagger: <http://smarthome.simbirsoft:8080/swagger-ui.html>