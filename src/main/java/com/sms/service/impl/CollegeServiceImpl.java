package com.sms.service.impl;

import com.sms.dao.CollegeDao;
import com.sms.entity.College;
import com.sms.service.CollegeService;
import com.sms.utils.ExcelUtil;
import com.sms.utils.page.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CollegeServiceImpl implements CollegeService {
    
    @Autowired
    CollegeDao collegeDao;



    @Override
    public int getTotalItemsCount(String searchKey) {
        return collegeDao.getTotalItemsCount(searchKey);
    }

    @Override
    public List<College> getCollege(Pagination<College> page,
                                          String searchKey) {
        return collegeDao.getCollege(page, searchKey);
    }

    @Override
    public List<College> getCollegeForSelect(String searchKey) {
        return collegeDao.getCollegeForSelect(searchKey);
    }

    @Override
    public int addCollege(College College) {

        int result = 0;
        result = collegeDao.insertSelective(College);
        return result;
    }

    @Override
    public int updateCollege(College College) {
        return collegeDao.updateByPrimaryKeySelective(College);
    }

    @Override
    public int deleteCollege(College t) {
        return collegeDao.deleteByPrimaryKey(t.getId());
    }

    @Override
    public int deleteCollege(List<Integer> list) {
        return collegeDao.deleteInList(list);
    }

}
