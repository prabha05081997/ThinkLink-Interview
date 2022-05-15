docker build -t springio/gs-spring-boot-docker .
./mvnw spring-boot:build-image -Dspring-boot.build-image.imageName=springio/gs-spring-boot-docker
docker run -p 8080:8080 -t springio/gs-spring-boot-docker