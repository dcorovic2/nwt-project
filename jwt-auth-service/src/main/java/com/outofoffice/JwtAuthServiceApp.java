package com.outofoffice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.netflix.ribbon.proxy.annotation.Http.HttpMethod;
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
  @Bean
  public CorsFilter corsFilter() {
      final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
      final CorsConfiguration config = new CorsConfiguration();
      config.setAllowedOrigins(Collections.singletonList("*"));
      config.setAllowedHeaders(Collections.singletonList("*"));
      config.setAllowedMethods(Arrays.stream(HttpMethod.values()).map(HttpMethod::name).collect(Collectors.toList()));
      source.registerCorsConfiguration("/**", config);
      return new CorsFilter(source);
  }
  @Override
  public void run(String... params) throws Exception {
//    User admin = new User();
//    admin.setUsername("admin");
//    admin.setPassword("admin");
//    admin.setEmail("email2@gmail.com");
//    admin.setRoles(new ArrayList<Role>(Arrays.asList(Role.ROLE_ADMIN)));
//    userService.signup(admin);
//
//    User client = new User();
//    client.setUsername("nermin");
//    client.setPassword("nermin");
//    client.setEmail("email1@gmail.com");
//    client.setRoles(new ArrayList<Role>(Arrays.asList(Role.ROLE_CLIENT)));
//    userService.signup(client);
//    
//    User client1 = new User();
//    client1.setUsername("esma");
//    client1.setPassword("esma");
//    client1.setEmail("email3@gmail.com");
//    client1.setRoles(new ArrayList<Role>(Arrays.asList(Role.ROLE_CLIENT)));
//    userService.signup(client1);
//    
//    User client2 = new User();
//    client2.setUsername("nudza");
//    client2.setPassword("nudza");
//    client2.setEmail("email4@gmail.com");
//    client2.setRoles(new ArrayList<Role>(Arrays.asList(Role.ROLE_CLIENT)));
//    userService.signup(client2);
//    
//    User client3 = new User();
//    client3.setUsername("beli");
//    client3.setPassword("beli");
//    client3.setEmail("email5@gmail.com");
//    client3.setRoles(new ArrayList<Role>(Arrays.asList(Role.ROLE_CLIENT)));
//    userService.signup(client3);
//    
//    User client4 = new User();
//    client4.setUsername("dali");
//    client4.setPassword("dali");
//    client4.setEmail("email6@gmail.com");
//    client4.setRoles(new ArrayList<Role>(Arrays.asList(Role.ROLE_CLIENT)));
//    userService.signup(client4);
//    
//    User client5 = new User();
//    client5.setUsername("amii");
//    client5.setPassword("ami");
//    client5.setEmail("email7@gmail.com");
//    client5.setRoles(new ArrayList<Role>(Arrays.asList(Role.ROLE_CLIENT)));
//    userService.signup(client5);
//    
//    User client6 = new User();
//    client6.setUsername("idaa");
//    client6.setPassword("ida");
//    client6.setEmail("email8@gmail.com");
//    client6.setRoles(new ArrayList<Role>(Arrays.asList(Role.ROLE_CLIENT)));
//    userService.signup(client6);
//    
//    User client7 = new User();
//    client7.setUsername("samba");
//    client7.setPassword("samba");
//    client7.setEmail("email9@gmail.com");
//    client7.setRoles(new ArrayList<Role>(Arrays.asList(Role.ROLE_CLIENT)));
//    userService.signup(client7);
//    
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
