package com.sms.controller;

import com.alibaba.fastjson.JSON;
import com.sms.dto.StudentVo;
import com.sms.entity.Student;
import com.sms.entity.Teacher;
import com.sms.service.StudentService;
import com.sms.utils.MD5Util;
import com.sms.utils.RepData;
import com.sms.utils.SIDUtil;
import com.sms.utils.page.Pagination;
import org.apache.poi.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value="/student")
public class StudentController extends BaseController{
	@Autowired
	private StudentService studentService;
	
	@ResponseBody
	@RequestMapping(value="/list")
	public RepData<List<StudentVo>> getStudentList(@RequestParam(defaultValue="0")int curr,
										  @RequestParam(defaultValue="20")int nums,
										  @RequestParam(defaultValue="")String searchKey) {
		
		Pagination<Student> page = new Pagination<Student>();
		
		page.setTotalItemsCount(studentService.getTotalItemsCount(searchKey));
		page.setPageSize(nums);
		page.setPageNum(curr);
		
		List<StudentVo> list = studentService.getStudentList(page,searchKey);

		return success(list,page.getTotalItemsCount());
	}
	
	
	/**
	 * 返回选修了我课程的学生列表
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/stulist")
	public RepData<List<StudentVo>> getMyStudentList(@RequestParam(defaultValue="0")int curr,
			@RequestParam(defaultValue="20")int nums,
			@RequestParam(required=false) Integer baseCourseId, HttpSession session) {
		Teacher t = (Teacher) session.getAttribute(USER);
		Pagination<StudentVo> page = new Pagination<StudentVo>();
		
		page.setTotalItemsCount(studentService.getTotalItemsCountByTid(t.getId(), baseCourseId));
		page.setPageSize(nums);
		page.setPageNum(curr);
		
		List<StudentVo> list = studentService.getStudentListByTid(page, t.getId(), baseCourseId);

		return success(list,page.getTotalItemsCount());
	}
	
	
	
	@RequestMapping(value="/addPage")
	public ModelAndView toAddPage() {
		return new ModelAndView("/studentAdd");
	}
	
	/**
	 * 增加，或者修改studnet
	 * @param opType
	 * @param stu
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/add")
	public RepData<String> addStudent(@RequestParam(defaultValue="2") int opType, Student stu) {
		int res = 0;
		//stu.setGrade(SIDUtil.returnGrade(stu.getGrade()));
		if (opType == 0) {
			try {
				stu.setPassword(stu.getPassword().toUpperCase());
				res = studentService.addStudent(stu);
			} catch (Exception e) {
				return fail("添加失败！学号重复！");
			}
			if (res > 0)
				return success(RESULT_TRUE);
			return fail(ADD_ERROR);
		} else if (opType == 1) {
			stu.setPassword(null);
			res = studentService.updateStudent(stu);
			if (res > 0) return success(RESULT_TRUE);
			return fail(UPDATE_ERROR);
		}
		return fail(ERROR);
	}
	
	/**
	 * 重置密码
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/resetPswd")
	public RepData<String> resetPasswrd(String id) {
		Student stu = new Student();
		stu.setId(id);
		stu.setPassword(MD5Util.MD5("123456"));
		if (studentService.updateStudent(stu) > 0) return success(RESULT_TRUE);
		return fail(UPDATE_ERROR);
	}
	
	@ResponseBody
	@RequestMapping(value="/delete")
	public RepData<String> deleteStudnet(Student stu) {
		if (studentService.deleteStudent(stu) > 0) return success(RESULT_TRUE);
		return fail(DELETE_ERROR);
	}
	
	/**
	 * 批量删除
	 * @param stuIds
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/deleteList")
	public RepData<String> deleteStudnetList(String stuIds) {
		List<String> list = new ArrayList<String>();
		try {
			String[] ids = stuIds.split(",");
			for (String id: ids) {
				list.add(id);
			}
			if (studentService.deleteStudent(list) > 0) {
				return success(RESULT_TRUE);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return fail(DELETE_ERROR);
		}
		return fail(DELETE_ERROR);
	}
	
	@ResponseBody
	@RequestMapping("/import")  
	public RepData<String> impotr(HttpServletRequest request, MultipartFile file) {
	     //获取上传的文件  
	     InputStream in = null;
		try {
			in = file.getInputStream();
			//数据导入  
			int res = studentService.importExcelInfo(in,file);
			if (res > 0) {
				return success(RESULT_TRUE,0);
			} else {
				return fail(RESULT_FALSE,0);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return fail(ERROR,0);
		} finally {
			if (in != null)
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}
	
	@RequestMapping(value="/courses")
	public ModelAndView toChoiceCoursePage() {
		return new ModelAndView("choiceCourse");
	}

	@ResponseBody
	@RequestMapping(value = "/getAutoSid")
	public RepData getAutoSid(Student student){

		String result = studentService.getAutoSid(student,null,null);
		if(result != null && "".equals(result)){
			return fail(ERROR);
		}
		return success(result,0);
	}
}
