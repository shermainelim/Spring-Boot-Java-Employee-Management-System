package Team4CA.LMS.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailManagerService {
	
	private JavaMailSender javaMailSender;
	
	@Autowired
	public EmailManagerService(JavaMailSender javaMailSender) {
		this.javaMailSender=javaMailSender;
	}
	
	public void sendManagerNotification() throws MailException {
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo("binu.arif@gmail.com");
		mail.setFrom("ayishajang@gmail.com");
		mail.setSubject("Process Leave Request");
		mail.setText("New Leave Request. Login for more details: http://localhost:8080/");
		
		javaMailSender.send(mail);
	}

}
