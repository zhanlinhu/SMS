package com.sms.service;

import com.sms.dto.StudentDto;
import com.sms.entity.Student;
import com.sms.utils.page.Pagination;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

public interface StudentService {

	int getTotalItemsCount(String searchKey);

	List<StudentDto> getStudentList(Pagination<Student> page, String searchKey);

	Student selectStudent(Student stu);

	int addStudent(Student stu) throws Exception;

	int updateStudent(Student stu);
	
	int deleteStudent(Student stu);
	
	int deleteStudent(List<String> ids);

	int importExcelInfo(InputStream in, MultipartFile file) throws Exception;

	int getTotalItemsCountByTid(String id, Integer baseCourseId);

	List<StudentDto> getStudentListByTid(Pagination<StudentDto> page, String id,
                                         Integer baseCourseId);

    String getAutoSid(Student student,String majorName, String collegeName);
}
