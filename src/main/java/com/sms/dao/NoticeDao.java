package com.sms.dao;

import com.sms.entity.Notice;
import com.sms.entity.Student;
import com.sms.utils.page.Pagination;

import java.util.List;

public interface NoticeDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Notice record);

    int insertSelective(Notice record);

    Notice selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Notice record);

    int updateByPrimaryKey(Notice record);

	int deleteBatch(List<Integer> list);

	List<Notice> getNotice(Pagination<Notice> page, Integer auth,
			String searchKey);

	int getTotalItemsCount(Integer authA, String searchKey);
}