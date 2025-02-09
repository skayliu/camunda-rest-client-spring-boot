OpenFeign library used in the `camunda-rest-client-spring-boot` has a high-configurable logging facility.
In order to configure it, a block of properties e.g. in `application.yml` is required:

```yml
logging:
  level:
    org.camunda.bpm.extension.rest.client.RuntimeServiceClient: DEBUG
    org.camunda.bpm.extension.rest.client.RepositoryServiceClient: DEBUG
    org.camunda.bpm.extension.rest.client.ExternalTaskServiceClient: DEBUG
```

In order to enable Request/Response logging, you need to configure additional Feign logging
by providing a factory bean:

```java
import feign.Logger;

@Configuration
public class MyConfiguration {
    /**
     * Full debug of feign client, including request/response
     */
    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
}
```
