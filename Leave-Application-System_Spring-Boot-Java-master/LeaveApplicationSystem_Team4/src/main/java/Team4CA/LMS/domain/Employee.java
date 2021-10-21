package Team4CA.LMS.domain;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.SelectBeforeUpdate;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotBlank
	@Length(min = 3, max = 30)
	private String name;
	
	private String email;
	
	private String supervisor;
	
	private Role role;
	private Gender gender;

	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-mm-yyyy")
	private Date dateOfBirth;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-mm-yyyy")
	private Date startDate;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-mm-yyyy")
	private Date endDate;
	
	@OneToOne
	@JoinColumn(name ="user_Id")
	UserSecurity userSecurity;
	@OneToMany (mappedBy = "employee")
	private Collection<LeaveRequest> leaveRequest;
	
	@OneToMany (mappedBy = "employee")
	private List<LeaveType> leaveTypes;

	public Employee() {
		super();
		
	}

	public Employee(@NotBlank @Length(min = 3, max = 30) String name,  String email, String supervisor, Role role, Gender gender,
			Date dateOfBirth, Date startDate, Date endDate, UserSecurity user, Collection<LeaveRequest> leaveRequest, List<LeaveType> leaveTypes) {
		super();
		this.name = name;
		this.role = role;
		this.email= email;
		this.supervisor= supervisor;
		this.gender = gender;
		this.dateOfBirth = dateOfBirth;
		this.startDate = startDate;
		this.endDate = endDate;
		this.userSecurity = user;
		this.leaveRequest = leaveRequest;
		this.leaveTypes = leaveTypes;
	}

	public Employee(@NotBlank @Length(min = 3, max = 30) String name,  String email, String supervisor,Role role, Gender gender,
			Date dateOfBirth, Date startDate, Date endDate) {
		super();
		this.name = name;
		this.role = role;
		this.email= email;
		this.supervisor= supervisor;
		this.gender = gender;
		this.dateOfBirth = dateOfBirth;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public Employee(@NotBlank @Length(min = 3, max = 30) String name, String email, String supervisor, Role role, Gender gender,
			Date dateOfBirth, Date startDate, Date endDate, Collection<LeaveRequest> leaveRequest, List<LeaveType> leaveTypes) {
		super();
		this.name = name;
		this.role = role;
		this.email= email;
		this.supervisor= supervisor;
		this.gender = gender;
		this.dateOfBirth = dateOfBirth;
		this.startDate = startDate;
		this.endDate = endDate;
		this.leaveRequest = leaveRequest;
		this.leaveTypes = leaveTypes;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public UserSecurity getUser() {
		return userSecurity;
	}

	public void setUser(UserSecurity user) {
		this.userSecurity = user;
	}

	public Collection<LeaveRequest> getLeaveRequest() {
		return leaveRequest;
	}

	public void setLeaveRequest(Collection<LeaveRequest> leaveRequest) {
		this.leaveRequest = leaveRequest;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(String supervisor) {
		this.supervisor = supervisor;
	}

	public List<LeaveType> getLeaveTypes() {
		return leaveTypes;
	}

	public void addLeaveType(LeaveType leaveType) {
		this.leaveTypes.add(leaveType);
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", role=" + role + ", email=" + email +", supervisor=" + supervisor +",gender=" + gender + ", dateOfBirth="
				+ dateOfBirth + ", startDate=" + startDate + ", endDate=" + endDate + ", user=" + userSecurity
				+ ", leaveRequest=" + leaveRequest + "]";
	}
	

	
	
	
	
	
	
	
	
	 
	
}
