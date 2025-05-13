# Usar imagen base de Java
FROM openjdk:17-jdk-slim

# Crear directorio para la app
WORKDIR /app

# Copiar el archivo JAR generado por tu build
COPY target/brawl_stars_visa-1.0-SNAPSHOT.jar app.jar

# Exponer el puerto que usa Spring Boot
EXPOSE 8080

# Comando para ejecutar la app
ENTRYPOINT ["java", "-jar", "app.jar"]
