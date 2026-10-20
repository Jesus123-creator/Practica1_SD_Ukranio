FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY ServidorHTTP.java .
RUN javac ServidorHTTP.java
CMD ["java", "ServidorHTTP"]
