# Utiliza una imagen base de OpenJDK para construir la aplicación
FROM maven:3.8.5-openjdk-11 AS build

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia el archivo pom.xml para descargar dependencias de Maven
COPY pom.xml .

# Descarga las dependencias de Maven (esto se realiza por separado para aprovechar el caché de Docker)
# RUN mkdir -p target && \
#    mvn -B dependency:go-offline

# Descarga las dependencias de Maven
RUN mvn -B dependency:go-offline

# Copia el resto de los archivos del proyecto y construye el archivo JAR
COPY src src

RUN mvn -B package

RUN ls /app/target

# Utiliza una imagen más liviana de OpenJDK para la ejecución
FROM openjdk:11-jre-slim

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia el archivo JAR generado en la etapa anterior al contenedor
COPY --from=build /app/target/prueba-tecnica.jar app.jar

# Expone el puerto en el que la aplicación se ejecutará dentro del contenedor (ajusta según sea necesario)
EXPOSE 8080

# Comando para ejecutar la aplicación al iniciar el contenedor
CMD ["java", "-jar", "app.jar"]
