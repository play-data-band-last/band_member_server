FROM openjdk:17
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} /var/jenkins_home/workspace/somoim_user/app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]