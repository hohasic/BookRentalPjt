package com.office.library.book.admin;

import org.springframework.stereotype.Service;

import com.office.library.book.BookDto;

@Service
public class BookService {

	final private String CLASS_NAME = "[BookService] ";
	
	final static public int BOOK_ISBN_ALREADY_EXIST = 0;	// 이미 등록된 도서
	final static public int BOOK_REGISTER_SUCCESS 	= 1;	// 도서 등록 성공
	final static public int BOOK_REGISTER_FAIL 		= -1;	// 도서 등록 실패
	
	final private BookDao bookDao;
	
	public BookService(BookDao bookDao) {
		this.bookDao = bookDao;
		
	}

	public int registerBookConfirm(BookDto bookDto) {
		System.out.println(CLASS_NAME.concat("registerBookConfirm()"));
		
		// ISBN 중복 체크
		boolean isISBN = bookDao.isISBN(bookDto.getB_isbn());
		
		// 신규 도서 등록
		if (!isISBN) {
			int result = bookDao.insertBook(bookDto);
			
			if (result > 0)
				return BOOK_REGISTER_SUCCESS;
			else
				return BOOK_REGISTER_FAIL;
			
		} else {
			return BOOK_ISBN_ALREADY_EXIST;
			
		}
		
	}
	
}
