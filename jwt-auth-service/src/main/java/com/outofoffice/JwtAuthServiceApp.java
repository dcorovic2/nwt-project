package com.outofoffice;

import java.util.ArrayList;
import java.util.Arrays;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.outofoffice.filter.ErrorFilter;
import com.outofoffice.filter.PostFilter;
import com.outofoffice.filter.PreFilter;
import com.outofoffice.filter.RouteFilter;
import com.outofoffice.model.Role;
import com.outofoffice.model.User;
import com.outofoffice.service.UserService;

@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy
public class JwtAuthServiceApp implements CommandLineRunner {

  @Autowired
  UserService userService;

  public static void main(String[] args) {
    SpringApplication.run(JwtAuthServiceApp.class, args);
  }

  @Bean
  public ModelMapper modelMapper() {
    return new ModelMapper();
  }

  @Override
  public void run(String... params) throws Exception {
    User admin = new User();
    admin.setUsername("admin");
    admin.setPassword("admin");
    admin.setEmail("admin@email.com");
    admin.setRoles(new ArrayList<Role>(Arrays.asList(Role.ROLE_ADMIN)));
    userService.signup(admin);

    User client = new User();
    client.setUsername("esmaa");
    client.setPassword("esma123");
    client.setEmail("client@email.com");
    client.setRoles(new ArrayList<Role>(Arrays.asList(Role.ROLE_CLIENT)));
    userService.signup(client);
    
    User client1 = new User();
    client1.setUsername("dalyy");
    client1.setPassword("daly123");
    client1.setEmail("client1@email.com");
    client1.setRoles(new ArrayList<Role>(Arrays.asList(Role.ROLE_CLIENT)));
    userService.signup(client1);
    

    User client2 = new User();
    client2.setUsername("nudzaSingerr");
    client2.setPassword("nudzy123");
    client2.setEmail("client2@email.com");
    client2.setRoles(new ArrayList<Role>(Arrays.asList(Role.ROLE_CLIENT)));
    userService.signup(client2);
    
    User client3 = new User();
    client3.setUsername("Belii");
    client3.setPassword("beli123");
    client3.setEmail("client3@email.com");
    client3.setRoles(new ArrayList<Role>(Arrays.asList(Role.ROLE_CLIENT)));
    userService.signup(client3);
  }
  
  
  @Bean
  @LoadBalanced
  RestTemplate restTemplateWithErrorHandler() {
      return new RestTemplateBuilder()
          .build();
  }
  
  
  	@Bean
	public PreFilter preFilter() {
		return new PreFilter();
	}

	@Bean
	public PostFilter postFilter() {
		return new PostFilter();
	}

	@Bean
	public ErrorFilter errorFilter() {
		return new ErrorFilter();
	}

	@Bean
	public RouteFilter routeFilter() {
		return new RouteFilter();
	}

}
