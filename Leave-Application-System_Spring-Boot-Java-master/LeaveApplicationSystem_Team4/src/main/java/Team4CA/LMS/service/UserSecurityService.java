package Team4CA.LMS.service;

import java.util.List;

import javax.validation.constraints.Email;

import Team4CA.LMS.domain.RoleSecurity;
import Team4CA.LMS.domain.UserSecurity;

public interface UserSecurityService {
	
	public UserSecurity findUserByEmail(String email);
	
	public void saveAdminUser(UserSecurity user);
	
	public void saveStaffUser(UserSecurity user);
	
	public void saveManagerUser(UserSecurity user);
	
	public void saveRole(RoleSecurity role);
	
	public int findRoleIdSecurityByUserId(Integer id);
	
	public List<String> findEmail();
}