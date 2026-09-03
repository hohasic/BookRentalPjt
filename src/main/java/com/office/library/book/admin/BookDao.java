package com.office.library.book.admin;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.office.library.book.BookDto;

@Repository
public class BookDao {

	final private String CLASS_NAME = "[BookDao] ";
	
	final private JdbcTemplate jdbcTemplate;
	
	public BookDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
		
	}
	
	public boolean isISBN(String b_isbn) {
		System.out.println(CLASS_NAME.concat("isISBN()"));
		
		String sql =  "SELECT COUNT(*) FROM "
					+ "tbl_book WHERE b_isbn = ?";
		
		int result = jdbcTemplate.queryForObject(sql, Integer.class, b_isbn);
		
		return result > 0 ? true : false;
		
	}

	public int insertBook(BookDto bookDto) {
		System.out.println(CLASS_NAME.concat("insertBook()"));
		
		String sql =  "INSERT INTO "
						+ "tbl_book("
							+ "b_thumbnail, "
							+ "b_name, "
							+ "b_author, "
							+ "b_pulisher, "
							+ "b_pulish_year, "
							+ "b_isbn, "
							+ "b_call_number, "
							+ "b_rantal_able) "
						+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
		
		int result = -1;
		try {
			result = jdbcTemplate.update(sql, 
											bookDto.getB_thumbnail(), 
											bookDto.getB_name(), 
											bookDto.getB_author(), 
											bookDto.getB_pulisher(), 
											bookDto.getB_pulish_year(), 
											bookDto.getB_isbn(), 
											bookDto.getB_call_number(), 
											bookDto.getB_rantal_able()
											);
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		return result;
		
	}

}
