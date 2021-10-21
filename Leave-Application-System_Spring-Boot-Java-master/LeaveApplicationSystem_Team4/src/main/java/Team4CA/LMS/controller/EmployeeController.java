package Team4CA.LMS.controller;

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
import Team4CA.LMS.service.EmployeeService;
import Team4CA.LMS.service.EmployeeServiceImpl;
import Team4CA.LMS.service.UserSecurityService;
import Team4CA.LMS.service.UserSecurityServiceImpl;

	
@Controller
@RequestMapping("/emp")
public class EmployeeController {
		
		@Autowired
		protected EmployeeService eservice ;
		
		@Autowired
		public void setEmployeeService(EmployeeServiceImpl serviceImpl) {
			this.eservice = serviceImpl;		
		}
		
		@Autowired
		protected UserSecurityService uservice ;
		
		@Autowired
		public void setUserService(UserSecurityServiceImpl uerviceImpl) {
			this.uservice = uerviceImpl;		
		}
		
		@RequestMapping(value = "/list")
		public String list(Model model) {
			model.addAttribute("elist", eservice.findAll());
			return "emp/employee-list";
		}

		@RequestMapping(value = "/add")
		public String addForm(Model model) {
			Employee emp = new Employee();
			model.addAttribute("employee", emp);
			model.addAttribute("emails",uservice.findEmail());
			return "emp/employee-form";
		}
		
		@RequestMapping(value = "/edit/{id}")
		public String editForm(@PathVariable("id") Integer id, Model model) {
			Employee emp = eservice.findEmployeeById(id);
			model.addAttribute("employee", emp);
			return "emp/employee-form";
		}
		@RequestMapping(value = "/save")
		public String saveEmployee(@ModelAttribute("employee") @Valid Employee emp, BindingResult result
				, Model model,HttpSession session) {
			int id =(int) session.getAttribute("userIdF");
			if (result.hasErrors()) {
				model.addAttribute("employee", emp);
			//	model.addAttribute("email",uservice.findEmailByUserId(id));
				model.addAttribute("emails",uservice.findEmail());
				return "emp/employee-form";
			}

			eservice.saveEmployee(emp);
		
			return "redirect:/emp/list";
		}
		
		@RequestMapping(value = "/delete/{id}")
		public String deleteEmployee(@PathVariable("id") Integer id) {
			eservice.deleteEmployee(id);
			return "forward:/emp/list";
		}
}

