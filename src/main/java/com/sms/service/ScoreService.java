package com.sms.service;

import com.sms.dto.ScoreVo;
import com.sms.entity.Course;
import com.sms.entity.Score;
import com.sms.utils.page.Pagination;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface ScoreService {

	int choiceCourse(Score score);

	int updateScore(Score score);

	int deleteScore(Score s);

	int updateScore(List<Score> scoreList);

	int getTotalItemsCount(String string, String result);

	List<Course> getCourseList(Pagination<Course> page, String string, String result);

	int getTotalItemsCount(ScoreVo scoreVo);

	List<ScoreVo> getScoreList(Pagination<ScoreVo> page, ScoreVo scoreVo);

	XSSFWorkbook exportExcelInfo(ScoreVo scoreVo) throws InvocationTargetException, ClassNotFoundException, IntrospectionException, IllegalAccessException;

}
