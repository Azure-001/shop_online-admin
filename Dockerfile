FROM registry.cn-hangzhou.aliyuncs.com/library-bd/openjdk:8-jdk-alpine
LABEL maintainer="yangkang@njbandou.com"

ENV TZ Asia/Shanghai
ENV JAVA_OPS -Duser.timezone=Asia/Shanghai

WORKDIR /app
COPY target/*.jar ./app.jar

EXPOSE 8081
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","./app.jar"]
