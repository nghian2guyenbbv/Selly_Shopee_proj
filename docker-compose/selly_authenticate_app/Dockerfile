FROM --platform=amd64 maven:3.8.4-openjdk-8
LABEL authors="nghianguyen"
WORKDIR /selly-authenticate_app
COPY . .
RUN mvn clean install -Dmaven.test.skip=true
EXPOSE 8080 
CMD ["java", "-jar", "target/selly-authen.jar"]