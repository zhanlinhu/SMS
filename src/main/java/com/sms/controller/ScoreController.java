package com.sms.controller;

import com.alibaba.fastjson.JSON;
import com.sms.dto.CourseDto;
import com.sms.dto.ScoreDto;
import com.sms.entity.Score;
import com.sms.entity.Student;
import com.sms.service.ScoreService;
import com.sms.utils.RepData;
import com.sms.utils.page.Pagination;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.beans.IntrospectionException;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

@Controller
@RequestMapping(value="/score")
public class ScoreController extends BaseController{
	@Autowired
	private ScoreService scoreService;
	
	@ResponseBody
	@RequestMapping(value="/list")
	public RepData<List<ScoreDto>> getScoreList(Integer curr, Integer nums, ScoreDto scoreDto) {
		System.out.println(scoreDto);
		Pagination<ScoreDto> page = new Pagination<ScoreDto>();
		page.setTotalItemsCount(scoreService.getTotalItemsCount(scoreDto));
		page.setPageSize(nums);
		page.setPageNum(curr);
		
		List<ScoreDto> list = scoreService.getScoreList(page, scoreDto);

		return success(list,page.getTotalItemsCount());
	}

	@ResponseBody
	@RequestMapping("/export")
	public void export(HttpServletRequest request, HttpServletResponse response, ScoreDto scoreDto)
			throws ClassNotFoundException, IntrospectionException,
			IllegalAccessException, ParseException, InvocationTargetException {
			
		response.reset(); // 清除buffer缓存
		// 设置文件名
		response.setHeader("Content-Disposition", "attachment;filename="
				+ System.currentTimeMillis() + ".xls");
		response.setContentType("application/vnd.ms-excel;charset=UTF-8");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		XSSFWorkbook workbook = null;
		// 导出Excel对象
		workbook = scoreService.exportExcelInfo(scoreDto);
		OutputStream output;
		try {
			output = response.getOutputStream();
			BufferedOutputStream bufferedOutPut = new BufferedOutputStream(output);
			bufferedOutPut.flush();
			workbook.write(bufferedOutPut);
			bufferedOutPut.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 学生查成绩列表
	 * @param curr
	 * @param nums
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/stuScore")
	public RepData<List<CourseDto>> getMyScoreList(@RequestParam(defaultValue="0")int curr,
												 @RequestParam(defaultValue="20")int nums,
												 HttpSession session, String result) {
		Student stu = (Student) session.getAttribute(USER);
		
		Pagination<CourseDto> page = new Pagination<CourseDto>();
		
		page.setTotalItemsCount(scoreService.getTotalItemsCount(stu.getId(), result));
		page.setPageSize(nums);
		page.setPageNum(curr);
		
		List<CourseDto> list = scoreService.getMyScoreList(page, stu.getId(), result);

		return success(list,page.getTotalItemsCount());
	}
	
	
	/**
	 * 学生选课
	 * @param session
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/choiceCourse")
	public RepData<String> choiceCourse(HttpSession session,
			@RequestParam(defaultValue="")Integer id) {
		if (id != null) {
			Student s = (Student) session.getAttribute(USER);
			Score score = new Score();
			score.setSId(s.getId());
			score.setBaseCourseId(id);
			int res = scoreService.choiceCourse(score);
			if (res > 0) return success(RESULT_TRUE);
			else return fail(RESULT_FALSE);
		}
		return fail(PARAMETER_ERROR);
	}
	
	
	/**
	 * 学生取消选课
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/delete")
	public RepData<String> deleteCourse(@RequestParam(defaultValue="")Integer id, HttpSession session) {
		Student stu = (Student) session.getAttribute(USER);
		Score s = new Score();
		s.setBaseCourseId(id);
		s.setSId(stu.getId());
		if (id != null) {
			int res = scoreService.deleteScore(s);
			if (res > 0) return success(RESULT_TRUE);
			else return fail("取消选课失败!");
		}
		return fail(PARAMETER_ERROR);
	}
	
	/**
	 * 评分
	 * @param score
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/update")
	public RepData<String> updateScore(Score score) {
		int res = scoreService.updateScore(score);
		if (res > 0) return success(RESULT_TRUE);
		else return fail(RESULT_FALSE);
	}
	
	@ResponseBody
	@RequestMapping(value="/updateList")
	public RepData<String> updateScoreList(String scoreListStr) {
		List<Score> scoreList = JSON.parseArray(scoreListStr, Score.class);
		System.out.println(scoreList);
		int res = scoreService.updateScore(scoreList);
		if (res > 0) return success(RESULT_TRUE);
		else return fail(RESULT_FALSE);
	}
}
