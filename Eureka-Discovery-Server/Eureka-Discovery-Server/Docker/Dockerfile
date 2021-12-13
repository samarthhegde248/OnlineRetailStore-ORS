# Specify the base machine
FROM ubuntu:latest

ARG JDK_VERSION=11

RUN apt-get update && apt-get install -y locales && rm -rf /var/lib/apt/lists/* \
	&& localedef -i en_US -c -f UTF-8 -A /usr/share/locale/locale.alias en_US.UTF-8
ENV LANG en_US.utf8

RUN apt-get update && apt-get install -y openjdk-${JDK_VERSION}-jdk
ENV JAVA_HOME /usr/lib/jvm/java-${JDK_VERSION}-openjdk-amd64
ENV PATH $PATH:$JAVA_HOME/bin

RUN apt-get update && apt-get install -y maven \
 && mvn -version
ENV M2_HOME /usr/share/maven
ENV PATH $PATH:$M2_HOME/bin

ARG PROJECT_NAME=Eureka-Discovery-Server
ARG APP_NAME=ORS-Eureka-Discovery-Server
ARG SPRING_PROFILES_ACTIVE=dev
ARG APP_RUN_PORT=8761
ARG LOG_FILE_PATH=/var/log/ORS
ARG CLOUD_CONFIG_URI=http://localhost:8085

# VOLUME [ "/data" ]

ENV SERVER_PORT ${APP_RUN_PORT}
ENV LOG_FILE_PATH ${LOG_FILE_PATH}
ENV CLOUD_CONFIG_URI ${CLOUD_CONFIG_URI}

WORKDIR /usr/

COPY ../target/${PROJECT_NAME}-0.0.1-SNAPSHOT.jar /${APP_NAME}.jar

RUN ls .
RUN ls /var/log/
RUN ls ${LOG_FILE_PATH}

# CMD ["java", "-Dspring.profiles.active=${SPRING_PROFILES_ACTIVE}", "-jar", "${APP_NAME}.jar"]
CMD ["java", "-jar", "${APP_NAME}.jar"]

EXPOSE ${APP_RUN_PORT}