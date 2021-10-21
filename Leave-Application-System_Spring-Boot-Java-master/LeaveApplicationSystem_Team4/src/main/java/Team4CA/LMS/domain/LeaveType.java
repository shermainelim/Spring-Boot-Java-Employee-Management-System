package Team4CA.LMS.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name ="leave_type")
public class LeaveType {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Length(max=50)
	private String leaveTypeName;
	
	private double leaveDay; //no of leave days based on their role
	
	private double leaveDayLeft;
	@ManyToOne
	@JoinColumn(name = "employee_Id")
	Employee employee;
	
	public LeaveType() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public LeaveType( @Length(max = 50) String leaveTypeName,  double leaveDay,
			 double leaveDayLeft, Employee employee) {
		super();
		this.leaveTypeName = leaveTypeName;
		this.leaveDay = leaveDay;
		this.leaveDayLeft = leaveDayLeft;
		this.employee = employee;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLeaveTypeName() {
		return leaveTypeName;
	}
	public void setLeaveTypeName(String leaveTypeName) {
		this.leaveTypeName = leaveTypeName;
	}
	public double getLeaveDay() {
		return leaveDay;
	}
	public void setLeaveDay(double leaveDay) {
		this.leaveDay = leaveDay;
	}
	public double getLeaveDayLeft() {
		return leaveDayLeft;
	}
	public void setLeaveDayLeft(double leaveDayLeft) {
		this.leaveDayLeft = leaveDayLeft;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	@Override
	public String toString() {
		return "LeaveType [id=" + id + ", leaveTypeName=" + leaveTypeName + ", leaveDay=" + leaveDay + ", leaveDayLeft="
				+ leaveDayLeft + "]";
	}
	
	
	
	
}
