package Team4CA.LMS.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class EmployeeTotalOvertime {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private double total;
	
	private int employeeId;

	public EmployeeTotalOvertime(int id, double total, int employeeId) {
		super();
		this.id = id;
		this.total = total;
		this.employeeId = employeeId;
	}

	public EmployeeTotalOvertime() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	
	
}
