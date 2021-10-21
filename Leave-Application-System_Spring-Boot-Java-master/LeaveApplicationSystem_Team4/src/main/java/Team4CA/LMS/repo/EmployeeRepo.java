package Team4CA.LMS.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Team4CA.LMS.domain.Employee;

@Repository("erepo")
public interface EmployeeRepo extends JpaRepository<Employee, Integer> {
	public List<Employee> findAllEmployeesByName(String name);
}
