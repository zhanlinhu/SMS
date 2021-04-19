package com.sms.controller;

import com.sms.entity.Admin;
import com.sms.entity.Student;
import com.sms.entity.Teacher;
import com.sms.entity.User;
import com.sms.service.AdminService;
import com.sms.service.StudentService;
import com.sms.service.TeacherService;
import com.sms.utils.RepData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value="/pswd")
public class PasswordController extends BaseController{
	@Autowired
	AdminService adminService;
	
	@Autowired
	TeacherService teacherService;
	
	@Autowired
	StudentService studentService;
	
	@RequestMapping(value="/page")
	public ModelAndView toPswdPage(ModelAndView mav) {
		mav = new ModelAndView("changePwd");
		return mav;
	}
	
	@ResponseBody
	@RequestMapping(value="/setting")
	public RepData<String> setting(HttpSession session, String oldPswd, String newPswd) {
		oldPswd = oldPswd.toUpperCase();
		newPswd = newPswd.toUpperCase();
		User user = (User) session.getAttribute(USER);
		int res = 0;
		if (ADMIN.equals(user.getUserType())) {
			Admin admin = (Admin)user;
			if(admin.getPassword().equals(oldPswd)) {
				admin.setPassword(newPswd);
				session.setAttribute(USER, admin);
				res = adminService.update(admin);
				if (res > 0) return success(RESULT_TRUE);
			} else {
				return fail(PASSWORD_ERROR);
			}
		} else if (TEACHER.equals(user.getUserType())) {
			Teacher t = (Teacher)user;
			if(t.getPassword().equals(oldPswd)) {
				t.setPassword(newPswd);
				res = teacherService.updateTeacher(t);
				if (res > 0) return success(RESULT_TRUE);
			} else {
				return fail(PASSWORD_ERROR);
			}
		} else {
			Student stu = (Student)user;
			if(stu.getPassword().equals(oldPswd)) {
				stu.setPassword(newPswd);
				res = studentService.updateStudent(stu);
				if (res > 0) return success(RESULT_TRUE);
			} else {
				return fail(PASSWORD_ERROR);
			}
		}
		return fail(OPRATION_ERROR);
	}
	
}
