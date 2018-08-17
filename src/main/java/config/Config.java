package config;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import optimization.DefaultGoal;
import optimization.Goal;

@Configuration
@ComponentScan("optimization")
public class Config {
	@Bean
	@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	public Goal goal() {
		return new DefaultGoal();
	}

}

