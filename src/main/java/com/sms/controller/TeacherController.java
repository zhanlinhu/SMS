package com.sms.controller;

import com.alibaba.fastjson.JSON;
import com.sms.dto.TeacherDto;
import com.sms.entity.Teacher;
import com.sms.service.TeacherService;
import com.sms.utils.MD5Util;
import com.sms.utils.RepData;
import com.sms.utils.page.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value="/teacher")
public class TeacherController extends BaseController{
	@Autowired
	private TeacherService teacherService;
	
	@ResponseBody
	@RequestMapping(value="/list")
	public RepData<List<TeacherDto>> getTeacherList(@RequestParam(defaultValue="0")int curr, @RequestParam(defaultValue="10")int nums,
													@RequestParam(defaultValue="")String searchKey) {
		Pagination<Teacher> page = new Pagination<Teacher>();
		
		page.setTotalItemsCount(teacherService.getTotalItemsCount(searchKey));
		page.setPageSize(nums);
		page.setPageNum(curr);
		List<TeacherDto> list = teacherService.getTeacher(page, searchKey);

		return success(list,page.getTotalItemsCount());
	}
	
	@ResponseBody
	@RequestMapping(value="/listForSelect")
	public RepData<List<Teacher>> getTeacherListForSelect(@RequestParam(defaultValue="") String searchKey) {
		List<Teacher> list = teacherService.getTeacherForSelect(searchKey);
		return success(list,list.size());
	}
    
	@RequestMapping(value="/addPage")
	public ModelAndView toAddPage() {
		return new ModelAndView("/teacherAdd");
	}
	
	/**
	 * 增加，或者修改teacher
	 * @param opType
	 * @param teacher
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/add")
	public RepData<String> addTeacher(@RequestParam(defaultValue="2") int opType, Teacher teacher) {
		int res = 0;
		if (opType == 0) {
			try {
				teacher.setPassword(teacher.getPassword().toUpperCase());
				res = teacherService.addTeacher(teacher);
			} catch (Exception e) {
				return fail(ADD_ERROR);
			}
			if (res > 0)
				return success(RESULT_TRUE);
			return fail(ADD_ERROR);
		} else if (opType == 1) {
			teacher.setPassword(null);
			res = teacherService.updateTeacher(teacher);
			if (res > 0) return success(RESULT_TRUE);
			return fail(UPDATE_ERROR);
		}
		return fail(ERROR);
	}
	
	/**
	 * 重置密码
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/resetPswd")
	public RepData<String> resetPasswrd(String id) {
		Teacher teacher = new Teacher();
		teacher.setId(id);
		teacher.setPassword(MD5Util.MD5("123456"));
		if (teacherService.updateTeacher(teacher) > 0) return success(RESULT_TRUE);
		return fail(UPDATE_ERROR);
	}
	
	@ResponseBody
	@RequestMapping(value="/delete")
	public RepData<String> deleteStudnet(Teacher t) {
		if (teacherService.deleteTeacher(t) > 0) return success(RESULT_TRUE);
		return fail(DELETE_ERROR);
	}
	
	/**
	 * 批量删除
	 * @param tIds
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/deleteList")
	public RepData<String> deleteStudnetList(String tIds) {
		List<String> list = new ArrayList<String>();
		try {
			String[] ids = tIds.split(",");
			for (String id: ids) {
				list.add(id);
			}
			if (teacherService.deleteTeacher(list) > 0) {
				return success(RESULT_TRUE);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return fail(DELETE_ERROR);
		}
		return fail(DELETE_ERROR);
	}
	
	@ResponseBody
	@RequestMapping("/import")  
	public RepData<String> impotr(HttpServletRequest request, MultipartFile file) {
	     //获取上传的文件  
	     InputStream in = null;
		try {
			in = file.getInputStream();
			//数据导入  
			int res = teacherService.importExcelInfo(in,file);
			if (res > 0) {
				return success(RESULT_TRUE,0);
			} else {
				return fail(RESULT_FALSE,0);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return fail(ERROR);
		} finally {
			if (in != null)
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}
}
