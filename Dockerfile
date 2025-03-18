# Usa uma imagem do Maven para compilar o projeto
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Usa uma imagem menor do Java para rodar o JAR
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
# Usa uma imagem base do Java
FROM openjdk:17-jdk-slim

# Expõe a porta usada pela aplicação
EXPOSE 9090