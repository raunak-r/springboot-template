# BUILD - sudo docker build -t springb-demo .
# RUN - sudo docker run -d -p 9500:9500 \
#         -e allowedOrigin=http://INSERTHost:Port \
#         -e spring.datasource.url=jdbc:mysql://INSERT_IP:3306/cetas_adq \
#         -e spring.datasource.username='INSERT' \
#         -e spring.datasource.password='INSERT' \
#         --log-opt max-size=10m springb-demo


### Build stage
FROM maven:3.6.0-jdk-11-slim AS build

WORKDIR /app
COPY pom.xml .

#resolve maven dependencies
RUN mvn clean package -Dmaven.test.skip -Dmaven.main.skip -Dspring-boot.repackage.skip && rm -r target/

COPY src ./src

# build the app (no dependency download here)
RUN mvn clean package -Dmaven.test.skip

# COPY ./src /app/src
# COPY ./pom.xml /app
# # https://stackoverflow.com/questions/53929866/spring-boot-to-exclude-connecting-to-database-during-maven-build
# # Else, it will try connecting to a db, and the build will fail, since db conn are supplied at runtime.
# RUN mvn -f /app/pom.xml clean install -DskipTests

### Package stage
FROM openjdk:11-jre-slim
COPY --from=build /app/target/api-0.0.1-SNAPSHOT.jar /usr/local/lib/sb-demo.jar
EXPOSE 9500
ENTRYPOINT ["java","-jar","/usr/local/lib/sb-demo.jar"]