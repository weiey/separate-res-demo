package com.example.demo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Calendar;
import java.util.Date;

@Controller
@RequestMapping("/demo")
public class DemoController extends BaseController {
	@RequestMapping(value="/",method=RequestMethod.GET)
	public String index(Model model) {
		return "index";
	}

	@RequestMapping(value="/wangEditor",method=RequestMethod.GET)
	public String wangEditor(Model model) {
		return "wangEditor";
	}

}