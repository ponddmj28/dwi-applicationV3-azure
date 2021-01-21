package th.co.acc.dwi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.sleuth.instrument.async.AsyncDefaultAutoConfiguration;
import org.springframework.cloud.sleuth.instrument.web.TraceWebServletAutoConfiguration;
import org.springframework.cloud.sleuth.instrument.web.client.feign.TraceFeignClientAutoConfiguration;

@EnableFeignClients("th.co.acc.dwi.service")
@EnableDiscoveryClient
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class,TraceWebServletAutoConfiguration.class,TraceFeignClientAutoConfiguration.class,AsyncDefaultAutoConfiguration.class})
public class DWIWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(DWIWebApplication.class, args);
	}

}
