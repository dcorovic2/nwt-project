package com.outofoffice.outofoffice.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.outofoffice.outofoffice.configuration.RabbitConfiguration;
import com.outofoffice.outofoffice.errorhandling.NotFoundException;
import com.outofoffice.outofoffice.errorhandling.NotSucesfullException;
import com.outofoffice.outofoffice.model.Department;
import com.outofoffice.outofoffice.model.Employee;
import com.outofoffice.outofoffice.repository.EmployeeRepository;
import com.outofoffice.outofoffice.responseobjects.RequestNotification;

@Component
public class RequestConsumer {
	private final EmployeeRepository employeeRepository;

	@Autowired
	RabbitTemplate rabbitTemplate;

	public RequestConsumer(EmployeeRepository employeeRepository) {
		super();
		this.employeeRepository = employeeRepository;
	}

	@RabbitListener(queues = RabbitConfiguration.QUEUE2)
	public void consumeMessageFromRequestQueue(RequestNotification requestEmployee) {
		System.out.println("Message2 recieved from LeaveRequest MS !");
		String id_string = requestEmployee.getEmployeeId() + "";
		try {
			Employee updated_employee = this.employeeRepository.findById(requestEmployee.getEmployeeId())
					.orElseThrow(() -> new NotFoundException(id_string, "Employee", "id", ""));
			updated_employee.setAllowance(requestEmployee.getRestDays());
			// updated_employee.setRemainingDays(requestEmployee.getRestDays());
			employeeRepository.save(updated_employee);
			System.out.println("Employee with ID " + requestEmployee.getEmployeeId() + " updated rest days: "
					+ requestEmployee.getRestDays());
			requestEmployee.setBackup(true);
			rabbitTemplate.convertAndSend(RabbitConfiguration.EXCHANGE, RabbitConfiguration.ROUTING_KEY3,
					requestEmployee);
		} catch (Exception e) {
			requestEmployee.setBackup(false);
			System.out.println("Something wnt wrong! Employee with id "+ requestEmployee.getEmployeeId() + " can not be updated!" );
			rabbitTemplate.convertAndSend(RabbitConfiguration.EXCHANGE, RabbitConfiguration.ROUTING_KEY3,
					requestEmployee);
		}
	}
}
