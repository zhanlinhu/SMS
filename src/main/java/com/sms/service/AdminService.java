package com.sms.service;

import com.sms.entity.Admin;

public interface AdminService {
	public Admin selectAdmin(Admin admin);

	public int update(Admin admin);
}
