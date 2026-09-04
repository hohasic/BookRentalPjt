package com.office.library.book.user;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookService {

	final private String CLASS_NAME = "[BookService] ";
	
	final private BookDao bookDao;
	
}
