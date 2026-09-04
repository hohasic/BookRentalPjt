package com.office.library.user.member;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user/member")
public class UserMemberController {

	final private String CLASS_NAME = "[UserMemberController] ";
	
	/*
	 * 사용자 회원 가입 양식
	 * /user/member/createAccountForm
	 */
	@GetMapping("/createAccountForm")
	public String createAccountForm() {
		System.out.println(CLASS_NAME.concat("createAccountForm()"));
		
		String nextPage = "user/member/create_account_form";
		
		return nextPage;
		
	}
	
}
