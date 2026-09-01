package com.office.library.admin.member;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AdminMemberDao {
	
	final private String CLASS_NAME = "[AdminMemberDao] ";
	
	final private JdbcTemplate jdbcTemplate;
	
//	@Autowired
	public AdminMemberDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
		
	}
	
	public boolean isAdminMember(String a_m_id) {
		System.out.println(CLASS_NAME.concat("isAdminMember()"));

		String sql =  "SELECT COUNT(*) FROM tbl_admin_member "
					+ "WHERE a_m_id = ?";
		
		int result = jdbcTemplate.queryForObject(sql, Integer.class, a_m_id);
		
		return result > 0 ? true : false;
		
	}

	public int insertAdminAccount(AdminMemberDto adminMemberDto) {
		System.out.println(CLASS_NAME.concat("insertAdminAccount()"));
		
		// super admin
		
//		String sql =  "INSERT INTO tbl_admin_member(a_m_id, a_m_pw, a_m_name, a_m_gender, a_m_part, a_m_position, a_m_mail, a_m_phone) "
//					+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
		
//		String sql =  "INSERT INTO tbl_admin_member(a_m_approval, a_m_id, a_m_pw, a_m_name, a_m_gender, a_m_part, a_m_position, a_m_mail, a_m_phone) "
//				+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
//		
//		int result = jdbcTemplate.update(sql, 
//				1,
//				adminMemberDto.getA_m_id(),
//				adminMemberDto.getA_m_pw(),
//				adminMemberDto.getA_m_name(),
//				adminMemberDto.getA_m_gender(),
//				adminMemberDto.getA_m_part(),
//				adminMemberDto.getA_m_position(),
//				adminMemberDto.getA_m_mail(),
//				adminMemberDto.getA_m_phone());
		
		List<String> args = new ArrayList<String>();
		
		String sql = "INSERT INTO tbl_admin_member(";
		if (adminMemberDto.getA_m_id().equals("super admin")) {
			sql += "a_m_approval, ";
			args.add("1");
			
		}
		
		sql += "a_m_id, ";
		args.add(adminMemberDto.getA_m_id());
		
		sql += "a_m_pw, ";
		args.add(adminMemberDto.getA_m_pw());
		
		sql += "a_m_name, ";
		args.add(adminMemberDto.getA_m_name());
		
		sql += "a_m_gender, ";
		args.add(adminMemberDto.getA_m_gender());
		
		sql += "a_m_part, ";
		args.add(adminMemberDto.getA_m_part());
		
		sql += "a_m_position, ";
		args.add(adminMemberDto.getA_m_position());
		
		sql += "a_m_mail, ";
		args.add(adminMemberDto.getA_m_mail());
		
		sql += "a_m_phone) ";
		args.add(adminMemberDto.getA_m_phone());
		
		if (adminMemberDto.getA_m_id().equals("super admin")) {
			sql += "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
		} else {
			sql += "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
			
		}
		
		int result = jdbcTemplate.update(sql, args.toArray());
		
		
		return result;
		
	}

}
