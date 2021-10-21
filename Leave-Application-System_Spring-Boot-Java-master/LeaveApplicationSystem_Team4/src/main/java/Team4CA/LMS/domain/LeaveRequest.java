package Team4CA.LMS.domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table (name ="leave_request")
public class LeaveRequest {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date leaveStartDate;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date leaveEndDate;

	private double duration;
	private String description;
	private String comment;
	private LeaveStatus leaveStatus;
	
	
	@ManyToOne
	@JoinColumn(name ="employee_Id")
	private Employee employee;
	
	@ManyToOne
	@JoinColumn(name ="leaveType_Id")
	private LeaveType leaveType;

	public LeaveRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LeaveRequest(Date leaveStartDate, Date leaveEndDate, double duration, String description,
			String comment, LeaveStatus leaveStatus, Employee employee, LeaveType leaveType) {
		super();
		this.leaveStartDate = leaveStartDate;
		this.leaveEndDate = leaveEndDate;
		this.duration = duration;
		this.description = description;
		this.comment = comment;
		this.leaveStatus = leaveStatus;
		this.employee = employee;
		this.leaveType = leaveType;
	}

	public LeaveRequest(Date leaveStartDate, Date leaveEndDate, double duration, String description,
			LeaveStatus leaveStatus, LeaveType leaveType) {
		super();
		this.leaveStartDate = leaveStartDate;
		this.leaveEndDate = leaveEndDate;
		this.duration = duration;
		this.description = description;
		this.leaveStatus = leaveStatus;
		this.leaveType = leaveType;
	}

	public LeaveRequest(Date leaveStartDate, Date leaveEndDate,  double duration, String description) {
		super();
		this.leaveStartDate = leaveStartDate;
		this.leaveEndDate = leaveEndDate;
		this.duration = duration;
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getLeaveStartDate() {
		return leaveStartDate;
	}

	public void setLeaveStartDate(Date leaveStartDate) {
		this.leaveStartDate = leaveStartDate;
	}

	public Date getLeaveEndDate() {
		return leaveEndDate;
	}

	public void setLeaveEndDate(Date leaveEndDate) {
		this.leaveEndDate = leaveEndDate;
	}

	public double getDuration() {
		return duration;
	}

	public void setDuration(double duration) {
		this.duration = duration;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public LeaveStatus getLeaveStatus() {
		return leaveStatus;
	}

	public void setLeaveStatus(LeaveStatus leaveStatus) {
		this.leaveStatus = leaveStatus;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public LeaveType getLeaveType() {
		return leaveType;
	}

	public void setLeaveType(LeaveType leaveType) {
		this.leaveType = leaveType;
	}

	@Override
	public String toString() {
		return "LeaveRequest [id=" + id + ", leaveStartDate=" + leaveStartDate + ", leaveEndDate=" + leaveEndDate
				+ ", duration=" + duration + ", description=" + description + ", comment=" + comment + ", leaveStatus="
				+ leaveStatus + ", employee=" + employee + ", leaveType=" + leaveType + "]";
	}
}
