package com.sms.service;

import com.sms.dto.CascaderDto;
import com.sms.dto.MajorDto;
import com.sms.entity.College;
import com.sms.entity.Major;
import com.sms.utils.page.Pagination;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

public interface MajorService {

    int getTotalItemsCount(String searchKey);

    int getCount(Integer majorNum, String collegeId);

    List<MajorDto> getMajor(Pagination<MajorDto> page, String searchKey);

    List<Major> getMajorForSelect(String searchKey);

    int addMajor(Major Major);

    int updateMajor(Major Major);

    int deleteMajor(List<Integer> list);

    int importExcelInfo(InputStream in, MultipartFile file) throws Exception;

    int deleteMajor(Major t);

    List<CascaderDto> getCascaderData();

    Map<String,Object> getCollegeAndMajorInfo();
}
