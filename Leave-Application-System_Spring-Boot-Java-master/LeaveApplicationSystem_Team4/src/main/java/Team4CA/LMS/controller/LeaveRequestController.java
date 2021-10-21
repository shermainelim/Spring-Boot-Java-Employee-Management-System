package Team4CA.LMS.controller;

import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import Team4CA.LMS.domain.Employee;
import Team4CA.LMS.domain.LeaveRequest;
import Team4CA.LMS.domain.LeaveStatus;
import Team4CA.LMS.domain.LeaveType;
import Team4CA.LMS.domain.Overtime;
import Team4CA.LMS.domain.OvertimeStatus;
import Team4CA.LMS.service.EmployeeService;
import Team4CA.LMS.service.EmployeeServiceImpl;
import Team4CA.LMS.service.LeaveRequestService;
import Team4CA.LMS.service.LeaveRequestServiceImpl;
import Team4CA.LMS.service.LeaveTypeService;
import Team4CA.LMS.service.LeaveTypeServiceImpl;
import Team4CA.LMS.service.OvertimeService;
import Team4CA.LMS.service.OvertimeServiceImpl;

@Controller
@RequestMapping("/leave")
public class LeaveRequestController {
	
	@Autowired
	private LeaveRequestService lrservice;
	
	@Autowired
	public void setLeaveRequestService(LeaveRequestServiceImpl lrserviceImpl) {
		this.lrservice = lrserviceImpl;
	}
	
	@Autowired
	private EmployeeService empservice;
	
	@Autowired
	public void setEmployeeService(EmployeeServiceImpl empserviceImpl) {
		this.empservice = empserviceImpl;
	}
	
	@Autowired
	private LeaveTypeService ltservice;
	
	@Autowired
	public void setLeaveTypeService(LeaveTypeServiceImpl ltserviceImpl) {
		this.ltservice = ltserviceImpl;
	}
	
	@RequestMapping(value = "/history")
	public String viewLeaveHistory(Model model , HttpSession session)
	{
		//here
		int id = (int) session.getAttribute("userIdF");
		model.addAttribute("leaves", lrservice.findLeaveRequestByEmployeeId(id));
		return "staff/leave";
	}
	
	@RequestMapping(value = "/apply")
	public String applyLeave(Model model,HttpSession session)
	{
		int id =(int) session.getAttribute("userIdF");
		model.addAttribute("leave", new LeaveRequest());
		model.addAttribute("ltnames",ltservice.findAllLeaveTypeNameByEmpId(id)); //neeed to check later
		return "staff/leaveform";
	}
	
	@RequestMapping(value = "/cancel/{id}")
	public String cancelLeave(@PathVariable("id") Integer id,HttpSession session)
	{
		int userid =(int) session.getAttribute("userIdF");
		LeaveRequest lr = lrservice.findLeaveById(id);
		LeaveType lt =  lr.getLeaveType();
		lt.setLeaveDayLeft(lt.getLeaveDayLeft()+lr.getDuration());
		ltservice.saveLeaveType(lt);
		lrservice.cancelLeave(lrservice.findLeaveById(id));
		return "forward:/leave/history";
	}
	
	@RequestMapping(value = "/update/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model,HttpSession session)
	{
		int userid =(int) session.getAttribute("userIdF");
		LeaveRequest lr = lrservice.findLeaveById(id);
		LeaveType lt =  lr.getLeaveType();
		lt.setLeaveDayLeft(lt.getLeaveDayLeft()+lr.getDuration());
		ltservice.saveLeaveType(lt);
		model.addAttribute("leave", lrservice.findLeaveById(id));
		model.addAttribute("ltnames",ltservice.findAllLeaveTypeNameByEmpId(userid));
		return "staff/leaveform";
	}
	
	@RequestMapping(value = "/save")
	public String saveLeaveApplication (@ModelAttribute("leave")@Valid LeaveRequest leavereq,
			BindingResult bindingResult, Model model,HttpSession session) 
	    {
		if(bindingResult.hasErrors()) {
			int id =(int) session.getAttribute("userIdF");
			model.addAttribute("leave", new LeaveRequest());
			model.addAttribute("ltnames",ltservice.findAllLeaveTypeNameByEmpId(id));
			return "forward:/leave/apply";
		}
		Date start = leavereq.getLeaveStartDate();
		Date end = leavereq.getLeaveEndDate();
		long duration = duration(start, end);
		LeaveRequest lNew = leavereq;
		lNew.setDuration(duration);
		lNew.setLeaveStatus(LeaveStatus.APPLIED);
		int id =(int) session.getAttribute("userIdF");
		Employee emp = empservice.findEmployeeById(id);
		lNew.setEmployee(emp);
		//leavereq.getLeaveType().getId();
	//	LeaveType lt = (LeaveType) model.getAttribute("leave.leaveType");
		LeaveType lt = ltservice.findLeaveTypeIdBySaw(id, leavereq.getLeaveType().getLeaveTypeName());
		if(duration > lt.getLeaveDayLeft() )
		{
			model.addAttribute("error","You don't have enough leave");
			//Here
			return "forward:/leave/apply";
		}
		else if(start.after(end))
		{
			model.addAttribute("error","Your Start Date is after the End Date");
			//Here
			return "forward:/leave/apply";
		}
		double leavedayleft = lt.getLeaveDayLeft() - duration ;
		lt.setLeaveDayLeft(leavedayleft);
		lNew.setLeaveType(lt);
		lrservice.saveLeave(lNew);
		
		//Modification for Email Interaction (Start)
		return "forward:/newreq";
		//Modification for Email Interaction (Start)
	}
	
	
	//home page for staff
	@RequestMapping(value = "/")
	public String Home()
	{
		return "staff/staffhome";
	}

	@Autowired
	private OvertimeService ovservice;
	
	@Autowired
	public void setOvertimeService(OvertimeServiceImpl ovserviceImpl) {
		this.ovservice = ovserviceImpl;
	}
	
	@RequestMapping(value = "/addovertime")
	public String addOvertime(Model model)
	{
		model.addAttribute("over", new Overtime());
		return "staff/overtimeform";
	}
	
	@RequestMapping(value = "/saveovertime")
	public String saveOvertime (@ModelAttribute("over")@Valid Overtime over,
			BindingResult bindingResult, Model model,HttpSession session)
	    {
		if(bindingResult.hasErrors()) {
			return "staff/overtimeform";
		}
		Overtime oNew = over;
		oNew.setOvertimeStatus(OvertimeStatus.APPLIED);
		int id =(int) session.getAttribute("userIdF");
		Employee emp = empservice.findEmployeeById(id);
		oNew.setEmployee(emp);
		ovservice.saveOver(over);
		return "forward:/leave/overtimehistory";
	}
	
	@RequestMapping(value ="/overtimehistory")
	public String viewOvertimeHistory(Model model,HttpSession session)
	{
		int id =(int) session.getAttribute("userIdF");
		model.addAttribute("overtimes", ovservice.findOvertimeByEmployeeId(id));
		return "staff/overtimehistory";
		
	}
	
	@RequestMapping(value = "/updateovertime/{id}")
	public String showUpdateOvertimeForm(@PathVariable("id") Integer id, Model model)
	{
		model.addAttribute("over", ovservice.findOverById(id));
		return "staff/overtimeform";
	}
	
	
	
	
	//Methods
	
	public static long duration(Date d1, Date d2) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(d1);
		cal.setTime(d2);
		long dur = (d2.getDate() - d1.getDate()) + 1;
		return dur;
	}

	

}
