package Team4CA.LMS.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Team4CA.LMS.domain.Employee;
import Team4CA.LMS.domain.LeaveRequest;
import Team4CA.LMS.service.ApprovedOutcomeService;
import Team4CA.LMS.service.LeaveRequestService;

@RestController
public class ApprovedEmailController {
	@Autowired
	private LeaveRequestService lrs;
	
	@Autowired 
	private ApprovedOutcomeService approvednotificationService;
	
	@RequestMapping(value="/approve/{id}")
	public void approve(@PathVariable("id") Integer id,HttpServletResponse response) throws IOException {
		
		LeaveRequest l = lrs.findLeaveById(id);
		Employee emp = l.getEmployee();
		try {
			approvednotificationService.sendNotification(emp);
		}catch(MailException e) {

		}
		response.sendRedirect("http://localhost:8080/pendingLeavelist");
	}
}
