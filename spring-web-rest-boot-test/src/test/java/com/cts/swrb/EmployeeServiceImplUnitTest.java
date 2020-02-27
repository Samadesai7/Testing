package com.cts.swrb;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.cts.swrb.dao.EmployeeRepository;
import com.cts.swrb.model.Department;
import com.cts.swrb.model.Employee;
import com.cts.swrb.service.EmployeeService;
import com.cts.swrb.service.EmployeeServiceImpl;

@RunWith(SpringRunner.class)
public class EmployeeServiceImplUnitTest {
	@TestConfiguration
	static class EmployeeServiceImplContextConfiguration {
		@Bean
		public EmployeeService employeeService() {

			return new EmployeeServiceImpl();

		}
	}

	@Autowired
	private EmployeeService employeeService;
	@MockBean
	private EmployeeRepository employeeRepository;

	@Before
	public void setUp() {
		Employee emp = new Employee("sama", "darshini", 45000, LocalDate.now(), Department.DEVELOPMENT, "8886835668",
				"sama@gmail.com");
		Mockito.when(employeeRepository.findByMobileNumber(emp.getMobileNumber())).thenReturn(emp);

	}

	@Test
	public void whenValidMobileNumber_thenEmployeeShouldBeFound() {
		String mno = "8886835668";
		Employee found = employeeService.findByMobileNumber(mno);
		assertThat(found.getMobileNumber()).isEqualTo(mno);
	}

	@Test
	public void whenInvalidMobileNumber_thenEmployeeShouldNotBeFound() {
		String mno = "1234567890";
		Employee found = employeeService.findByMobileNumber(mno);
		assertThat(found).isNull();
	}

}
