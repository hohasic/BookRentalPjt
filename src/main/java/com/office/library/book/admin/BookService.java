package com.office.library.book.admin;

import org.springframework.stereotype.Service;

@Service
public class BookService {

	final private BookDao bookDao;
	
	public BookService(BookDao bookDao) {
		this.bookDao = bookDao;
		
	}
	
}
