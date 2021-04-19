package com.sms.dao;

import com.sms.dto.CascaderDto;
import com.sms.entity.College;
import com.sms.utils.page.Pagination;

import java.util.List;

public interface CollegeDao {
    int deleteByPrimaryKey(String id);

    int insert(College record);

    int insertSelective(College record);

    College selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(College record);

    int updateByPrimaryKey(College record);

    int getTotalItemsCount(String searchKey);

    List<College> getCollege(Pagination<College> page, String searchKey);

    List<College> getCollegeForSelect(String searchKey);

    int deleteInList(List<Integer> list);

    int insertBatch(List<College> list);

    List<CascaderDto> getCascaderData();
}
