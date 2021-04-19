package com.sms.dao;

import com.sms.dto.TeacherDto;
import com.sms.entity.Teacher;
import com.sms.utils.page.Pagination;

import java.util.List;

public interface TeacherDao {
	
    int deleteByPrimaryKey(String id);

    int insert(Teacher record);

    int insertSelective(Teacher record);

    Teacher selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Teacher record);

    int updateByPrimaryKey(Teacher record);

	Teacher selectTeacher(Teacher teacher);

	int getTotalItemsCount(String searchKey);

	int deleteInList(List<String> list);

	int insertBatch(List<Teacher> list);

	List<TeacherDto> getTeacher(Pagination<Teacher> page, String searchKey);

	List<Teacher> getTeacherForSelect(String searchKey);

}