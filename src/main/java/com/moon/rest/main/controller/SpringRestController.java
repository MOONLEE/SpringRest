package com.moon.rest.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SpringRestController {
	
	@RequestMapping(value="/")
	public String forwardIndexPage() {
		return "/index";
	}
}
