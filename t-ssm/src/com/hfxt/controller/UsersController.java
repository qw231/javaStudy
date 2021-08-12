package com.hfxt.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.hfxt.common.RetCode;
import com.hfxt.entity.Users;

@Controller
@RequestMapping("/user")
public class UsersController extends BaseController {
	
	@RequestMapping("/login")
	public String login(Users user,HttpSession session){
		Users loginuser = usersService.getUsesByUsername(user.getUsername());
		if(loginuser==null){
			throw new RuntimeException("用户名不存在");
		}else if(!loginuser.getPassword().equals(user.getPassword())){
			throw new RuntimeException("密码不正确");
		}else{
			session.setAttribute("loginuser", loginuser);
			return "index";
			//return "redirect:/sale/index";
		}
	}
	
	@RequestMapping("/ajaxlogin")
	@ResponseBody
	public String ajaxlogin(Users user,HttpSession session){
		Users loginuser = usersService.getUsesByUsername(user.getUsername());
		Map<String, Object> maps = new HashMap<String, Object>();
		if(loginuser==null){
			maps.put("code",0);
			maps.put("msg", "用户名不存在");
		}else if(!loginuser.getPassword().equals(user.getPassword())){
			maps.put("code",0);
			maps.put("msg", "密码不正确");
		}else{
			session.setAttribute("loginuser", loginuser);
			maps.put("code",1);
			maps.put("msg", "登录成功");
		}
		return JSONArray.toJSONString(maps);
	}

}
