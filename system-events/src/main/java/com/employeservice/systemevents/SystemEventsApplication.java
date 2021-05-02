package com.employeservice.systemevents;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import model.GRPCModel;
import repository.GRPCRepository;
import service.EventsService;

@SpringBootApplication
@ComponentScan()
@EntityScan("model")
@EnableJpaRepositories("repository")
public class SystemEventsApplication {
	
	public static void main(String[] args) throws IOException, InterruptedException{
		SpringApplication.run(SystemEventsApplication.class, args);
	}

}
