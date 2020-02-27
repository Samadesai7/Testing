package com.cts.swrb;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThat;

import java.time.LocalDate;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.cts.swrb.dao.EmployeeRepository;
import com.cts.swrb.model.Department;
import com.cts.swrb.model.Employee;

@SuppressWarnings("unused")
@RunWith(SpringRunner.class)//a bridge between spring boot test features and Junit
@DataJpaTest//configuring H2, an in-memory database,setting Hibernate,spring Data
            //Datasource,performing an @EntityScan turning on SQL logging
public class EmployeeDaoUnitTest {
	private Employee emps[];

	@Autowired
	private TestEntityManager entityManager;
	@Autowired
	private EmployeeRepository employeeRepository;
	public EmployeeDaoUnitTest() {
		emps = new Employee[] {
				new Employee("sama","darshini",45000,LocalDate.now(),Department.DEVELOPMENT,"9248609241","sama@gmail.com")
		};
	}
		@Before
		public void beforeEachTest() {
			for(Employee e:emps) {
				entityManager.persist(e);
			
		}
			entityManager.flush();
		
	}
		@After
		public void afterEachTest() {
			for(Employee e:emps) {
				entityManager.remove(e);
			}
			entityManager.flush();
			}
		
@Test
public void whenFindByMobileNumber_thenReturnEmployee() {
	Employee e=employeeRepository.findByMobileNumber(emps[0].getMobileNumber());
	assertThat(e).isEqualTo(emps[0]);

}
@Test
public void whenFindByMobileNumber_withNonExistingMobileNumber_thenReturnNull() {
	Employee e=employeeRepository.findByMobileNumber("9121915902");
	assertThat(e).isNull();
}
}
