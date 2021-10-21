package Team4CA.LMS.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import Team4CA.LMS.domain.Employee;

@Service
public class ApprovedOutcomeService {
	
	private JavaMailSender javaMailSender;
	
	@Autowired
	public ApprovedOutcomeService(JavaMailSender javaMailSender) {
		this.javaMailSender=javaMailSender;
	}
	
	public void sendNotification(Employee emp) throws MailException {
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(emp.getEmail());
		mail.setFrom("ayishajang@gmail.com");
		mail.setSubject("Leave Request Outcome");
		mail.setText("Leave Request Status: Approved. Login for more details: http://localhost:8080/");
		
		javaMailSender.send(mail);
	}

}
