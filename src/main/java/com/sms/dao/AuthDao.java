package com.sms.dao;

import com.sms.entity.Auth;
import com.sms.utils.page.Pagination;

import java.util.List;

public interface AuthDao {
	
	List<Auth> selectUrl(String userType);

    int deleteByPrimaryKey(Integer id);

    int insert(Auth record);

    int insertSelective(Auth record);

    int updateByPrimaryKeySelective(Auth record);

    int updateByPrimaryKey(Auth record);

	int getTotalItemsCount(String searchKey);

	List<Auth> getAuthList(Pagination<Auth> page, String searchKey);
}