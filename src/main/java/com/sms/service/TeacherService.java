package com.sms.service;

import com.sms.dto.TeacherDto;
import com.sms.entity.Teacher;
import com.sms.utils.page.Pagination;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

public interface TeacherService {

	Teacher selectTeacher(Teacher teacher);

	int getTotalItemsCount(String searchKey);

	int addTeacher(Teacher stu);

	int updateTeacher(Teacher stu);

	int deleteTeacher(Teacher t);

	int deleteTeacher(List<String> list);

	int importExcelInfo(InputStream in, MultipartFile file) throws Exception;

	List<TeacherDto> getTeacher(Pagination<Teacher> page, String searchKey);

	List<Teacher> getTeacherForSelect(String searchKey);

}
