package com.sms.service;

import com.sms.entity.User;

public interface Login {

	/**
	 * 登录验证方法
	 * @return
	 */
	public User loginValidate(String username, String password);
}
