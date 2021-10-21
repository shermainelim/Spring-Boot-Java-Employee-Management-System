package Team4CA.LMS.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import Team4CA.LMS.domain.Employee;
import Team4CA.LMS.domain.LeaveRequest;
import Team4CA.LMS.domain.LeaveType;
import Team4CA.LMS.service.EmployeeService;
import Team4CA.LMS.service.LeaveRequestService;
import Team4CA.LMS.service.LeaveTypeService;

@Controller
@RequestMapping("/admin")
public class LeaveTypeController {

	private final EmployeeService empService;
	private final LeaveTypeService ltService;
	private final LeaveRequestService lrService;
	
	public LeaveTypeController(EmployeeService empService, LeaveTypeService ltService, LeaveRequestService lrService) {
		super();
		this.empService = empService;
		this.ltService = ltService;
		this.lrService = lrService;
	}

	@RequestMapping({"", "/"})
	public String index(Model model) {
		return "admin/index";
	}
	
	// List LeaveTypes
	
	@RequestMapping({"/leavelist"})
	public String list(Model model) {
		model.addAttribute("ltList", ltService.findAll());
		return "admin/LeaveList";
	}
	

	// Add LeaveType to employee
	
	@RequestMapping(value = "/add")
	public String addForm(Model model) {
		List<Employee> empList = empService.findAll();
		model.addAttribute("empList", empList);
		model.addAttribute("leaveType", new LeaveType());
		return "admin/leaveType-form";
	}
	
	// Edit LeaveType of employee
	
	@RequestMapping(value = "/edit/{id}")
	public String editForm(@PathVariable("id") Integer id, Model model) {
		
		LeaveType leaveType = ltService.findLeaveTypeById(id);
		model.addAttribute("leaveType", leaveType);
		
		Employee emp = leaveType.getEmployee();
		List<Employee> empList = new ArrayList<>();
		empList.add(emp);
		model.addAttribute("empList", empList);
		return "admin/leaveType-form";
	}
	
	// save LeaveTypes
	
	@RequestMapping("/save")
	public String saveLeaveType(LeaveType leaveType, Model model) {
		
		Employee emp = empService.findEmployeeById(leaveType.getEmployee().getId());
		emp.addLeaveType(leaveType);
		leaveType.setEmployee(emp);
		ltService.saveLeaveType(leaveType);
		return "forward:/admin/leavelist";
	}
	
	// delete LeaveTypes
	
	@RequestMapping(value = "/delete/{id}")
	public String deleteLeaveType(@PathVariable("id") Integer id) {
		
		List<LeaveRequest> leaveRequests = lrService.findLeaveByLeaveTypeId(id);
		
		for(LeaveRequest lr : leaveRequests) {
			lrService.cancelLeave(lr);
		}
		
		ltService.deleteLeaveType(ltService.findLeaveTypeById(id));
		return "forward:/admin/leavelist";
	}
}
