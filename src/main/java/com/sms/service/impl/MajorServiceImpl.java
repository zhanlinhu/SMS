package com.sms.service.impl;

import com.sms.dao.CollegeDao;
import com.sms.dao.MajorDao;
import com.sms.dto.CascaderDto;
import com.sms.dto.MajorDto;
import com.sms.entity.College;
import com.sms.entity.Major;
import com.sms.service.MajorService;
import com.sms.utils.ExcelUtil;
import com.sms.utils.SIDUtil;
import com.sms.utils.page.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.*;

@Service
@Transactional
public class MajorServiceImpl implements MajorService {
    
    @Autowired
    MajorDao majorDao;

    @Autowired
    CollegeDao collegeDao;

    @Override
    public int getTotalItemsCount(String searchKey) {
        return majorDao.getTotalItemsCount(searchKey);
    }

    @Override
    public int getCount(Integer majorNum, String collegeId) {
        return majorDao.getCount(majorNum,collegeId);
    }

    @Override
    public List<MajorDto> getMajor(Pagination<MajorDto> page,
                                          String searchKey) {
        return majorDao.getMajor(page, searchKey);
    }

    @Override
    public List<Major> getMajorForSelect(String searchKey) {
        return majorDao.getMajorForSelect(searchKey);
    }

    @Override
    public int addMajor(Major baseCourse) {
        return majorDao.insertSelective(baseCourse);
    }

    @Override
    public int updateMajor(Major baseCourse) {
        return majorDao.updateByPrimaryKeySelective(baseCourse);
    }

    @Override
    public int deleteMajor(Major t) {
        return majorDao.deleteByPrimaryKey(t.getId());
    }

    @Override
    public int deleteMajor(List<Integer> list) {
        return majorDao.deleteInList(list);
    }

    @Override
    public int importExcelInfo(InputStream in, MultipartFile file) throws Exception {
        List<List<Object>> listObject = ExcelUtil.getBankListByExcel(in,
                file.getOriginalFilename());
        List<Major> list = new ArrayList<Major>();
        for (List<Object> object: listObject) {
            Major major = new Major();
            major.setName(String.valueOf(object.get(0)));
            major.setMajorNum(String.valueOf(object.get(1)));
            major.setSynopsis(String.valueOf(object.get(2)));
            major.setCollegeId(SIDUtil.collegeNum.get(object.get(3)));
            list.add(major);
        }
        System.out.println("list");
        return majorDao.insertBatch(list);
    }

    @Override
    public List<CascaderDto> getCascaderData() {
        CascaderDto cascaderDto = null;
        List<CascaderDto> cascaderList = new ArrayList<>();

        Set<Map.Entry<String, String>> college = SIDUtil.collegeNum.entrySet();
        Iterator<Map.Entry<String, String>> iterator = college.iterator();
        List<CascaderDto> majorList = majorDao.getCascaderData();
        while (iterator.hasNext()) {
            cascaderDto = new CascaderDto();
            Map.Entry<String,String> it = iterator.next();
            cascaderDto.setName(it.getKey());
            cascaderDto.setId(it.getValue());
            for (CascaderDto dto : majorList) {
                if (cascaderDto.getId().equals(dto.getCollegeId())) {
                    if (cascaderDto.getChildren() != null) {
                        cascaderDto.getChildren().add(dto);
                    } else {
                        //cascaderDto.setChildren(Arrays.asList(dto));
                        cascaderDto.setChildren(new ArrayList<CascaderDto>());
                        cascaderDto.getChildren().add(dto);
                    }
                }
            }
            cascaderList.add(cascaderDto);
        }
        
        return cascaderList;
    }

    @Override
    public Map<String, Object> getCollegeAndMajorInfo() {

        List<CascaderDto> majorInfo = null;
        List<CascaderDto> collegeInfo = null;
        Map<String,Object> map = null;
        majorInfo = majorDao.getCascaderData();
        collegeInfo = collegeDao.getCascaderData();
        map = new HashMap<>();
        map.put("major",majorInfo);
        map.put("college",collegeInfo);

        return map;
    }
}
