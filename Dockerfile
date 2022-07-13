FROM bellsoft/liberica-openjdk-alpine:latest
RUN apk add -U tzdata
ENV TZ America/Lima
RUN ln -snf /usr/share/zoneinfo/${TZ} /etc/localtime
RUN echo "${TZ}" > /etc/timezone
VOLUME /tmp
EXPOSE 8090
ADD ./target/gateway-service-0.1.jar gateway.jar
ENTRYPOINT ["java", "-jar", "/gateway.jar"]