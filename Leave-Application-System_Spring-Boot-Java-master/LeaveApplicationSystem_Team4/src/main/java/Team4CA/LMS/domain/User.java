package Team4CA.LMS.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotBlank
	@Length( min = 2, max = 30)
	private String userName;
	@NotBlank
	@Length( min =5, max =200)
	private String password;
	
	private UserType userType;
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(int id, String userName, String password, UserType userType) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.userType = userType;
	}
	public User(String userName, String password, UserType userType) {
		super();
		this.userName = userName;
		this.password = password;
		this.userType = userType;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public UserType getUserType() {
		return userType;
	}
	public void setUserType(UserType userType) {
		this.userType = userType;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", password=" + password + ", userType=" + userType + "]";
	}
	
	
	

}
