# 1. Utiliser l'image Java officielle (JDK 17 léger)
FROM eclipse-temurin:17-jdk-alpine

# 2. Répertoire de travail dans le conteneur
WORKDIR /app

# 3. Copier le JAR déjà buildé
COPY target/*.jar app.jar

# 4. Exposer le port (Render utilisera $PORT automatiquement)
EXPOSE 8080

# 5. Démarrer l'application avec la variable PORT de Render
ENTRYPOINT ["sh", "-c", "java -jar app.jar --server.port=$PORT"]

