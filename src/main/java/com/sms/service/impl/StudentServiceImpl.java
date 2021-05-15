package com.sms.service.impl;

import com.sms.dao.StudentDao;
import com.sms.dto.StudentDto;
import com.sms.entity.Student;
import com.sms.entity.User;
import com.sms.service.Login;
import com.sms.service.StudentService;
import com.sms.utils.ExcelUtil;
import com.sms.utils.MD5Util;
import com.sms.utils.SIDUtil;
import com.sms.utils.page.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class StudentServiceImpl implements StudentService, Login {

	@Autowired
	private StudentDao studentDao;



	@Override
	public int getTotalItemsCount(String searchKey) {
		return studentDao.getTotalItemsCount(searchKey);
	}

	@Override
	public List<StudentDto> getStudentList(Pagination<Student> page,
                                           String searchKey) {
		return studentDao.selectBySearchKey(page, searchKey);
	}

	@Override
	public Student selectStudent(Student stu) {

		return studentDao.getStudent(stu);
	}

	@Override
	public int addStudent(Student stu) throws Exception {
		return studentDao.insert(stu);
	}

	@Override
	public int updateStudent(Student stu) {
		return studentDao.updateByPrimaryKeySelective(stu);
	}

	@Override
	public int deleteStudent(Student stu) {
		return studentDao.deleteByPrimaryKey(stu.getId());
	}

	@Override
	public int deleteStudent(List<String> list) {
		return studentDao.deleteInList(list);
	}

	@Override
	public int importExcelInfo(InputStream in, MultipartFile file)
			throws Exception {
		List<List<Object>> listObject = ExcelUtil.getBankListByExcel(in,
				file.getOriginalFilename());
		List<Student> listTemp = new ArrayList<Student>();
		List<Student> list = new ArrayList<Student>();
		// 遍历listObject数据，把数据放到List中
		for (List<Object> object: listObject) {
			Student student = new Student();
			// 通过遍历实现把每一列封装成一个model中
			student.setPassword(MD5Util.MD5((String)object.get(0)));
			student.setName(String.valueOf(object.get(1)));
			student.setSex(String.valueOf(object.get(2)));
			student.setGrade(String.valueOf(object.get(3)));
			student.setAdmissionDate(String.valueOf(object.get(4)));
			student.setGraduationDate(String.valueOf(object.get(5)));
			System.out.println(object.get(6));
			student.setAcademicStatus((Integer.valueOf((String)object.get(6))));
			System.out.println(object.get(6));
			student.setMajorId(Integer.valueOf(SIDUtil.majorList.get(object.get(7))));
			student.setId(getAutoSid(student, (String) object.get(7),(String) object.get(8)));
			listTemp.add(student);
		}
		int count = studentDao.getTotalItemsCount(listTemp.get(1).getId());
		for (Student student:listTemp){
			if(count == 0){
				count = 1;
			}else {
				count++;
			}
			if(count < 10){
				 student.setId(student.getId()+ "0" + count);
			}else {
				student.setId(student.getId()+ count);
			}
			list.add(student);
		}
		return studentDao.insertBatch(list);
	}
	
	@Override
	public User loginValidate(String username, String password) {
		Student student = new Student();
		student.setId(username);
		student.setPassword(password);
		student = selectStudent(student);
		if(student != null) student.setUserType("student");
		return student;
	}

	@Override
	public int getTotalItemsCountByTid(String id, Integer baseCourseId) {
		return studentDao.getTotalItemsCountByTid(id, baseCourseId);
	}

	@Override
	public List<StudentDto> getStudentListByTid(Pagination<StudentDto> page,
                                                String id, Integer baseCourseId) {
		return studentDao.getStudentListByTid(page, id, baseCourseId);
	}

	@Override
	public String getAutoSid(Student student,String majorName,String collegeName) {
		String majorNum = null;
		String sid = "";
		int count = 0;
		//后端自动生成sid
		if(majorName != null){
			majorNum = SIDUtil.majorNum.get(majorName);
			String collegeId = SIDUtil.collegeNum.get(collegeName);
			sid = student.getAdmissionDate().split("-")[0] + "" + collegeId + majorNum + student.getGrade();
		}else{
			//前端调用生成sid
			majorNum = SIDUtil.majorNum.get(student.getName());
			sid = student.getAdmissionDate().split("-")[0] + "" + student.getGraduationDate() + majorNum + student.getGrade();
			count = studentDao.getTotalItemsCount(sid);
			if(count == 0){
				count = 1;
			}else {
				count++;
			}
			if(count < 10){
				sid = sid + "0" + count;
			}else {
				sid+=count;
			}
		}
		return sid;
	}
}
