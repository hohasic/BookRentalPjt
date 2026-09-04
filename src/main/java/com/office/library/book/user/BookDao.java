package com.office.library.book.user;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.office.library.book.BookDto;

import lombok.RequiredArgsConstructor;

//@Repository("user.BookDao")
@Repository
@RequiredArgsConstructor
public class BookDao {

	final private String CLASS_NAME = "[BookDao] ";

	final private JdbcTemplate jdbcTemplate;
	
	
	public List<BookDto> selectBooksBySearch(String b_name) {
		System.out.println(CLASS_NAME.concat("selectBooksBySearch()"));
		
		String sql =  "SELECT * FROM tbl_book "
					+ "WHERE b_name LIKE ? ORDER BY b_no DESC";
		
		List<BookDto> bookDtos = null;
		
		try {
			
			RowMapper<BookDto> rowMapper = 
					BeanPropertyRowMapper.newInstance(BookDto.class);
			bookDtos = jdbcTemplate.query(sql, rowMapper, "%" + b_name + "%");			
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		return bookDtos.size() > 0 ? bookDtos : null;
		
	}
	
}
