package Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import service.EventsService;

@Configuration
public class AppConfig {
	@Bean
	public EventsService eventsService() {
		return new EventsService();
	}
	
	
}