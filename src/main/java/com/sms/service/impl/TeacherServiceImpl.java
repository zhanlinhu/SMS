package com.sms.service.impl;

import com.sms.dao.TeacherDao;
import com.sms.dto.TeacherDto;
import com.sms.entity.Teacher;
import com.sms.entity.User;
import com.sms.service.Login;
import com.sms.service.TeacherService;
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
public class TeacherServiceImpl implements TeacherService, Login{

	@Autowired
	private TeacherDao teacherDao;

	@Override
	public Teacher selectTeacher(Teacher teacher) {
		return teacherDao.selectTeacher(teacher);
	}

	@Override
	public int getTotalItemsCount(String searchKey) {
		return teacherDao.getTotalItemsCount(searchKey);
	}

	@Override
	public int addTeacher(Teacher t) {
		return teacherDao.insert(t);
	}

	@Override
	public int updateTeacher(Teacher t) {
		return teacherDao.updateByPrimaryKeySelective(t);
	}

	@Override
	public int deleteTeacher(Teacher t) {
		return teacherDao.deleteByPrimaryKey(t.getId());
	}

	@Override
	public int deleteTeacher(List<String> list) {
		return teacherDao.deleteInList(list);
	}

	/**
	 * 导入教师excel信息
	 * @param in
	 * @param file
	 * @return
	 * @throws Exception
	 */
	@Override
	public int importExcelInfo(InputStream in, MultipartFile file) throws Exception {
		List<List<Object>> listObject = ExcelUtil.getBankListByExcel(in,
				file.getOriginalFilename());
		List<Teacher> list = new ArrayList<Teacher>();
		//把数据放到List中
		for (List<Object> Objectject: listObject) {
			Teacher t = new Teacher();
			// 通过遍历实现把每一列封装成一个model中
			t.setId(String.valueOf(Objectject.get(0)));
			t.setPassword(MD5Util.MD5((String)Objectject.get(1)));
			t.setName(String.valueOf(Objectject.get(2)));
			t.setSex(String.valueOf(Objectject.get(3)));
			t.setSynopsis(String.valueOf(Objectject.get(4)));
			t.setMajorId(SIDUtil.majorList.get(Objectject.get(5)));
			list.add(t);
		}
		return teacherDao.insertBatch(list);
	}

	@Override
	public List<TeacherDto> getTeacher(Pagination<Teacher> page, String searchKey) {
		return teacherDao.getTeacher(page, searchKey);
	}

	@Override
	public List<Teacher> getTeacherForSelect(String searchKey) {
		return teacherDao.getTeacherForSelect(searchKey);
	}
	
	
	@Override
	public User loginValidate(String username, String password) {
		Teacher teacher = new Teacher();
		teacher.setId(username);
		teacher.setPassword(password);
		teacher = selectTeacher(teacher);
		if(teacher != null) teacher.setUserType("teacher");
		return teacher;
	}

}
