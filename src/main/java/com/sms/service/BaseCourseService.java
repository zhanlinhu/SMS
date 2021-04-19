package com.sms.service;

import com.sms.dto.BaseCourseDto;
import com.sms.entity.BaseCourse;
import com.sms.utils.page.Pagination;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

public interface BaseCourseService {

	int getTotalItemsCount(String searchKey);

	List<BaseCourseDto> getBaseCourse(Pagination<BaseCourseDto> page, String searchKey);

	List<BaseCourse> getBaseCourseForSelect(String searchKey);

	int addBaseCourse(BaseCourse baseCourse);

	int updateBaseCourse(BaseCourse baseCourse);

	int deleteBaseCourse(List<Integer> list);

	int importExcelInfo(InputStream in, MultipartFile file) throws Exception;

	int deleteBaseCourse(BaseCourse t);

}
