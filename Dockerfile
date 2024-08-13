FROM ubuntu:latest
LABEL authors="98173"

# 使用 OpenJDK 作为基础镜像
FROM openjdk:17-jdk-slim

# 设置工作目录
WORKDIR /backend-app

# 复制构建好的 JAR 文件
COPY target/AD-0.0.1-SNAPSHOT.jar .

# 指定暴露的端口
EXPOSE 8080

# 运行 Spring Boot 应用
CMD ["java", "-jar", "AD-0.0.1-SNAPSHOT.jar"]


