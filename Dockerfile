FROM ubuntu:latest

WORKDIR /app

RUN apt-get update &&\
    apt-get install -y default-jdk

COPY src/* /app/

RUN javac Client.java  
#RUN javac Server.java
#CMD java Server 8027
ENTRYPOINT ["java", "Client"]

