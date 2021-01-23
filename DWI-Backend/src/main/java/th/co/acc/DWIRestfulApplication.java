package th.co.acc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
//@SpringBootApplication(exclude = {ServerTracingAutoConfiguration.class,FeignTracingAutoConfiguration.class,AsyncDefaultAutoConfiguration.class})
public class DWIRestfulApplication {

	public static final String AUTH_TOKEN= "Auth";
	public static void main(String[] args) {
		SpringApplication.run(DWIRestfulApplication.class, args);
	}

}
