package Team4CA.LMS.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name= "user")
public class UserSecurity {
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private int id;
	
	@Column(name= "email")
	private String email;
	
	@Column(name= "firstname")
	private String firstname;
	
	@Column(name= "lastname")
	private String lastname;
	
	@Column(name= "password")
	private String password;
	
	@Column(name= "active")
	private int active;
	
	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	@ManyToMany(cascade= CascadeType.ALL)
	@JoinTable(name="user_role", joinColumns=@JoinColumn(name="user_id"), inverseJoinColumns=@JoinColumn(name="role_id"))
	private Set<RoleSecurity> roles;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}



	public Set<RoleSecurity> getRoles() {
		return roles;
	}

	public void setRoles(Set<RoleSecurity> roles) {
		this.roles = roles;
	}
	
	
}
