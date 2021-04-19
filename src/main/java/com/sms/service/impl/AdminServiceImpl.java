package com.sms.service.impl;

import com.sms.dao.AdminDao;
import com.sms.entity.Admin;
import com.sms.entity.User;
import com.sms.service.AdminService;
import com.sms.service.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AdminServiceImpl implements AdminService, Login{

	@Autowired
	private AdminDao adminDao;

	@Override
	public Admin selectAdmin(Admin admin) {
		return adminDao.select(admin);
	}

	@Override
	public User loginValidate(String username, String password) {
		Admin admin = new Admin();
		admin.setUsername(username);
		admin.setPassword(password);
		admin = selectAdmin(admin);
		if(admin != null) admin.setUserType("admin");
		return admin;
	}

	@Override
	public int update(Admin admin) {
		return adminDao.updateByPrimaryKeySelective(admin);
	}

}
