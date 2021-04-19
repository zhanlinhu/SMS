package com.sms.dao;

import com.sms.dto.CourseDto;
import com.sms.entity.Course;
import com.sms.utils.page.Pagination;

import java.util.List;
import java.util.Map;

public interface CourseDao {
	int deleteByPrimaryKey(Integer id);

    int insert(Course record);

    int insertSelective(Course record);

    Course selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Course record);

	List<CourseDto> getCourseList(Pagination<Course> page, String searchKey);

	int getTotalItemsCount(String searchKey);

	int deleteInList(List<Integer> list);

	int getTotalItemsCountByTid(String id);

	List<Course> getCourseListByTid(Pagination<Course> page, String id);

	int getTotalItemsCountBySid(Map map);

	List<Course> getCourseListBySid(Map map);
}