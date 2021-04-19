package com.sms.controller;

import com.alibaba.fastjson.JSON;
import com.sms.entity.Notice;
import com.sms.entity.Student;
import com.sms.entity.User;
import com.sms.service.NoticeService;
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
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value="notice")
public class NoticeController extends BaseController{
	
	private static final Integer AUTH_A = 3;
	private static final Integer AUTH_T = 2;
	private static final Integer AUTH_S = 1;
	
	
	@Autowired
	private NoticeService noticeService;
	
	@ResponseBody
	@RequestMapping(value="/list")
	public RepData<List<Notice>> getNoticeList(@RequestParam(defaultValue="0")int curr,
								 @RequestParam(defaultValue="20")int nums,
								 @RequestParam(defaultValue="")String searchKey,
								 HttpSession session) {
		
		Pagination<Notice> page = new Pagination<Notice>();
		page.setPageSize(nums);
		page.setPageNum(curr);
		
		List<Notice> list = new ArrayList<Notice>();
		
		User user = (User) session.getAttribute(USER);
		Integer auth = null;
		if (user.getUserType().equals(ADMIN)) {
			auth = AUTH_A;
		} else if (user.getUserType().equals(TEACHER)) {
			auth = AUTH_T;
		} else if (user.getUserType().equals(STUDENT)) {
			auth = AUTH_S;
		}
		page.setTotalItemsCount(noticeService.getTotalItemsCount(auth, searchKey));
		list = noticeService.getNoticeList(page, auth, searchKey);

		return success(list,page.getTotalItemsCount());
	}
	
	/**
	 * 查看公告
	 * @param nId
	 * @param mav
	 * @return
	 */
	@RequestMapping(value="/info")
	public ModelAndView showNoticeInfo(HttpSession session, Integer nId, ModelAndView mav) {
		User user = (User) session.getAttribute(USER);
		Integer auth = null;
		if (user.getUserType().equals(ADMIN)) {
			auth = AUTH_A;
		} else if (user.getUserType().equals(TEACHER)) {
			auth = AUTH_T;
		} else if (user.getUserType().equals(STUDENT)) {
			auth = AUTH_S;
		}
		Notice notice = noticeService.getNotice(nId);
		//无权限查看
		if (auth < notice.getAuth()) {
			return new ModelAndView("404");
		}
		mav = new ModelAndView("notice");
		mav.addObject("notice", notice);
		return mav;
	}
	@RequestMapping(value="/look")
	public ModelAndView showNotice(){
		return new ModelAndView("notice");
	}
	
	@RequestMapping(value="/addPage")
	public ModelAndView toAddPage() {
		return new ModelAndView("noticeAdd");
	}
	/**
	 * 增加，或者修改notice
	 * @param opType
	 * @param notice
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/add")
	public RepData<String> addNotice(@RequestParam(defaultValue="2")Integer opType, Notice notice) {
		int res = 0;
		if (opType == 0) {
			try {
				res = noticeService.addNotice(notice);
			} catch (Exception e) {
				return fail(ADD_ERROR);
			}
			if (res > 0)
				return success(RESULT_TRUE);
			return fail(ADD_ERROR);
		} else if (opType == 1) {
			res = noticeService.updateNotice(notice);
			if (res > 0)
				return success(RESULT_TRUE);
			return fail(UPDATE_ERROR);
		}
		return fail(ERROR);
	}
	
	
	@ResponseBody
	@RequestMapping(value="/delete")
	public RepData<String> deleteNotice(Notice notice) {
		if (noticeService.deleteNotice(notice) > 0) return success(RESULT_TRUE);
		return fail(DELETE_ERROR);
	}
	
	/**
	 * 批量删除
	 * @param nIds
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/deleteList")
	public RepData<String> deleteNoticeList(String nIds) {
		List<Integer> list = new ArrayList<Integer>();
		try {
			String[] ids = nIds.split(",");
			for (String id: ids) {
				list.add(Integer.parseInt(id));
			}
			if (noticeService.deleteNotice(list) > 0) {
				return success(RESULT_TRUE);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return fail(DELETE_ERROR);//
		}
		return fail(DELETE_ERROR);
	}
	
	
	@ResponseBody
	@RequestMapping(value="/uploadImg")
	public RepData<Map<String,String>> uploadImg(MultipartFile file, HttpServletRequest request) throws IOException{
        System.out.println("comming!");
        String path = request.getSession().getServletContext().getRealPath("/images");  
        System.out.println("path>>"+path);
        
        String fileName = file.getOriginalFilename();
        System.out.println("fileName>>"+fileName);
        fileName = fileName.substring(fileName.lastIndexOf("."), fileName.length());
        fileName = System.currentTimeMillis() + fileName;
        System.out.println("fileName>>"+fileName);
        File dir = new File(path, fileName);
        if(!dir.exists()){  
            dir.mkdirs();  
        }  
//      MultipartFile自带的解析方法  
        file.transferTo(dir);

        Map<String,String> map = new HashMap<>();
        map.put("src","/SMS/images/" + fileName);
		map.put("title",fileName);

		return success(map);
    }  
}
