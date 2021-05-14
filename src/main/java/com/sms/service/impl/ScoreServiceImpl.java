package com.sms.service.impl;

import com.sms.dao.CourseDao;
import com.sms.dao.ScoreDao;
import com.sms.dto.CourseDto;
import com.sms.dto.ExcelBean;
import com.sms.dto.ScoreDto;
import com.sms.entity.Course;
import com.sms.entity.Score;
import com.sms.service.ScoreService;
import com.sms.utils.ExcelUtil;
import com.sms.utils.page.Pagination;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ScoreServiceImpl implements ScoreService {

	@Autowired
	ScoreDao scoreDao;

	@Autowired
	CourseDao courseDao;

	@Override
	public int choiceCourse(Score score) {
		// 选课过程为一个事务
		Course c = courseDao.selectByPrimaryKey(score.getBaseCourseId());
		if (c.getStudentNum() == c.getChoiceNum()) {
			// 此课程学生人数已满，选课失败
			return 0;
		}
		// 对所选课程中，choiceNum（已选人数+1）
		c.setChoiceNum(c.getChoiceNum() + 1);
		courseDao.updateByPrimaryKeySelective(c);
		return scoreDao.insertSelective(score);
	}

	@Override
	public int deleteScore(Score score) {
		Course c = courseDao.selectByPrimaryKey(score.getBaseCourseId());
		c.setChoiceNum(c.getChoiceNum() - 1);
		courseDao.updateByPrimaryKeySelective(c);
		return scoreDao.delete(score);
	}

	@Override
	public int updateScore(Score score) {
		return scoreDao.updateByPrimaryKeySelective(score);
	}

	@Override
	public int updateScore(List<Score> scoreList) {
		return scoreDao.update(scoreList);
	}

	@Override
	public int getTotalItemsCount(String id, String result) {
		return scoreDao.getTotalItemsCount(id, result);
	}

	@Override
	public List<CourseDto> getMyScoreList(Pagination<CourseDto> page, String id,
										 String result) {
		return scoreDao.getMyScoreList(page, id, result);
	}

	@Override
	public int getTotalItemsCount(ScoreDto scoreDto) {
		return scoreDao.getTotalItemsCountForExport(scoreDto);
	}

	@Override
	public List<ScoreDto> getScoreList(Pagination<ScoreDto> page, ScoreDto scoreDto) {
		return scoreDao.getScoreListForExport(page, scoreDto);
	}

	@Override
	public XSSFWorkbook exportExcelInfo(ScoreDto scoreDto) throws InvocationTargetException, ClassNotFoundException, IntrospectionException, IllegalAccessException {
	    //根据条件查询数据，把数据装载到一个list中  
	    List<ScoreDto> list = scoreDao.getScoreListForExport(null, scoreDto);
	    List<ExcelBean> excel=new ArrayList<ExcelBean>();
	    Map<Integer,List<ExcelBean>> map=new LinkedHashMap<Integer,List<ExcelBean>>();
	    XSSFWorkbook xssfWorkbook=null;
	    //设置标题栏  
	    excel.add(new ExcelBean("时间","startDate",0));  
	    excel.add(new ExcelBean("学号","stuId",0));  
	    excel.add(new ExcelBean("姓名","stuName",0));  
	    excel.add(new ExcelBean("专业","MajorName",0));
	    excel.add(new ExcelBean("班级","grade",0));  
	    excel.add(new ExcelBean("课程名","courseName",0));  
	    excel.add(new ExcelBean("任课教师","teacherName",0));  
	    excel.add(new ExcelBean("考核方式","testMode",0));  
	    excel.add(new ExcelBean("成绩","score",0));  
	    excel.add(new ExcelBean("结果","result",0));  
	    map.put(0, excel);
	    //调用ExcelUtil的方法  
	    xssfWorkbook = ExcelUtil.createExcelFile(ScoreDto.class, list, map, "sheet1");
	    return xssfWorkbook;  
	}
}
