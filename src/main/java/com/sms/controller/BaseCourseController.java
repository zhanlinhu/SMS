package com.sms.controller;

import com.sms.dto.BaseCourseDto;
import com.sms.entity.BaseCourse;
import com.sms.service.BaseCourseService;
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
@RequestMapping(value="/basecourse")
public class BaseCourseController extends BaseController{
	
	@Autowired
	private BaseCourseService baseCourseService;
	
	@ResponseBody
	@RequestMapping(value="/list")
	public RepData<List<BaseCourseDto>> getBaseCourseList(@RequestParam(defaultValue="0")int curr, @RequestParam(defaultValue="10")int nums,
														  @RequestParam(defaultValue="")String searchKey) {
		Pagination<BaseCourseDto> page = new Pagination<>();
		
		page.setTotalItemsCount(baseCourseService.getTotalItemsCount(searchKey));
		page.setPageSize(nums);
		page.setPageNum(curr);
		List<BaseCourseDto> list = baseCourseService.getBaseCourse(page, searchKey);

		return success(list,page.getTotalItemsCount());
	}
	
	@ResponseBody
	@RequestMapping(value="/listForSelect")
	public RepData<List<BaseCourse>> getBaseCourseListForSelect(@RequestParam(defaultValue="") String searchKey) {
		List<BaseCourse> list = baseCourseService.getBaseCourseForSelect(searchKey);
		return success(list,list.size());
	}
    
	@RequestMapping(value="/addPage")
	public ModelAndView toAddPage() {
		return new ModelAndView("/baseCourseAdd");
	}
	
	/**
	 * 增加，或者修改BaseCourse
	 * @param baseCourse
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/add")
	public RepData<String> addBaseCourse(BaseCourse baseCourse) {
		int res = 0;
		if (baseCourse.getId() == null || baseCourse.getId().equals("")) {
			try {
				res = baseCourseService.addBaseCourse(baseCourse);
			} catch (Exception e) {
				return fail(OPRATION_ERROR);
			}
			if (res > 0)
				return success(RESULT_TRUE);
			return fail(OPRATION_ERROR);
		} else {
			res = baseCourseService.updateBaseCourse(baseCourse);
			if (res > 0) {
				return success(RESULT_TRUE);
			}
			return fail(OPRATION_ERROR);
		}
	}
	
	
	@ResponseBody
	@RequestMapping(value="/delete")
	public RepData<String> deleteStudnet(BaseCourse t) {
		if (baseCourseService.deleteBaseCourse(t) > 0) return success(RESULT_TRUE);;
		return fail(OPRATION_ERROR);
	}

	/**
	 * 批量删除
	 * @param baseCourseIds
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/deleteList")
	public RepData<String> deleteStudnetList(String baseCourseIds) {
		List<Integer> list = new ArrayList<Integer>();
		try {
			String[] ids = baseCourseIds.split(",");
			for (String id: ids) {
				list.add(Integer.parseInt(id));
			}
			if (baseCourseService.deleteBaseCourse(list) > 0) {
				return success(RESULT_TRUE);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return fail("删除失败！参数出错！");
		}
		return fail("删除失败！");
	}
	
	@ResponseBody
	@RequestMapping("/import")  
	public RepData<String> impotr(HttpServletRequest request, MultipartFile file) {
	     //获取上传的文件  
	     InputStream in = null;
		try {
			in = file.getInputStream();
			//数据导入  
			int res = baseCourseService.importExcelInfo(in,file);
			if (res > 0) {
				return success(RESULT_TRUE,0);
			} else {
				return fail(RESULT_FALSE,0);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return fail("error",0);
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
