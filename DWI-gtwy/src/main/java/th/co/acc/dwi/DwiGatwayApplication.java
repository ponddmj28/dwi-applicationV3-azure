package th.co.acc.dwi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy
public class DwiGatwayApplication {

	public static void main(String[] args) {
		SpringApplication.run(DwiGatwayApplication.class, args);
	}

}
