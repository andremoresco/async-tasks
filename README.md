# Async tasks

This project run a Gmail backup connecting on Gmail API and getting the emails.

## Roadmap

- Connect on Gmail API by service account.

## Tech Stack

Java 11 + Spring-boot 2.3.12.RELEASE

## Documentation

Before starting the application, It's necessary to set up the Gmail API and Client authentication.


### Gmail API

To enable the Gmail API follow the [Google's Support Documentation](https://support.google.com/googleapi/answer/6158841?hl=en)

### Authetication & Authorization

For this version, the service is using the OAuth 2.0 Client ID to authenticate and authorize API access.  
Follow the [Setting up OAuth 2.0](https://support.google.com/googleapi/answer/6158849?hl=en&ref_topic=7013279#zippy=%2Cuser-consent)

* Create the OAuth Client id using the application type **desktop app**.

* Setting up the authentication in the service:
```bash
Configure in appication.yml

gmail:
  authentication:
    client:
      id: xxxxxx
      secret: xxxxxx
```
* Set Up User id
```bash
Set up the user id that will connect on the Gmail API and have permissions to connect on gmail.

gmail:
  authentication:
    user:
      id: xxxxxx@gmail.com

```

Now, you can run the application.

The first time to request a new backup, the service will print in the console the link to grant authorization for the application on the browser.
Login with your Google account and grant access to start the backup process.

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
