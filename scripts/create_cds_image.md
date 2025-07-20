What is CDS?
--------------
Class Data Sharing is a JVM feature designed to:

* Reduce startup time of Java applications
* Save memory by sharing common class metadata between JVM instances

CDS does this by creating a shared archive of class metadata that can be memory-mapped and reused across JVM processes.

Why does the image tag have -cds?
=================
* The -cds variant of the Liberica OpenJDK Docker image includes a pre-generated CDS archive or enables CDS by default.
* This means when you run Java inside that container, the JVM can start faster and use less memory by leveraging the CDS archive.

Benefits of CDS images
================
* Faster JVM startup
* Lower memory footprint, especially if you run multiple JVMs on the same host
* Better performance for microservices or short-lived Java processes

How to create a CDS image
========================
```bash
$ java -Xshare:dump -XX:SharedArchiveFile=app.jsa -cp your-app.jar
```
Optional: -XX:SharedClassListFile=classes.lst
This command generates a shared archive file named `app.jsa` from the classes in `your-app.jar`.

Alternative
========
```bash
$ java -Xshare:off -XX:DumpLoadedClassList=classes.lst -jar your-app.jar
```
This generates a classes.lst file with the list of loaded classes.


To use the generated CDS archive, you can run your Java application with the following command:
```bash
$ java -Xshare:on -XX:SharedArchiveFile=app.jsa -jar your-app.jar
``` 
Reference: java -Xshare:on -XX:SharedArchiveFile=app.jsa -cp your-app.jar com.your.Main

Example Dockerfile
```dockerfile
FROM eclipse-temurin:17-jre-alpine

COPY your-app.jar /app.jar

# Step 1: generate CDS archive
RUN java -Xshare:dump -XX:SharedArchiveFile=/app-cds.jsa -cp /app.jar

# Step 2: run app using the CDS archive
CMD ["java", "-Xshare:on", "-XX:SharedArchiveFile=/app-cds.jsa", "-jar", "/app.jar"]
```
This Dockerfile does the following:
1. Uses a base image with Java installed.
2. Copies your application JAR into the image.
3. Generates a CDS archive from the application JAR.
4. Runs the application using the generated CDS archive to benefit from faster startup and reduced memory usage.
```
Note:
===========
You can also generate the classes.lst outside Docker to speed up builds.
