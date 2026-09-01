package com.office.library.admin.member;

import javax.servlet.http.HttpSession;

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
	
	/*
	 * 회원(관리자) 로그인 양식  (/admin/member/loginForm)
	 */
	@GetMapping("/loginForm")
	public String loginForm() {
		System.out.println(CLASS_NAME.concat("loginForm()"));
		
		String nextPage = "admin/member/login_form";
		
		return nextPage;
		
	}
	
	
	/*
	 * 회원(관리자) 로그인 확인  (/admin/member/loginConfirm)
	 */
	@PostMapping("/loginConfirm")
	public String loginConfirm(AdminMemberDto adminMemberDto, HttpSession session) {
		System.out.println(CLASS_NAME.concat("loginConfirm()"));
		
		String nextPage = "admin/member/login_ok";
		
		String loginedAdminMemberId = adminMemberService.loginConfirm(adminMemberDto);
		if (loginedAdminMemberId == null) {
			nextPage = "admin/member/login_ng";
			
		} else {
			session.setAttribute("loginedAdminMemberId", loginedAdminMemberId);
			session.setMaxInactiveInterval(60 * 30);
			
		}
		
		return nextPage;
		
	}
	
	/*
	 * 관리자 로그 아웃 화인
	 */
	@GetMapping("/logoutConfirm")
	public String logoutConfirm(HttpSession session) {
		System.out.println(CLASS_NAME.concat("logoutConfirm()"));
		
		String nextPage = "redirect:/admin";
		
		session.removeAttribute("loginedAdminMemberId");
		
		return nextPage;
		
	}
	
}
