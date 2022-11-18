FROM eclipse-temurin:11
ARG JAR_FILE=out/artifacts/OnlineMarket_jar/OnlineMarket.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
