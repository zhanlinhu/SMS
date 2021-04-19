package com.sms.controller;

import com.sms.dto.CascaderDto;
import com.sms.dto.MajorDto;
import com.sms.entity.Major;
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
@RequestMapping(value="/major")
public class MajorController extends BaseController{
	
	@Autowired
	private MajorService majorService;
	
	@ResponseBody
	@RequestMapping(value="/list")
	public RepData<List<MajorDto>> getMajorList(@RequestParam(defaultValue="0")int curr, @RequestParam(defaultValue="10")int nums,
									 @RequestParam(defaultValue="")String searchKey) {
		Pagination<MajorDto> page = new Pagination<>();
		
		page.setTotalItemsCount(majorService.getTotalItemsCount(searchKey));
		page.setPageSize(nums);
		page.setPageNum(curr);
		List<MajorDto> list = majorService.getMajor(page, searchKey);

		return success(list,page.getTotalItemsCount());
	}

	@ResponseBody
	@RequestMapping(value="/getCount")
	public RepData<Integer> getCount(Integer majorNum,String collegeId) {

		int result = majorService.getCount(majorNum,collegeId);

		return success(result);
	}

	@ResponseBody
	@RequestMapping(value="/getCascader")
	public RepData<List<CascaderDto>> getCascader() {
		List<CascaderDto> list = majorService.getCascaderData();

		return success(list);
	}
	
	@ResponseBody
	@RequestMapping(value="/listForSelect")
	public RepData<List<Major>> getMajorListForSelect(@RequestParam(defaultValue="") String searchKey) {
		List<Major> list = majorService.getMajorForSelect(searchKey);
		return success(list,list.size());
	}
    
	@RequestMapping(value="/addPage")
	public ModelAndView toAddPage() {
		return new ModelAndView("/majorAdd");
	}
	
	/**
	 * 增加，或者修改Major
	 * @param major
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/add")
	public RepData<String> addMajor(Major major) {
		int res = 0;
		if (major.getId() == null) {
			try {
				res = majorService.addMajor(major);
				Map<String, Object> collegeAndMajorInfo = majorService.getCollegeAndMajorInfo();
				SIDUtil.initParam(collegeAndMajorInfo);
			} catch (Exception e) {
				return fail(OPRATION_ERROR);
			}
			if (res > 0)
				return success(RESULT_TRUE);
			return fail(OPRATION_ERROR);
		} else {
			res = majorService.updateMajor(major);
			if (res > 0) {
				return success(RESULT_TRUE);
			}
			return fail(OPRATION_ERROR);
		}
	}
	
	
	@ResponseBody
	@RequestMapping(value="/delete")
	public RepData<String> deleteMajor(Major t) {
		if (majorService.deleteMajor(t) > 0) return success(RESULT_TRUE);;
		return fail(OPRATION_ERROR);
	}

	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/deleteList")
	public RepData<String> deleteMajorList(String ids) {
		List<Integer> list = new ArrayList<Integer>();
		try {
			String[] arr = ids.split(",");
			for (String id: arr) {
				list.add(Integer.parseInt(id));
			}
			if (majorService.deleteMajor(list) > 0) {
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
			int res = majorService.importExcelInfo(in,file);
			if (res > 0) {
				Map<String, Object> collegeAndMajorInfo = majorService.getCollegeAndMajorInfo();
				SIDUtil.initParam(collegeAndMajorInfo);
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
