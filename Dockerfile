FROM openjdk:8
EXPOSE 8088
WORKDIR /app

RUN apt-get update && apt-get install -y curl
RUN curl -o kaddem-1.0.jar -L "http://192.168.2.132:8081/repository/maven-releases/tn/esprit/spring/kaddem/1.0/kaddem-1.0.jar"

ENTRYPOINT ["java", "-jar", "kaddem-1.0.jar"]
