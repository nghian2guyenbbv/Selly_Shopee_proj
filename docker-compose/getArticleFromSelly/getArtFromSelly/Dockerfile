FROM --platform=arm64 maven:3.8.4-openjdk-8
LABEL authors="nghianguyen"
WORKDIR /getArtFromSelly
COPY . .
EXPOSE 8081
RUN mvn clean install -Dmaven.test.skip=true
CMD [ "java", "-jar", "target/get_art_from_selly.jar" ]