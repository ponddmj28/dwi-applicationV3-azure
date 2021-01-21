package th.co.acc.dwi.conf;

import javax.sql.DataSource;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariDataSource;

import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;

@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = "th.co.acc.dwi.repository")
public class WebApplicationConfig {
	
	@Bean(destroyMethod = "close")
	public DataSource dataSource(DataSourceProperties properties) {
		return properties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
	}
	@Bean
    public Encoder feignFormEncoder () {
      return new SpringFormEncoder();
    }
	
}
