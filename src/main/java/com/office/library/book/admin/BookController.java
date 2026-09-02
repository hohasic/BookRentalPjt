package com.office.library.book.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/book/admin")
public class BookController {

	final private BookService bookService;
	
	public BookController(BookService bookService) {
		this.bookService = bookService;
		
	}
	
}
