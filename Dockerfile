# build stage
FROM maven as builder
WORKDIR /app

COPY src src
COPY pom.xml .

RUN mvn package

# execution stage
FROM openjdk
WORKDIR /app

COPY --from=builder /app/target/IntegerInformation-0.0.1.jar app.jar   
ENTRYPOINT ["java", "-jar", "app.jar"]
