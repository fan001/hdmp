FROM 192.168.1.187:5000/base/java:8-alpine
EXPOSE 80
ENTRYPOINT ["java","-jar","-Xms1024m","-Xmx1024m","app.jar"]
ADD build/libs/*.jar app.jar