# Getting Started

### Reference Documentation

For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.3.9.RELEASE/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.3.9.RELEASE/maven-plugin/reference/html/#build-image)
* [Hystrix [Maintenance]](https://docs.spring.io/spring-cloud-netflix/docs/2.2.x/reference/html/#circuit-breaker-hystrix-clients)
* [Spring Boot Actuator](https://docs.spring.io/spring-boot/docs/2.4.3/reference/htmlsingle/#production-ready)
* [Spring Security](https://docs.spring.io/spring-boot/docs/2.4.3/reference/htmlsingle/#boot-features-security)
* [Spring Configuration Processor](https://docs.spring.io/spring-boot/docs/2.4.3/reference/htmlsingle/#configuration-metadata-annotation-processor)
* [Hystrix Dashboard [Maintenance]](https://docs.spring.io/spring-cloud-netflix/docs/2.2.x/reference/html/#circuit-breaker-hystrix-dashboard)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/2.4.3/reference/htmlsingle/#boot-features-jpa-and-spring-data)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/2.4.3/reference/htmlsingle/#using-boot-devtools)
* [Okta Spring Boot documentation](https://github.com/okta/okta-spring-boot#readme)

### Guides

The following guides illustrate how to use some features concretely:

* [Circuit Breaker](https://spring.io/guides/gs/circuit-breaker/)
* [Building a RESTful Web Service with Spring Boot Actuator](https://spring.io/guides/gs/actuator-service/)
* [Securing a Web Application](https://spring.io/guides/gs/securing-web/)
* [Spring Boot and OAuth2](https://spring.io/guides/tutorials/spring-boot-oauth2/)
* [Authenticating a User with LDAP](https://spring.io/guides/gs/authenticating-ldap/)
* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
* [Accessing data with MySQL](https://spring.io/guides/gs/accessing-data-mysql/)
* [Okta-Hosted Login Page Example](https://github.com/okta/samples-java-spring/tree/master/okta-hosted-login)
* [Custom Login Page Example](https://github.com/okta/samples-java-spring/tree/master/custom-login)
* [Okta Spring Security Resource Server Example](https://github.com/okta/samples-java-spring/tree/master/resource-server)

### Additional Links

These additional references should also help you:

* [Sample of circuit-breaker metrics with Spring Cloud and Hystrix Dashboard](https://github.com/spring-cloud-samples/hystrix-dashboard)

## OAuth 2.0 and OIDC with Okta

If you don't have a free Okta developer account, you can create one with [the Okta CLI](https://cli.okta.com):

```bash
$ okta register
```

Then, register your Spring Boot app on Okta using:

```bash
$ okta apps create
```

Select **Web** > **Okta Spring Boot Starter** and accept the default redirect URIs.

# Spring Cloud Netflix Maintenance Mode

The dependencies listed below are in maintenance mode. We do not recommend adding them to new projects:

* Hystrix
* Hystrix Dashboard

The decision to move most of the Spring Cloud Netflix projects to maintenance mode was a response to Netflix not
continuing maintenance of many of the libraries that we provided support for.

Please
see [this blog entry](https://spring.io/blog/2018/12/12/spring-cloud-greenwich-rc1-available-now#spring-cloud-netflix-projects-entering-maintenance-mode)
for more information on maintenance mode and a list of suggested replacements for those libraries.
