package com.sms.service;

import com.sms.dto.CourseDto;
import com.sms.entity.Course;
import com.sms.utils.page.Pagination;

import java.util.List;
import java.util.Map;

public interface CourseService {

	int getTotalItemsCount(String searchKey);

	List<CourseDto> getCourseList(Pagination<Course> page, String searchKey);

	int addCourse(Course course);

	int updateCourse(Course course);

	int deleteCourse(Course c);

	int deleteCourse(List<Integer> list);

	int getTotalItemsCountByTid(String id);

	List<Course> getCourseListByTid(Pagination<Course> page, String id);

	int getTotalItemsCountBySid(Map map);

	List<Course> getCourseListBySid(Map map);

	int completeCourse(Course course);
}
