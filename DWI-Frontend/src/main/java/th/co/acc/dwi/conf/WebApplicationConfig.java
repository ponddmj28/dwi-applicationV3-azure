package th.co.acc.dwi.conf;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = "th.co.acc.dwi.reposite")
public class WebApplicationConfig {
}
