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
import Team4CA.LMS.service.LeaveRequestService;
import Team4CA.LMS.service.RejectedOutcomeService;

@RestController
public class RejectedEmailController {
	
	@Autowired
	private LeaveRequestService lrs;
	
	@Autowired 
	private RejectedOutcomeService rejectednotificationService;
	
	@RequestMapping(value="/reject/{i}")
	public void approve(@PathVariable("i") Integer i,HttpServletResponse response) throws IOException {
		
		LeaveRequest l = lrs.findLeaveById(i);
		Employee emp = l.getEmployee();
		try {
			rejectednotificationService.sendNotification(emp);
		}catch(MailException e) {

		}
		response.sendRedirect("http://localhost:8080/pendingLeavelist");
	}
}
