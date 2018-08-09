package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import optimization.*;

@Configuration
public class Config {
	@Bean
	public Goal goal() {
		return new DefaultGoal();
	}

}

