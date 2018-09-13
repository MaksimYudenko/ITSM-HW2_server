FROM openjdk:11
COPY . /usr/itsm_hw2_server
WORKDIR /usr/itsm_hw2_server
RUN apt-get install gradle \
&& gradle build \
&& cd build/libs
ENTRYPOINT ["java","-jar", "HW2s-1.0.jar"]