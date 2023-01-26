package cpservice.board.config;

import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
public class PropertyConfig {
	
	@Bean(name="pageConfigBean")
	public PropertiesFactoryBean propertiesFactoryBean() throws Exception {
		PropertiesFactoryBean pfb = new PropertiesFactoryBean();
		ClassPathResource cpr = new ClassPathResource("/test/pagination/pagination-config.properties");
		
		pfb.setLocation(cpr);
		
		return pfb;
	}
	
}