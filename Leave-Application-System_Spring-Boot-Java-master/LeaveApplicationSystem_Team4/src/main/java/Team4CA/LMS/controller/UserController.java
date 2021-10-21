package Team4CA.LMS.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import Team4CA.LMS.domain.UserSecurity;
import Team4CA.LMS.service.UserSecurityService;

//return views and routing
@Controller
public class UserController {

@Autowired
private UserSecurityService userService;

@RequestMapping(value= {"/"}, method=RequestMethod.GET)
public ModelAndView login() {
ModelAndView model = new ModelAndView();
model.setViewName("user/login");
return model;
}



@RequestMapping(value= {"/signupAdmin"}, method=RequestMethod.GET)
public ModelAndView signup2() {
ModelAndView model = new ModelAndView();
UserSecurity user = new UserSecurity();
model.addObject("user", user);
model.setViewName("user/signupAdmin");

return model;
}

@RequestMapping(value= {"/signupAdmin"}, method=RequestMethod.POST)
public ModelAndView createUser(@Valid UserSecurity user, BindingResult bindingResult) {
ModelAndView model = new ModelAndView();
UserSecurity userExists = userService.findUserByEmail(user.getEmail());

if(userExists != null) {
bindingResult.rejectValue("email", "error.user", "This email already exists!");
}
if(bindingResult.hasErrors()) {
model.setViewName("user/signupAdmin");
} else {
userService.saveAdminUser(user);
model.addObject("msg", "User has been registered successfully!");
model.addObject("user", new UserSecurity());
model.setViewName("user/signupAdmin");
}

return model;
}

//Sign up STAFF

@RequestMapping(value= {"/signup"}, method=RequestMethod.GET)
public ModelAndView signup() {
ModelAndView model = new ModelAndView();
UserSecurity user = new UserSecurity();
model.addObject("user", user);
model.setViewName("user/signupStaff");

return model;
}

@RequestMapping(value= {"/signup"}, method=RequestMethod.POST)
public ModelAndView createEmpUser(@Valid UserSecurity user, BindingResult bindingResult) {
ModelAndView model = new ModelAndView();
UserSecurity userExists = userService.findUserByEmail(user.getEmail());

if(userExists != null) {
bindingResult.rejectValue("email", "error.user", "This email already exists!");
}
if(bindingResult.hasErrors()) {
model.setViewName("user/signupStaff");
} else {
userService.saveStaffUser(user);
model.addObject("msg", "User has been registered successfully!");
model.addObject("user", new UserSecurity());
model.setViewName("user/signupStaff");
}

return model;
}

//Sign up MANAGER

@RequestMapping(value= {"/signupM"}, method=RequestMethod.GET)
public ModelAndView signup3() {
ModelAndView model = new ModelAndView();
UserSecurity user = new UserSecurity();
model.addObject("user", user);
model.setViewName("user/signupM");

return model;
}

@RequestMapping(value= {"/signupM"}, method=RequestMethod.POST)
public ModelAndView createEmpUser3(@Valid UserSecurity user, BindingResult bindingResult) {
ModelAndView model = new ModelAndView();
UserSecurity userExists = userService.findUserByEmail(user.getEmail());

if(userExists != null) {
bindingResult.rejectValue("email", "error.user", "This email already exists!");
}
if(bindingResult.hasErrors()) {
model.setViewName("user/signupM");
} else {
userService.saveManagerUser(user);
model.addObject("msg", "User has been registered successfully!");
model.addObject("user", new UserSecurity());
model.setViewName("user/signupM");
}

return model;
}


//login access
@RequestMapping(value= {"/home/home","/home"}, method=RequestMethod.GET)
public String home(HttpSession session) {
ModelAndView model = new ModelAndView();
Authentication auth = SecurityContextHolder.getContext().getAuthentication();
UserSecurity user = userService.findUserByEmail(auth.getName());
//Testing
int id = user.getId();
int roleId = userService.findRoleIdSecurityByUserId(id);
session.setAttribute("userIdF", id);
model.addObject("userName", user.getFirstname() + " " + user.getLastname());
if(roleId == 2 ) {
model.setViewName("/leave/");
return "/staff/staffhome";
}
else if(roleId == 3)
{
	model.setViewName("/pendingLeavelist");
	return "manager/ManagerLeaveToApproveList" ;
}
else
{
	model.setViewName("/home/home");
	return "/home/home";
}
}

//
@RequestMapping(value= {"/home/calendar"}, method=RequestMethod.GET)
public ModelAndView calendar() {
ModelAndView model = new ModelAndView();

model.setViewName("calendar/calendar");
return model;
}

//login denied
@RequestMapping(value= {"/access_denied"}, method=RequestMethod.GET)
public ModelAndView accessDenied() {
ModelAndView model = new ModelAndView();
model.setViewName("errors/access_denied");
return model;
}
}