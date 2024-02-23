ARG PROJ_NAME=auth
ARG VERSION=1.0.0
ARG JARNAME=${PROJ_NAME}-${VERSION}.jar

FROM gradle:8.5 AS build
COPY --chown=gradle:gradle ../core-service /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle --no-daemon build -x test

FROM eclipse-temurin:21-jre as jre
ENV JARNAME=$JARNAME
COPY --from=build /home/gradle/src/build/libs/$JARNAME  /

ENTRYPOINT java -jar auth-1.0.0.jar