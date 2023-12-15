FROM gcr.io/distroless/java17-debian12

COPY ./build/libs/book-shop-0.0.1.jar ./service.jar

EXPOSE 9090

ENTRYPOINT [ "java", "-jar", "service.jar" ]