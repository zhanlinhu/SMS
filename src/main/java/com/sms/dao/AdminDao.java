package com.sms.dao;

import com.sms.entity.Admin;

public interface AdminDao {
	public Admin select(Admin admin);

	public int updateByPrimaryKeySelective(Admin admin);
}