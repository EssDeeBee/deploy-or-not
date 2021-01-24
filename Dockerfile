FROM bellsoft/liberica-openjdk-alpine-musl
COPY ./build/libs/deploy-or-not-0.0.1-SNAPSHOT.jar /usr/app/
WORKDIR /usr/app
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "deploy-or-not-0.0.1-SNAPSHOT.jar"]