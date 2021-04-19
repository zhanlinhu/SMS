package com.sms.quartz;

import com.sms.dao.CollegeDao;
import com.sms.service.CollegeService;
import com.sms.service.MajorService;
import com.sms.utils.SIDUtil;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

public class LoadDataJob {
    @Autowired
    MajorService majorService;

    @Autowired
    CollegeService collegeService;

    public void load(){
        SIDUtil.initParam(majorService.getCollegeAndMajorInfo());
    }


}
