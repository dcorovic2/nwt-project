package com.employeservice.systemevents;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import service.EventsService;

@SpringBootApplication
@ComponentScan(basePackages = {"Config"}) 
@ComponentScan()
@EntityScan("model")
@EnableJpaRepositories("repository")
public class SystemEventsApplication implements CommandLineRunner {
	
	public static void main(String[] args) throws IOException, InterruptedException{
		SpringApplication.run(SystemEventsApplication.class, args);
	}

	@Autowired
    private EventsService eventsService; 
	
	@Override
    public void run(String... args) throws Exception {
    	Server server = ServerBuilder.forPort(9091).addService(eventsService).build();
        server.start();
        System.out.println("Server started on " + server.getPort());
        server.awaitTermination();
    }
}
