package com.sms.service;

import com.sms.dto.CourseDto;
import com.sms.dto.ScoreDto;
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

	List<CourseDto> getMyScoreList(Pagination<CourseDto> page, String string, String result);

	int getTotalItemsCount(ScoreDto scoreDto);

	List<ScoreDto> getScoreList(Pagination<ScoreDto> page, ScoreDto scoreDto);

	XSSFWorkbook exportExcelInfo(ScoreDto scoreDto) throws InvocationTargetException, ClassNotFoundException, IntrospectionException, IllegalAccessException;

}
