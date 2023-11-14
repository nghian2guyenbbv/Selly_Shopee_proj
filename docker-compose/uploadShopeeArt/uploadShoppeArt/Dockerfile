FROM --platform=amd64 maven:3.8.4-openjdk-8
LABEL authors="nghianguyen"
WORKDIR /uploadShoppeArt
COPY . .
RUN mvn clean install -Dmaven.test.skip=true
EXPOSE 8083 
CMD ["java", "-jar", "target/uploadShoppeArt.jar"]