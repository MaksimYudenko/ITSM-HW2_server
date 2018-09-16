FROM openjdk:alpine
COPY . /usr/itsm_hw2_server
WORKDIR /usr/itsm_hw2_server
RUN apk update \
&& apk add gradle \
&& gradle build \
&& cd build/libs
ENTRYPOINT ["java","-jar", "/usr/itsm_hw2_server/build/libs/hw2s-1.0.jar"]