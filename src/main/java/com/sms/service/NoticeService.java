package com.sms.service;

import com.sms.entity.Notice;
import com.sms.entity.Student;
import com.sms.utils.page.Pagination;

import java.util.List;

public interface NoticeService {

	int getTotalItemsCount(Integer authA, String searchKey);

	List<Notice> getNoticeList(Pagination<Notice> page, Integer auth,
			String searchKey);

	int deleteNotice(Notice notice);

	int deleteNotice(List<Integer> list);

	int addNotice(Notice notice);

	int updateNotice(Notice notice);

	Notice getNotice(Integer nId);
	
}
