package com.office.library.user.member;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserMemberService {

	final private String CLASS_NAME = "[UserMemberService] ";
	
	final static public int USER_ACCOUNT_ALREADY_EXIST 		= 0;
	final static public int USER_ACCOUNT_CREATE_SUCCESS 	= 1;
	final static public int USER_ACCOUNT_CREATE_FAIL 		= -1;
	
	final private UserMemberDao userMemberDao;
	final private PasswordEncoder passwordEncoder;
	
	public int createAccountConfirm(UserMemberDto userMemberDto) {
		System.out.println(CLASS_NAME.concat("createAccountConfirm()"));
		
		boolean isMember = userMemberDao.isUserMember(userMemberDto.getU_m_id());
		
		if (!isMember) {
			
			String encodedPassword = passwordEncoder.encode(userMemberDto.getU_m_pw());
			userMemberDto.setU_m_pw(encodedPassword);
			
			int result = userMemberDao.insertUserAccount(userMemberDto);
			
			if (result > 0)
				return USER_ACCOUNT_CREATE_SUCCESS;
			else
				return USER_ACCOUNT_CREATE_FAIL;
			
		} else {
			return USER_ACCOUNT_ALREADY_EXIST;
			
		}
		
	}

	public String loginConfirm(UserMemberDto userMemberDto) {
		System.out.println(CLASS_NAME.concat("loginConfirm()"));
		
		UserMemberDto selectedUserMemberDto = 
				userMemberDao.selectUser(userMemberDto.getU_m_id());
		
		if (selectedUserMemberDto != null) {
			if(passwordEncoder.matches(userMemberDto.getU_m_pw(), selectedUserMemberDto.getU_m_pw())) {
				System.out.println(CLASS_NAME.concat("USER MEMBER LOGIN SUCCESS!!"));
				return selectedUserMemberDto.getU_m_id();
				
			} 
			
			System.out.println(CLASS_NAME.concat("USER MEMBER LOGIN FAIL!!"));
			return null;
			
		} 
		
		System.out.println(CLASS_NAME.concat("USER MEMBER LOGIN FAIL!!"));
		return null;
		
	}

}
