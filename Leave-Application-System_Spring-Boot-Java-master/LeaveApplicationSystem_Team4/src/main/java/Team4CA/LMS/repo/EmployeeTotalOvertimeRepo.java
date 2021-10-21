package Team4CA.LMS.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import Team4CA.LMS.domain.EmployeeTotalOvertime;

public interface EmployeeTotalOvertimeRepo extends JpaRepository<EmployeeTotalOvertime, Integer>{

	public EmployeeTotalOvertime findByEmployeeId(Integer id);
}
