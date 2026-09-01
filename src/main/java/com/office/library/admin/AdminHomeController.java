package com.office.library.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminHomeController {

	@RequestMapping("/admin")
	public String home() {
		System.out.println("[AdminHomeController] home()");
		
		String nextPage = "admin/home";
		
		return nextPage;
		
	}
	
}
