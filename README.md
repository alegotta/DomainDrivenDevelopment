# Domain Driven Development

A small project to exercise with the DDD architectural pattern.

The *Logic* module implements the following:
- an `Entity`  that represents a *student*
- a `Value Object`that represent *phone contacts*
- `Domain Events` that represent *enrolment*, *conclusion* and *withdraw* from a course
- a `Repository` from where information can be retrieved, implemented as a generic JDBC database bridge.

A simple *web application* has also been put in place so to visualize the information in a better way. It is divided in three parts:
- a JSON API (easily implemented thanks to Jakarta Servlets)
- a simple HTML frontend
- a Javascript client to fetch data from the logic module

## Running

1) Start postgres and tomcat
2) Configure the database credentials in the [related file](Logic/src/main/resources/database.properties)
3) Run the following:

```bash
mvn test install
mvn -f WebApp tomcat7:deploy
```

## Technology used

- JUnit5
- Tomcat
- Jakarta Servlets and Jersey
- JDBC
- Maven