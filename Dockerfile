FROM openjdk:alpine
COPY . /usr/itsm_hw2_server
WORKDIR /usr/itsm_hw2_server
RUN apk update \
&& apk add gradle\
&& gradle fatJar \
&& cd build/libs
ENTRYPOINT ["java","-jar", "/usr/itsm_hw2_server/build/libs/hw2s-1.0-fat.jar"]

#FROM alpine-gradle
#COPY . /usr/itsm_hw2_server
#WORKDIR /usr/itsm_hw2_server
#RUN gradle fatJar \
#&& cd build \
#&& cd libs
#ENTRYPOINT ["java","-jar", "/usr/itsm_hw2_server/build/libs/hw2s-1.0-fat.jar"]