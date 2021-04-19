package com.sms.controller;

import com.sms.entity.Auth;
import com.sms.service.AuthService;
import com.sms.utils.RepData;
import com.sms.utils.page.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value="/auth")
public class AuthController extends BaseController{
	
	@Autowired
	AuthService authService;
	
	@ResponseBody
	@RequestMapping(value="/list")
	public RepData<List<Auth>> getAuthList(@RequestParam(defaultValue="0")int curr, @RequestParam(defaultValue="10")int nums,
							   @RequestParam(defaultValue="")String searchKey) {
		Pagination<Auth> page = new Pagination<Auth>();
		page.setTotalItemsCount(authService.getTotalItemsCount(searchKey));
		page.setPageSize(nums);
		page.setPageNum(curr);
		List<Auth> list = authService.getAuthList(page, searchKey);

		return success(list);
	}
	
	@ResponseBody
	@RequestMapping(value="/setting")
	public RepData<String> setting(Auth auth, String type, Integer val) {
		if ("teacherAuth".equals(type)) {
			auth.setTeacherAuth(val);
		} else {
			auth.setStudentAuth(val);
		}
		System.out.println(auth.toString());
		if (authService.update(auth) > 0)
			return success(RESULT_TRUE);
		return fail(OPRATION_ERROR);
	}
	
	
}
