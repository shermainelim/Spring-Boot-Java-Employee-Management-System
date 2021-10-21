package Team4CA.LMS.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import Team4CA.LMS.domain.LeaveRequest;
import Team4CA.LMS.domain.LeaveStatus;
import Team4CA.LMS.repo.LeaveRequestRepo;
import Team4CA.LMS.service.LeaveRequestService;
import Team4CA.LMS.service.LeaveRequestServiceImpl;

@Controller
public class ManagerLeaveController {
	@Autowired
	private LeaveRequestService lrs;

	@Autowired
	public void setLeaveRequestService(LeaveRequestServiceImpl lrsImpl) {
		lrs = lrsImpl;
	}

	@Autowired
	private LeaveRequestRepo lrrepo;

	@RequestMapping(value = "/pendingLeavelist")
	public String pendingLeaveList(Model model) {
		model.addAttribute("pendingLeaveList", lrs.findByLeaveStatus(LeaveStatus.APPLIED));
		return "manager/ManagerLeaveToApproveList";
	}

	@RequestMapping(value = "/leaveApproved/{id}")
	public String leaveApproved(@PathVariable("id") Integer id, Model model) {

		boolean b = lrs.approvedLeaveByManager(id);

		if (b == true) {
			model.addAttribute("leaveApproveflag", 1);
		} else {
			model.addAttribute("leaveApproveflag", 2);
		}
		//Modification for Email Interaction(Start)
		return "forward:/approve/"+id;
		//Modification for Email Interaction(End)
	}

	@RequestMapping(value = "/leaveRejected/addcomment/{id}") // {id}
	public String leaveRejectWithComment(@PathVariable("id") Integer id, Model model) {

		LeaveRequest l =  lrs.findLeaveById(id);
		model.addAttribute("leave",l);
		return "manager/ManagerLeaveAddComment";
	}
	
	@RequestMapping(value = "/leaveRejected") // {id}
	public String leaveRejected(@ModelAttribute("leave") LeaveRequest l, Model model) {
		
		int i = l.getId();
		boolean b = lrs.rejectLeaveByManager(l);

		if (b == true) {
			model.addAttribute("leaveRejectflag", 1);
		} else {
			model.addAttribute("leaveRejectflag", 2);
		}
		
		//Modification for Email Interaction(Start)
		return "forward:/reject/"+i;
	}
	
	//by summer
	@RequestMapping(value = "/managerHistory")
	public String viewLeaveHistory(Model model)
	{
		model.addAttribute("leaves", lrs.findAll());
		return "manager/leave";
	}
	/*
	 * @RequestMapping (value = "/addComment/{id}") public String
	 * addComment(@PathVariable ("id") Integer id, Model model,String s) {
	 * lrs.addComment(s, id); return "forward:/pendingLeavelist"; }
	 */

}