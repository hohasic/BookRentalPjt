package com.office.library.admin.member;

import org.springframework.stereotype.Service;

@Service
public class AdminMemberService {

	final private String CLASS_NAME = "[AdminMemberService] ";
	
	final private AdminMemberDao adminMemberDao;
	
	public AdminMemberService(AdminMemberDao adminMemberDao) {
		this.adminMemberDao = adminMemberDao;
		
	}

	public int createAccountConfirm(AdminMemberDto adminMemberDto) {
		System.out.println(CLASS_NAME.concat("createAccountConfirm()"));
		
		// 1. 아이디 중복 체크
		boolean isMember = adminMemberDao.isAdminMember(adminMemberDto.getA_m_id());
		System.out.println(CLASS_NAME.concat("isMember: " + isMember));
		
		// 2. 관리자 회원 가입
		if (!isMember) {
			int result = adminMemberDao.insertAdminAccount(adminMemberDto);
			
			if (result > 0) {
				System.out.println(CLASS_NAME.concat("ADMIN NEW MEMBER CREATE ACCOUNT SUCCESS!!"));
				
			} else {
				System.out.println(CLASS_NAME.concat("ADMIN NEW MEMBER CREATE ACCOUNT FAIL!!"));
				
			}
			
		} else {
			System.out.println(CLASS_NAME.concat("ADMIN NEW MEMBER CREATE ACCOUNT FAIL!!"));
			
		}
		
		return 0;
		
	}
	
	
	
}
