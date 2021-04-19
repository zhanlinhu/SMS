package com.sms.dao;

import com.sms.dto.CascaderDto;
import com.sms.dto.MajorDto;
import com.sms.entity.Major;
import com.sms.utils.page.Pagination;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MajorDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Major record);

    int insertSelective(Major record);

    Major selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Major record);

    int updateByPrimaryKey(Major record);

    int getTotalItemsCount(String searchKey);

    int getCount(@Param("majorNum") Integer majorNum,@Param("collegeId") String collegeId);

    List<MajorDto> getMajor(Pagination<MajorDto> page, String searchKey);

    List<Major> getMajorForSelect(String searchKey);

    int deleteInList(List<Integer> list);

    int insertBatch(List<Major> list);

    List<CascaderDto> getCascaderData();
}
