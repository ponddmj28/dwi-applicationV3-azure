# Drop Wire Installation
![alt text](https://github.com/ponddmj28/dwi-applicationV3-azure/blob/main/messageImage_1611415376757.jpg?Draw=true)


# Upgrade as of 23/01/2021

As previous [article](https://github.com/ponddmj28/dwi-kubernate-gcp) we upgraded to Hystrix ,Ribbon Client LoadBalancing and Zuul Gateway but this release is more upgraded version whole projects to Spring Boot 2.4.2 so some features was missed out so this release focus on 3 mentioned above are replaced by Resilience4j,Spring LoadBalancer and Spring Cloud Gateway

### what benefits from this pactices
- Resilience4j
  - how its working either Thread or Semaphore and setting up configurations those Retry,Circuit Breaker,Rate and Bulkhead
  - Jmeter Loader to play with features above
- Spring LoadBalancer
  - how it works with discovery and feign client
  - many features are avairable that can be used and support cross cutting concern as well
- Spring Cloud Gateway
  - how to config for mapped routing each endpoints
  - how to implements Gateway filter and Global Filter
  - able to implement circuit breaker



