# G Suite Safe - Gmail Backup

Project to provide for our clients a Gmail backup service.


## Tech Stack

Java 11 + Spring-boot 2.3.12.RELEASE


## Run

Go to the project directory

```bash
  cd async-tasks
```

Install dependencies

```bash
  mvn install
```

Run using maven
```bash
   mvn spring-boot:run -Dspring-boot.run.arguments=--server.port=${PORT}
```

Run using java
```bash
   cd target/ && java -jar gmail-backup-*.jar --server.port=${PORT}
```
If the variable ${PORT} not informed, the server will start and be reachable in the port 8080
## Running Tests

To run tests, run the following command

```bash
  mvn test
```

  
