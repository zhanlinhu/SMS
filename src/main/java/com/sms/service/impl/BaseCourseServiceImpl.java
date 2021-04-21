package com.sms.service.impl;

import com.sms.dao.BaseCourseDao;
import com.sms.dto.BaseCourseDto;
import com.sms.entity.BaseCourse;
import com.sms.service.BaseCourseService;
import com.sms.utils.ExcelUtil;
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
public class BaseCourseServiceImpl implements BaseCourseService {

	@Autowired
	BaseCourseDao baseCourseDao;
	
	@Override
	public int getTotalItemsCount(String searchKey) {
		return baseCourseDao.getTotalItemsCount(searchKey);
	}

	@Override
	public List<BaseCourseDto> getBaseCourse(Pagination<BaseCourseDto> page,
											 String searchKey) {
		return baseCourseDao.getBaseCourse(page, searchKey);
	}

	@Override
	public List<BaseCourse> getBaseCourseForSelect(String searchKey) {
		return baseCourseDao.getBaseCourseForSelect(searchKey);
	}

	@Override
	public int addBaseCourse(BaseCourse baseCourse) {
		return baseCourseDao.insertSelective(baseCourse);
	}

	@Override
	public int updateBaseCourse(BaseCourse baseCourse) {
		return baseCourseDao.updateByPrimaryKeySelective(baseCourse);
	}

	@Override
	public int deleteBaseCourse(BaseCourse t) {
		return baseCourseDao.deleteByPrimaryKey(t.getId());
	}

	@Override
	public int deleteBaseCourse(List<Integer> list) {
		return baseCourseDao.deleteInList(list);
	}

	@Override
	public int importExcelInfo(InputStream in, MultipartFile file) throws Exception {
		List<List<Object>> listObject = ExcelUtil.getBankListByExcel(in,
				file.getOriginalFilename());
		List<BaseCourse> list = new ArrayList<BaseCourse>();
		for (List<Object> Object: listObject) {
			BaseCourse course = new BaseCourse();
			course.setName(String.valueOf(Object.get(0)));
			course.setSynopsis(String.valueOf(Object.get(1)));
			course.setCollegeId(SIDUtil.collegeNum.get(Object.get(2)));
			list.add(course);
		}
		return baseCourseDao.insertBatch(list);
	}

}
