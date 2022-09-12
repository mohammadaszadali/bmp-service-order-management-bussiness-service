FROM openjdk:8
WORKDIR /apps
ADD target/bmp-service-order-management-business-service-1.0-SNAPSHOT.jar /apps/
ENTRYPOINT ["java","-jar","/apps/bmp-service-order-management-business-service-1.0-SNAPSHOT.jar"]

