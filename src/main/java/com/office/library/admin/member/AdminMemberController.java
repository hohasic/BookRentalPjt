package com.office.library.admin.member;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/admin/member")
public class AdminMemberController {

	final private String CLASS_NAME = "[AdminMemberController] ";
	
	final private AdminMemberService adminMemberService;
	
	public AdminMemberController(AdminMemberService adminMemberService) {
		this.adminMemberService = adminMemberService;
		
	}
	
	/*
	 * 회원(관리자) 가입 양식
	 */
//	@RequestMapping(value = "/createAccountForm", method = RequestMethod.GET)
//	@RequestMapping(value = "/createAccountForm")
//	@RequestMapping("/createAccountForm")
	@GetMapping("/createAccountForm")
	public String createAccountForm() {
		System.out.println(CLASS_NAME.concat("createAccountForm()"));
		
		String nextPage = "admin/member/create_account_form";
		
		return nextPage;
		
	}
	
	/*
	 * 회원(관리자) 가입 확인
	 */
//	@RequestMapping(value = "/createAccountConfirm", method = RequestMethod.POST)
	@PostMapping("/createAccountConfirm")
	public String createAccountConfirm(AdminMemberDto adminMemberDto) {
		System.out.println(CLASS_NAME.concat("createAccountConfirm()"));
		
		String nextPage = "admin/member/create_account_ok";
		
		int result = adminMemberService.createAccountConfirm(adminMemberDto);
		if (result <= AdminMemberService.ADMIN_ACCOUNT_ALREADY_EXIST) 
			nextPage = "admin/member/create_account_ng";
		
		return nextPage;
		
	}
	
	
	
	
}
