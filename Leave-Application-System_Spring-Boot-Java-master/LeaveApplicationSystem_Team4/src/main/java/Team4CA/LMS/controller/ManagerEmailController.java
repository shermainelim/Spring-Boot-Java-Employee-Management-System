package Team4CA.LMS.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Team4CA.LMS.service.EmailManagerService;

@RestController
public class ManagerEmailController {
	
	@Autowired 
	private EmailManagerService managernotificationService;
	
	@RequestMapping("/newreq")
	public void newreq(HttpServletResponse response) throws IOException {

		try {
			managernotificationService.sendManagerNotification();
		}catch(MailException e) {

		}
		response.sendRedirect("http://localhost:8080/leave/history");;
	}

}
