FROM --platform=arm64 openjdk:8
LABEL authors="nghianguyen"
WORKDIR /getArtFromSelly
COPY target/get_art_from_selly.jar get_art_from_selly.jar
EXPOSE 8081
CMD [ "java", "-jar", "get_art_from_selly.jar" ]