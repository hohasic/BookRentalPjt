package com.office.library.admin.member;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminMemberService {

	final private String CLASS_NAME = "[AdminMemberService] ";
	
	final static public int ADMIN_ACCOUNT_ALREADY_EXIST 	= 0;
	final static public int ADMIN_ACCOUNT_CREATE_SUCCESS 	= 1;
	final static public int ADMIN_ACCOUNT_CREATE_FAIL 		= -1;
	
	final private AdminMemberDao adminMemberDao;
	final private PasswordEncoder passwordEncoder;
	
	public AdminMemberService(
			AdminMemberDao adminMemberDao, 
			PasswordEncoder passwordEncoder) {
		this.adminMemberDao = adminMemberDao;
		this.passwordEncoder = passwordEncoder;
		
	}

	public int createAccountConfirm(AdminMemberDto adminMemberDto) {
		System.out.println(CLASS_NAME.concat("createAccountConfirm()"));
		
		// 1. 아이디 중복 체크
		boolean isMember = adminMemberDao.isAdminMember(adminMemberDto.getA_m_id());
		System.out.println(CLASS_NAME.concat("isMember: " + isMember));
		
		// 2. 관리자 회원 가입
		if (!isMember) {
			
			// 비밀번호를 암호화 처리
			String encodedPassword = passwordEncoder.encode(adminMemberDto.getA_m_pw());
			adminMemberDto.setA_m_pw(encodedPassword);
			
			int result = adminMemberDao.insertAdminAccount(adminMemberDto);
			
			if (result > 0) {
				System.out.println(CLASS_NAME.concat("ADMIN NEW MEMBER CREATE ACCOUNT SUCCESS!!"));
				return ADMIN_ACCOUNT_CREATE_SUCCESS;
				
			} else {
				System.out.println(CLASS_NAME.concat("ADMIN NEW MEMBER CREATE ACCOUNT FAIL!!"));
				return ADMIN_ACCOUNT_CREATE_FAIL;
				
			}
			
		} else {
			System.out.println(CLASS_NAME.concat("ADMIN NEW MEMBER CREATE ACCOUNT FAIL!!"));
			return ADMIN_ACCOUNT_ALREADY_EXIST;
			
		}
		
	}

	public String loginConfirm(AdminMemberDto adminMemberDto) {			// 1234  == 1234
		System.out.println(CLASS_NAME.concat("loginConfirm()"));
		
		AdminMemberDto selectedAdminMemberDto =
				adminMemberDao.selectAdmin(adminMemberDto.getA_m_id());
		
		if (selectedAdminMemberDto != null) {
			
			if(passwordEncoder.matches(adminMemberDto.getA_m_pw(), selectedAdminMemberDto.getA_m_pw())) {
				System.out.println(CLASS_NAME.concat("ADMIN LOGIN SUCCESS!!"));
				
				return selectedAdminMemberDto.getA_m_id();
				
			} else {
				System.out.println(CLASS_NAME.concat("ADMIN LOGIN FAIL!!"));
				
				return null;
				
			}
			
		} else {
			System.out.println(CLASS_NAME.concat("ADMIN LOGIN FAIL!!"));
			
			return null;
			
		}
		
	}

}
