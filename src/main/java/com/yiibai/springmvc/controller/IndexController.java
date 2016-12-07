package com.yiibai.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Administrator 
 * 主页一个简单的控制器
 */
@Controller
@RequestMapping("/index")
public class IndexController {
	@RequestMapping(method = RequestMethod.GET)
	public String getIndexPage() {
		return "UserManagement";
	}
}
