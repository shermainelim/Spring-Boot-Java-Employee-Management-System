package Team4CA.LMS;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import Team4CA.LMS.domain.Employee;
import Team4CA.LMS.domain.Gender;
import Team4CA.LMS.domain.LeaveRequest;
import Team4CA.LMS.domain.LeaveStatus;
import Team4CA.LMS.domain.LeaveType;
import Team4CA.LMS.domain.Overtime;
import Team4CA.LMS.domain.OvertimeStatus;
import Team4CA.LMS.domain.Role;
import Team4CA.LMS.domain.RoleSecurity;
import Team4CA.LMS.domain.UserSecurity;
import Team4CA.LMS.repo.EmployeeRepo;
import Team4CA.LMS.repo.LeaveRequestRepo;
import Team4CA.LMS.repo.LeaveTypeRepo;
import Team4CA.LMS.repo.OvertimeRepo;
import Team4CA.LMS.service.UserSecurityService;
@SpringBootApplication
public class Team4CaApplication implements CommandLineRunner{
	@Autowired
	EmployeeRepo erepo;
	@Autowired
	LeaveRequestRepo lrrepo;
	@Autowired
	LeaveTypeRepo ltrepo;
	@Autowired
	OvertimeRepo orepo;
	
	@Autowired
	 private UserSecurityService userService;

	/*
	 * @Autowired UserRepo urepo;
	 */

	public static void main(String[] args) {
		SpringApplication.run(Team4CaApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		//Admincreate - Store to db
		RoleSecurity role = new RoleSecurity();
		role.setId(1);
		role.setRole("ADMIN");
		userService.saveRole(role);
		
		role = new RoleSecurity();
		role.setId(2);
		role.setRole("STAFF");
		userService.saveRole(role);
		
		role = new RoleSecurity();
		role.setId(3);
		role.setRole("MANAGER");
		userService.saveRole(role);
		
		UserSecurity user = new UserSecurity();	
		user.setId(1);
		user.setEmail("admin@gmail.com");
		user.setPassword("abc123");
		user.setFirstname("Admin");
		user.setLastname("Admin");
		userService.saveAdminUser(user);
	}
	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {
			DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String d0 = "2014-02-11";
			Date dob = sdf.parse(d0);
			
			String d1 = "2017-02-11";
			Date s1 = sdf.parse(d1);
			String d2 = "2020-06-11";
			Date end1 = sdf.parse(d2);
			//create Employee object
			Employee e1 = new Employee ("Summer","summer@gmail.com","Vivaldi", Role.ADMIN,Gender.FEMALE,dob,s1,end1);
			erepo.save(e1);
			String d3 = "2019-05-11";
			Date leavestart1 = sdf.parse(d3);
			String d4 = "2019-05-15";
			Date leaveend1 = sdf.parse(d4);
			
			Employee e2 = new Employee ("Saw","saw@gmail.com","Vivaldi", Role.ADMIN,Gender.MALE,dob,s1,end1);
			erepo.save(e2);
			Employee e3 = new Employee ("KyawThiha","Kyaw@gmail.com","Vivaldi",Role.ADMIN,Gender.MALE,dob,s1,end1);
			erepo.save(e3);
			Employee e4 = new Employee ("Lance","Lance@gmail.com","Vivaldi",Role.MANAGER,Gender.MALE,dob,s1,end1);
			erepo.save(e4);
			
			//create LeaveType object
			
			LeaveType lt1 = new LeaveType("Annual Leave",18,9,e1);
			ltrepo.save(lt1);
			LeaveType lt2 = new LeaveType("Medical Leave",60,40,e2);
			ltrepo.save(lt2);
			
			
			//create LeaveRequest Object
			
			LeaveRequest lr1 = new LeaveRequest(leavestart1,leaveend1,5,"Annual Leave"," ",LeaveStatus.APPLIED,e1,lt1);
			lrrepo.save(lr1);
			LeaveRequest lr2 = new LeaveRequest(leavestart1,leaveend1,5,"Medical Leave"," ",LeaveStatus.APPLIED,e2,lt2);
			lrrepo.save(lr2);
			
			//create OverTime Object
			
			
			//I think we can fix this later by SAW
			/*
			 * Overtime o1 = new
			 * Overtime(LocalDateTime.of(2020,4,10,18,0,0),LocalDateTime.of(2020,4,10,21,0,0
			 * ),OvertimeStatus.APPLIED,3,"Urgent report",e1); orepo.save(o1);
			 */
			
			Overtime o1 = new Overtime(s1, end1, OvertimeStatus.APPLIED, 3, "Urgent", e1);
			Overtime o2 = new Overtime(s1, end1, OvertimeStatus.APPLIED, 3, "Urgent", e1);
			Overtime o3 = new Overtime(s1, end1, OvertimeStatus.APPLIED, 3, "Urgent", e1);
			Overtime o4 = new Overtime(s1, end1, OvertimeStatus.APPLIED, 3, "Urgent", e3);
			
			orepo.save(o1);
			orepo.save(o2);
			orepo.save(o3);
			orepo.save(o4);
		};
	}
	
}




