FROM --platform=amd64 openjdk:8
LABEL authors="nghianguyen"
WORKDIR /selly-authenticate_app
COPY target/selly-authen.jar selly-authen.jar
EXPOSE 8080
CMD ["java", "-jar", "selly-authen.jar"]