### Assignment

To run Application Java 11 is required.
Everything tested on Ubuntu Linux and on macOS. Probably it works on Windows too, but I have not Windows machine to test it.

There is two options how to run Application:

build and run as Java self executable container or using gradle.

Using gradle:
```shell script
gradle run
```

Using standalone Jar

1. build application using command:
```shell script
gradle clean build
```

2. run application
```shell script
java -jar build/libs/revolut-test.jar
```


To run acceptance tests server should be up first. Then can be used following command:
```shell script
gradle acceptanceTest
```

Used frameworks:

 - Jetty Web Server
 - RestEasy REST library
 - Dieselpoint Norm library that simplifies interation with database
 - Guice as IoC container
 - Spock Framework for unit and integration testing
 - Cucumber for acceptance testing
 - H2 in-memory database
 - Liqubase for database migrations and initial dataset
 
 
 
 
#### Codestyle remaks

Used latest Java possibilites but there should be one remark about that.

`var` keywork is used only in cases when clearly visible the type of this variable.

For example
```java
var account = new Account();
``` 

if there method call then variable type added explicitly

For example

```java
Account account = service.getAccount();
```