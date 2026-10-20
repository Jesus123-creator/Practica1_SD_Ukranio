FROM openjdk:17-jdk-slim
WORKDIR /app
COPY ServidorHTTP.java .
RUN javac ServidorHTTP.java
CMD ["java", "ServidorHTTP"]
