package Team4CA.LMS.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import Team4CA.LMS.domain.Overtime;
import Team4CA.LMS.domain.OvertimeStatus;

public interface OvertimeRepo extends JpaRepository<Overtime, Integer> {
	public List<Overtime> findOvertimeByOvertimeStatus(OvertimeStatus os);
	
	public List<Overtime> findOvertimeByEmployeeId(Integer id);
}
