Change properties
===================

How to set 

### Shell command
```bash
$ curl http://localhost:8080/api/public/message
```

Now change in properties file this property
`app.message`


After that refresh via actuator

### Shell command
```bash
$ curl -X POST http://localhost:8080/actuator/refresh
```

Retry the first command to see the change