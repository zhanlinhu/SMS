package com.sms.controller;

import com.sms.dto.CourseDto;
import com.sms.entity.Course;
import com.sms.entity.Student;
import com.sms.entity.Teacher;
import com.sms.service.CourseService;
import com.sms.utils.RepData;
import com.sms.utils.page.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value="/course")
public class CourseController extends BaseController{
	@Autowired
	CourseService courseService;
	
	@ResponseBody
	@RequestMapping(value="/list")
	public RepData<List<CourseDto>> getCourseList(@RequestParam(defaultValue="0")int curr,
												  @RequestParam(defaultValue="20")int nums,
												  @RequestParam(defaultValue="")String searchKey) {
		
		Pagination<Course> page = new Pagination<Course>();
		
		page.setTotalItemsCount(courseService.getTotalItemsCount(searchKey));
		page.setPageSize(nums);
		page.setPageNum(curr);
		
		List<CourseDto> list = courseService.getCourseList(page,searchKey);

		return success(list,page.getTotalItemsCount());
	}
	
	
	/**
	 * 返回教师自己教的课程列表
	 */
	@ResponseBody
	@RequestMapping(value="/getMyCourse")
	public RepData<List<Course>> getMyCourse(@RequestParam(defaultValue="0")int curr,
			@RequestParam(defaultValue="10")int nums, HttpSession session) {
		
		Pagination<Course> page = new Pagination<Course>();
		Teacher t = (Teacher) session.getAttribute(USER);
		page.setTotalItemsCount(courseService.getTotalItemsCountByTid(t.getId()));
		page.setPageSize(nums);
		page.setPageNum(curr);
		
		List<Course> list = courseService.getCourseListByTid(page, t.getId());

		return success(list,page.getTotalItemsCount());
	}
	
	/**
	 * 返回可选课程列表（可选：人数未满、课程开始时间在当前时间之后）
	 * @param curr
	 * @param nums
	 * @param searchKey
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/choiceList")
	public RepData<List<Course>> getCourseChoiceList(@RequestParam(defaultValue="0")int curr,
			@RequestParam(defaultValue="30")int nums, @RequestParam(defaultValue="1") int isAll,
			@RequestParam(defaultValue="")String searchKey, HttpSession session) {
		Pagination<Course> page = new Pagination<Course>();
		String sId = ((Student) session.getAttribute(USER)).getId();
		Integer majorId = ((Student) session.getAttribute(USER)).getMajorId();

		Map<String,Object> map = new HashMap<>();
		map.put("isAll",isAll);
		map.put("searchKey",searchKey);
		map.put("sId",sId);
		map.put("majorId",majorId);

		page.setTotalItemsCount(courseService.getTotalItemsCountBySid(map));
		page.setPageSize(nums);
		page.setPageNum(curr);

		map.put("page",page);
		List<Course> list = courseService.getCourseListBySid(map);

		return success(list,page.getTotalItemsCount());
	}
	
	@RequestMapping(value="/addPage")
	public ModelAndView toAddPage() {
		return new ModelAndView("courseAdd");
	}
	
	/**
	 * 增加，或者修改Course
	 * @param course
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/add")
	public RepData<String> addCourse(Course course) {
		int res = 0;
		if (course.getId() == null || course.getId().equals("")) {
			try {
				res = courseService.addCourse(course);
			} catch (Exception e) {
				e.printStackTrace();
				return fail(ADD_ERROR);
			}
			if (res > 0)
				return success(RESULT_TRUE);
			return fail(ADD_ERROR);
		} else  {
			res = courseService.updateCourse(course);
			if (res > 0)
				return success(RESULT_TRUE);
			return fail(UPDATE_ERROR);
		}
	}
	
	
	@ResponseBody
	@RequestMapping(value="/complete")
	public RepData<String> complete(Course course) {
		int res = courseService.completeCourse(course);
		if (res > 0)
			return success(RESULT_TRUE);
		return fail(OPRATION_ERROR);
	}
	
	@ResponseBody
	@RequestMapping(value="/delete")
	public RepData<String> deleteStudnet(Course c) {
		if (courseService.deleteCourse(c) > 0) return success(RESULT_TRUE);
		return fail(DELETE_ERROR);
	}
	
	/**
	 * 批量删除
	 * @param baseCourseIds
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/deleteList")
	public RepData<String> deleteStudnetList(String baseCourseIds) {
		List<Integer> list = new ArrayList<Integer>();
		try {
			String[] ids = baseCourseIds.split(",");
			for (String id: ids) {
				list.add(Integer.parseInt(id));
			}
			if (courseService.deleteCourse(list) > 0) {
				return success(RESULT_TRUE);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return fail(DELETE_ERROR);
		}
		return fail(DELETE_ERROR);
	}
}
