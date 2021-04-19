package com.sms.service;

import com.sms.entity.Auth;
import com.sms.utils.page.Pagination;

import java.util.List;


public interface AuthService {

	List<Auth> getMenuList(String userType);

	List<Auth> getUrlList(String userType);

	int getTotalItemsCount(String searchKey);

	List<Auth> getAuthList(Pagination<Auth> page, String searchKey);

	int update(Auth auth);

}
