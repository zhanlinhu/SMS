package com.sms.dao;

import com.sms.dto.BaseCourseDto;
import com.sms.entity.BaseCourse;
import com.sms.utils.page.Pagination;

import java.util.List;

public interface BaseCourseDao {
    int deleteByPrimaryKey(Integer id);

    int insert(BaseCourse record);

    int insertSelective(BaseCourse record);

    BaseCourse selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BaseCourse record);

    int updateByPrimaryKey(BaseCourse record);

	int getTotalItemsCount(String searchKey);

	List<BaseCourseDto> getBaseCourse(Pagination<BaseCourseDto> page, String searchKey);

	List<BaseCourse> getBaseCourseForSelect(String searchKey);

	int deleteInList(List<Integer> list);

	int insertBatch(List<BaseCourse> list);
}