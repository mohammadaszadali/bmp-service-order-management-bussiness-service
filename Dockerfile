FROM adoptopenjdk/openjdk8:latest
ADD target/bmp-service-order-management-business-service-1.0.jar bmp-service-order-management-business-service.jar
RUN sh -c 'touch /bmp-service-order-management-business-service.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/bmp-service-order-management-business-service.jar"]

