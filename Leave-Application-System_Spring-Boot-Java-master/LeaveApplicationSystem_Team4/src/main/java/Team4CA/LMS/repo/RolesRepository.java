package Team4CA.LMS.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Team4CA.LMS.domain.RoleSecurity;



@Repository("roleRepository")
public interface RolesRepository extends JpaRepository<RoleSecurity, Long> {
	
	RoleSecurity findByRole(String role);
}
