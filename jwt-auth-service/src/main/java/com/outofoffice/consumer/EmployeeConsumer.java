package com.outofoffice.consumer;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.outofoffice.configuration.RabbitConfiguration;
import com.outofoffice.model.NewUser;
import com.outofoffice.model.Role;
import com.outofoffice.model.User;
import com.outofoffice.repository.UserRepository;
import com.outofoffice.service.UserService;


@Component
public class EmployeeConsumer {
	
	@Autowired
	  private UserService userService;

	public EmployeeConsumer(UserService userService) {
		this.userService = userService;
	}
	
	@RabbitListener(queues = RabbitConfiguration.QUEUE4)
	public void consumeMessageFromEmployeeQueue (NewUser user) {
		System.out.println("Message recieved from Employee MS !");
		User newUser = new User(user.getUsername(), user.getEmail(), user.getPassword());
		newUser.setRoles(new ArrayList<Role>(Arrays.asList(Role.ROLE_CLIENT)));
		try {
			String token = userService.signup(newUser);
		} catch (Exception e) {
		}
	}

}
