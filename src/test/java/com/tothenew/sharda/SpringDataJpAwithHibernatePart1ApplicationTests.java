package com.tothenew.sharda;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.tothenew.sharda.Dao.EmployeeRepository;
import com.tothenew.sharda.Dao.EmployeeRepositoryForPagingAndSorting;
import com.tothenew.sharda.Model.Employee;


@ExtendWith(SpringExtension.class)
@AutoConfigureTestEntityManager
@SpringBootTest
class SpringDataJpAwithHibernatePart1ApplicationTests {

	@Autowired
	EmployeeRepository repository;
	
	@Autowired
	EmployeeRepositoryForPagingAndSorting forPagingAndSorting;
	
	@Test
	void contextLoads() {
	}

	@Test
	public void testCreate_insertIntoDatabase() {
		Employee employee = new Employee();
		employee.setName("Test");
		employee.setAge(20);
		employee.setLocation("Delhi");
		
		Employee employee2 = new Employee();
		employee2.setName("James");
		employee2.setAge(28);
		employee2.setLocation("Lucknow");
		
		Employee employee3 = new Employee();
		employee3.setName("Sneha");
		employee3.setAge(28);
		employee3.setLocation("Delhi");
	
		repository.save(employee);
		repository.save(employee2);
		repository.save(employee3);
	}
	
	@Test
	public void testRead_fetchFromDatabase() {
		try {
			Employee cur = repository.findById(1).get();
			assertNotNull(cur);
			assertEquals("Test User", cur.getName());
		}
		catch (Exception e) {
			
		}
	}
	
	@Test
	public void testUpdate_updateIntoDatabase() {
		Employee curr = repository.findById(1).get();
		curr.setLocation("Bihar");
		repository.save(curr);
	}
	
	@Test
	public void testDelete_deleteFromDatabase() {
		if (repository.existsById(1))
			repository.deleteById(1);
	}
	
	@Test
	public void testCount_CountNumberofEmployeesFromDatabase() {
		System.out.println("Total number of employees: "+repository.count());
	}
	
	@Test
	public void testFindByCharacterInStartingOfName() {
		List<Employee> list = repository.findByNameStartsWith("J");
		list.forEach((c) -> System.out.println(c));
	}
	
	@Test
	public void testFindByName_CustomFinder() {
		List<Employee> empList = repository.findByName("James");
		empList.forEach((p) -> System.out.println(p));
	}
	
	@Test
	public void testByAgeRange() {
		List<Employee> listbyage = repository.findByAgeBetween(28, 32);
		listbyage.forEach((s) -> System.out.println(s));
	}
	
	@Test
	public void testfindAllPaginggForAge() {
		PageRequest pageable = PageRequest.of(0, 2);
		Iterable<Employee> results = forPagingAndSorting.findAll(pageable);
		results.forEach(p -> System.out.println(p.getAge()));
	}
	
	@Test
	public void testfindAllSorting() {
		List<Employee> emps = (List<Employee>) forPagingAndSorting.findAll(Sort.by(Sort.Direction.ASC, "age"));
		emps.forEach(k -> System.out.println(k));
	}
}