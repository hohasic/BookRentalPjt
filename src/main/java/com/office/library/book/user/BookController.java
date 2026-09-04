package com.office.library.book.user;

import org.springframework.stereotype.Controller;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class BookController {

	final private String CLASS_NAME = "[BookController] ";
	
	final private BookService bookService;
	
}
