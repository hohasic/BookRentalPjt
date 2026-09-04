package com.office.library.book.user;

import java.util.List;

import org.springframework.stereotype.Service;

import com.office.library.book.BookDto;

import lombok.RequiredArgsConstructor;

//@Service("user.BookService")
@Service
@RequiredArgsConstructor
public class BookService {

	final private String CLASS_NAME = "[BookService] ";
	
	final private BookDao bookDao;

	public List<BookDto> searchBookConfirm(String b_name) {
		System.out.println(CLASS_NAME.concat("searchBookConfirm"));
		
		return bookDao.selectBooksBySearch(b_name);
		
	}
	
}
