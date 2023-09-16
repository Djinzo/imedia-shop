# iMedia24 Coding challenge

### Reference Documentation
For further reference, please consider the following sections:

* [Official Gradle documentation](https://docs.gradle.org)
* [Official Kotlin documentation](https://kotlinlang.org/docs/home.html)
* [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.4.3/gradle-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.4.3/gradle-plugin/reference/html/#build-image)
* [Flyway database migration tool](https://flywaydb.org/documentation/)

### Additional Links
These additional references should also help you:

* [Gradle Build Scans – insights for your project's build](https://scans.gradle.com#gradle)


# Docker
for this example we are using Buildpacks to create owr docker image,
Spring Boot includes both Maven and Gradle support for buildpacks, 
so we can directly run the flowing command in the project directory
```
gradle bootBuildImage
```

after the build is don run the fallowing command

```
docker run -p 8080:8080 shop:0.0.1-SNAPSHOT
```
happy coding.
