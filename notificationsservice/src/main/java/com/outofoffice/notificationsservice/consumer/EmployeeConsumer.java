package com.outofoffice.notificationsservice.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.outofoffice.notificationsservice.configuration.RabbitConfiguration;
import com.outofoffice.notificationsservice.model.Employee;
import com.outofoffice.notificationsservice.model.Notification;
import com.outofoffice.notificationsservice.repository.EmployeeRepository;
import com.outofoffice.notificationsservice.requestobjects.AddedEmployee;


@Component
public class EmployeeConsumer {
	private final EmployeeRepository employeeRepository;

	public EmployeeConsumer(EmployeeRepository employeeRepository) {
		super();
		this.employeeRepository = employeeRepository;
	}
	
	@RabbitListener(queues = RabbitConfiguration.QUEUE3)
	public void consumeMessageFromEmployeeQueue (AddedEmployee empRequest) {
		System.out.println("Message recieved from Employee MS !");
		Employee empl = new Employee(empRequest.getId(), empRequest.getAllowance(), empRequest.getDepartmentId(), empRequest.getEmail(),
				empRequest.getFirstNameLastName());
		employeeRepository.save(empl);
		System.out.println("Employee with ID: " + empRequest.getId() + " and name:" + empRequest.getFirstNameLastName() + " added in Notification MS");
	}

}
