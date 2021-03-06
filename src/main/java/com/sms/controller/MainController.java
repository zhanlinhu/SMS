package com.sms.controller;


import com.sms.entity.*;
import com.sms.service.CourseService;
import com.sms.service.MajorService;
import com.sms.utils.SIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping(value="/main")
public class MainController extends BaseController{
	
	@Autowired
	CourseService courseService;

	@Autowired
	MajorService majorService;
	
	
	@RequestMapping(value="/index")
	public ModelAndView toIndexPage(HttpSession session) {
		User user = (User) session.getAttribute(USER);
		if (user.getUserType().equals(ADMIN)) {
			user = (Admin) user;
		} else if (user.getUserType().equals(TEACHER)) {
			user = (Teacher) user;
		} else if (user.getUserType().equals(STUDENT)) {
			user = (Student) user;
		}
		ModelAndView mav = new ModelAndView("index");
		mav.addObject("user");
		System.out.println(SIDUtil.collegeNum);
		System.out.println(SIDUtil.majorNum);
		return mav;
	}
	
	@RequestMapping(value="/student")
	public ModelAndView toStudentListPage(HttpSession session) {
		return new ModelAndView("studentList");
	}
	

	@RequestMapping(value="/teacher")
	public ModelAndView toTeacherListPage() {
		return new ModelAndView("teacherList");
	}
	
	@RequestMapping(value="/course")
	public ModelAndView toCourseListPage(HttpSession session, ModelAndView mav) {
		String userType = ((User) session.getAttribute(USER)).getUserType();
		if (userType.equals(ADMIN)) {
			mav = new ModelAndView("courseList");
		} else if(userType.equals(TEACHER)){
			mav = new ModelAndView("teacher/courseList");
		} else {
			mav = new ModelAndView("student/courseList");
		}
		return mav;
	}
	
	
	@RequestMapping(value="/score")
	public ModelAndView toScoreListPage(HttpSession session, ModelAndView mav) {
		User user = (User) session.getAttribute(USER);
		String userType = user.getUserType();
		if (userType.equals(ADMIN)) {
			mav = new ModelAndView("scoreList");
		} else if(userType.equals(TEACHER)){
			mav = new ModelAndView("teacher/studentScoreList");
			List<Course> list = courseService.getCourseListByTid(null, ((Teacher)user).getId());
			mav.addObject("courseList", list);
		} else {
			mav = new ModelAndView("student/scoreList");
		}
		return mav;
	}
	
	@RequestMapping(value="/notice")
	public ModelAndView toNoticeListPage(ModelAndView mav) {
		mav = new ModelAndView("noticeList");
		return mav;
	}

	@RequestMapping(value="/major")
	public ModelAndView toMajorListPage(ModelAndView mav) {
		mav = new ModelAndView("majorList");
		return mav;
	}
	
	@RequestMapping(value="/system")
	public ModelAndView toSystemListPage(ModelAndView mav) {
		mav = new ModelAndView("systemAuth");
		return mav;
	}
	
}
