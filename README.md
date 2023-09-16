# How to run using Docker
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
