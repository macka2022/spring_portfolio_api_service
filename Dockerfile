# 1. Utiliser l'image Java officielle (avec JDK)
FROM eclipse-temurin:17-jdk-alpine

# 2. Définir le répertoire de travail dans le conteneur
WORKDIR /app

# 3. Copier le fichier jar du projet dans le conteneur
# Assure-toi que ton .jar est déjà buildé avec Maven/Gradle dans target/
COPY target/demoportflio-0.0.1-SNAPSHOT.jar app.jar

# 4. Exposer le port utilisé par Spring Boot (par défaut 8080)
EXPOSE 8080

# 5. Définir la commande pour démarrer l'application
ENTRYPOINT ["java","-jar","app.jar"]
