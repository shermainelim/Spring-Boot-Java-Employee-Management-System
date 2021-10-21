package Team4CA.LMS.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.*;
import java.util.Date;

@Entity
public class Overtime {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	  @Temporal(TemporalType.DATE)
	  
	  @DateTimeFormat(pattern = "yyyy-MM-dd") private Date start;
	  
	  @Temporal(TemporalType.DATE)
	  
	  @DateTimeFormat(pattern = "yyyy-MM-dd") private Date end;
	 
	/*
	 * private LocalDateTime start; private LocalDateTime end;
	 */
	private OvertimeStatus overtimeStatus; //enum
	private double duration; //hours
	private String description;
	@ManyToOne
	@JoinColumn(name ="Employee_Id")
	private Employee employee;
	public Overtime() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Overtime(int id, Date start, Date end, OvertimeStatus overtimeStatus, double duration,
			String description, Employee employee) {
		super();
		this.id = id;
		this.start = start;
		this.end = end;
		this.overtimeStatus = overtimeStatus;
		this.duration = duration;
		this.description = description;
		this.employee = employee;
	}
	public Overtime(Date start, Date end, OvertimeStatus overtimeStatus, double duration,
			String description) {
		super();
		this.start = start;
		this.end = end;
		this.overtimeStatus = overtimeStatus;
		this.duration = duration;
		this.description = description;
	}
	public Overtime(Date start, Date end, OvertimeStatus overtimeStatus, double duration,
			String description, Employee employee) {
		super();
		this.start = start;
		this.end = end;
		this.overtimeStatus = overtimeStatus;
		this.duration = duration;
		this.description = description;
		this.employee = employee;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getStart() {
		return start;
	}
	public void setStart(Date start) {
		this.start = start;
	}
	public Date getEnd() {
		return end;
	}
	public void setEnd(Date end) {
		this.end = end;
	}
	public OvertimeStatus getOvertimeStatus() {
		return overtimeStatus;
	}
	public void setOvertimeStatus(OvertimeStatus overtimeStatus) {
		this.overtimeStatus = overtimeStatus;
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
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	@Override
	public String toString() {
		return "Overtime [id=" + id + ", start=" + start + ", end=" + end + ", overtimeStatus=" + overtimeStatus
				+ ", duration=" + duration + ", description=" + description + ", employee=" + employee + "]";
	}
	
	
}
