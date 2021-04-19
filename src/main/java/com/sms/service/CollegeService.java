package com.sms.service;

import com.sms.entity.College;
import com.sms.utils.page.Pagination;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

public interface CollegeService {

    int getTotalItemsCount(String searchKey);

    List<College> getCollege(Pagination<College> page, String searchKey);

    List<College> getCollegeForSelect(String searchKey);

    int addCollege(College College);

    int updateCollege(College College);

    int deleteCollege(List<Integer> list);

    int deleteCollege(College t);
}
