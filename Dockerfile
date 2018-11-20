FROM openjdk:8-jre-slim
ARG version=0.1.0
ENV VERSION=${version}

RUN useradd -s /bin/bash -k /etc/skell -u 1000 -U -m appuser
RUN mkdir /app
COPY ascii.txt.gz /app
COPY target/HelloWorld*.jar /app
RUN chown -R appuser:appuser /app

USER appuser
WORKDIR /app
CMD java -jar HelloWorld-${VERSION}.jar 
