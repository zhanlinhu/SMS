package com.sms.dao;

import com.sms.dto.StudentVo;
import com.sms.entity.Student;
import com.sms.utils.page.Pagination;

import java.util.List;

public interface StudentDao {
    int deleteByPrimaryKey(String id);

    int insert(Student record) throws Exception;

    int insertSelective(Student record);

    Student selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);

	List<StudentVo> selectBySearchKey(Pagination<Student> page, String searchKey);

	int getTotalItemsCount(String searchKey);

	Student getStudent(Student stu);

	int deleteInList(List<String> ids);

	int insertBatch(List<Student> list);

	int getTotalItemsCountByTid(String id, Integer baseCourseId);

	List<StudentVo> getStudentListByTid(Pagination<StudentVo> page, String id,
			Integer baseCourseId);
}