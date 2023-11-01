# Utilisez une image de base qui contient une JVM
FROM openjdk:11-jre-slim

# Copiez le JAR de l'application dans le conteneur
COPY target/DevOps_Project-2.1.jar /app.jar

# Commande pour démarrer l'application au lancement du conteneur
CMD ["java", "-jar", "/app.jar"]
