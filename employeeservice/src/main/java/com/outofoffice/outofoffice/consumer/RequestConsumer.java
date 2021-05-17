package com.outofoffice.outofoffice.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.outofoffice.outofoffice.configuration.RabbitConfiguration;
import com.outofoffice.outofoffice.errorhandling.NotFoundException;
import com.outofoffice.outofoffice.errorhandling.NotSucesfullException;
import com.outofoffice.outofoffice.model.Department;
import com.outofoffice.outofoffice.model.Employee;
import com.outofoffice.outofoffice.repository.EmployeeRepository;
import com.outofoffice.outofoffice.responseobjects.RequestEmployee;


@Component
public class RequestConsumer {
	private final EmployeeRepository employeeRepository;
	
	public RequestConsumer (EmployeeRepository employeeRepository) {
		super();
		this.employeeRepository = employeeRepository;
	}
	
	@RabbitListener(queues = RabbitConfiguration.QUEUE2)
	public void consumeMessageFromRequestQueue (RequestEmployee requestEmployee) {
		System.out.println("Message2 recieved from LeaveRequest MS !");
		String id_string = requestEmployee.getEmployeeId() + "";
		Employee updated_employee = this.employeeRepository.findById(requestEmployee.getEmployeeId())
				.orElseThrow(() -> new NotFoundException(id_string, "Employee", "id", ""));
		try {
			updated_employee.setAllowance(requestEmployee.getRestDays());
			//updated_employee.setRemainingDays(requestEmployee.getRestDays());
			employeeRepository.save(updated_employee);
			System.out.println("Employee with ID " + requestEmployee.getEmployeeId() + " updated rest days: " + requestEmployee.getRestDays());
		} catch (Exception e) {
			throw new NotSucesfullException();
		}
	}
	

}
