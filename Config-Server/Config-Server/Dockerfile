# Specify the base machine
FROM ubuntu:latest

ARG JDK_VERSION=11
ENV JDK_VERSION ${JDK_VERSION}

RUN apt-get update && apt-get install -y locales && rm -rf /var/lib/apt/lists/* \
	&& localedef -i en_US -c -f UTF-8 -A /usr/share/locale/locale.alias en_US.UTF-8
ENV LANG en_US.utf8

RUN apt-get update --fix-missing && apt-get update && apt-get install -y openjdk-11-jdk
ENV JAVA_HOME /usr/lib/jvm/java-${JDK_VERSION}-openjdk-amd64
ENV PATH $PATH:$JAVA_HOME/bin

RUN apt-get update && apt-get install -y maven \
 && mvn -version
ENV M2_HOME /usr/share/maven
ENV PATH $PATH:$M2_HOME/bin

ARG PROJECT_NAME=Config-Server
ARG APP_NAME=ORS-Config-Server
ARG SPRING_PROFILES_ACTIVE=dev
ARG APP_RUN_PORT=8085
ARG CONFIG_GIT_URL=https://github.com/samarthhegde248/ORS-Config-Server.git
ARG GIT_BRANCH=main
ARG LOG_FILE_PATH=/var/log/ORS
ARG PATH_TO_ORS_APP=/usr/ORS

# VOLUME [ "/data" ]

ENV SERVER_PORT ${APP_RUN_PORT}
ENV GIT_BRANCH ${GIT_BRANCH}
ENV LOG_FILE_PATH ${LOG_FILE_PATH}
ENV CONFIG_GIT_URL ${CONFIG_GIT_URL}
ENV APP_NAME ${APP_NAME}
ENV PATH_TO_ORS_APP ${PATH_TO_ORS_APP}

RUN mkdir ${PATH_TO_ORS_APP}

WORKDIR ${PATH_TO_ORS_APP}

COPY ./target/${PROJECT_NAME}-0.0.1-SNAPSHOT.jar ${PATH_TO_ORS_APP}/${APP_NAME}.jar

RUN mkdir ${LOG_FILE_PATH}

# CMD java -Dspring.profiles.active=${SPRING_PROFILES_ACTIVE} -jar ${PATH_TO_ORS_APP}/${APP_NAME}.jar
CMD java -jar ${PATH_TO_ORS_APP}/${APP_NAME}.jar

EXPOSE ${APP_RUN_PORT}