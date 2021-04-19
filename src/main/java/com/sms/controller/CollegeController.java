package com.sms.controller;

import com.sms.entity.College;
import com.sms.service.CollegeService;
import com.sms.service.MajorService;
import com.sms.utils.RepData;
import com.sms.utils.SIDUtil;
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
import java.util.Map;

@Controller
@RequestMapping(value="/college")
public class CollegeController extends BaseController{
	
	@Autowired
	private CollegeService collegeService;

	@Autowired
	private MajorService majorService;
	
	@ResponseBody
	@RequestMapping(value="/list")
	public RepData<List<College>> getCollegeList(@RequestParam(defaultValue="0")int curr, @RequestParam(defaultValue="10")int nums,
									 @RequestParam(defaultValue="")String searchKey) {
		Pagination<College> page = new Pagination<>();
		
		page.setTotalItemsCount(collegeService.getTotalItemsCount(searchKey));
		page.setPageSize(nums);
		page.setPageNum(curr);
		List<College> list = collegeService.getCollege(page, searchKey);

		return success(list,page.getTotalItemsCount());
	}
	
	@ResponseBody
	@RequestMapping(value="/listForSelect")
	public RepData<List<College>> getCollegeListForSelect(@RequestParam(defaultValue="") String searchKey) {
		List<College> list = collegeService.getCollegeForSelect(searchKey);
		return success(list,list.size());
	}
    
	@RequestMapping(value="/addPage")
	public ModelAndView toAddPage() {
		return new ModelAndView("/collegeAdd");
	}
	
	/**
	 * 增加，或者修改College
	 * @param college
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/add")
	public RepData<String> addCollege(@RequestParam(defaultValue="2") int opType,College college) {
		int res = 0;
		if (opType == 0) {
			try {
				res = collegeService.addCollege(college);
				Map<String, Object> collegeAndMajorInfo = majorService.getCollegeAndMajorInfo();
				SIDUtil.initParam(collegeAndMajorInfo);
			} catch (Exception e) {
				return fail("编号重复！");
			}
			if (res > 0)
				return success(RESULT_TRUE);
			return fail(OPRATION_ERROR);
		} else {
			res = collegeService.updateCollege(college);
			if (res > 0) {
				return success(RESULT_TRUE);
			}
			return fail(OPRATION_ERROR);
		}
	}
	
	
	@ResponseBody
	@RequestMapping(value="/delete")
	public RepData<String> deleteStudnet(College t) {
		if (collegeService.deleteCollege(t) > 0) return success(RESULT_TRUE);;
		return fail(OPRATION_ERROR);
	}

	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/deleteList")
	public RepData<String> deleteStudnetList(String ids) {
		List<Integer> list = new ArrayList<Integer>();
		try {
			String[] arr = ids.split(",");
			for (String id: arr) {
				list.add(Integer.parseInt(id));
			}
			if (collegeService.deleteCollege(list) > 0) {
				return success(RESULT_TRUE);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return fail("删除失败！参数出错！");
		}
		return fail("删除失败！");
	}
	
}
