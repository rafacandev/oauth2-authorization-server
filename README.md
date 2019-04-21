# oauth2-authorization-server
This is a sample spring-boot authorization server using oauth2



Quick Start
-----------

`${GENERATED_PASSWORD}`: This password is generated when the application starts and it is printed on the standard output
(normally the console where you started the application)

**Note**: If that is the first time you running the application; then, you need to start the application indicating that you want to initialize the database:
```
./gradlew bootRun --args='--spring.datasource.initialization-mode=always'
```

```
# Start the application
./gradlew bootRun

# Open another terminal and request a new token
curl myClientId:myClientSecret@localhost:8080/oauth/token -d grant_type=password -d username=user -d password="${GENERATED_PASSWORD}"
```