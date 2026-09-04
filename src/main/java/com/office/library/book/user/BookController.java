package com.office.library.book.user;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.office.library.book.BookDto;

import lombok.RequiredArgsConstructor;

//@Controller("user.BookController")
@Controller
@RequiredArgsConstructor
@RequestMapping("/book/user")
public class BookController {

	final private String CLASS_NAME = "[BookController] ";
	
	final private BookService bookService;
	
	/*
	 * 도서 검색 확인
	 * /book/user/searchBookConfirm
	 */
	@GetMapping("/searchBookConfirm")
	public String searchBookConfirm(
			@RequestParam(value="b_name", required=false) String b_name, 
			Model model) {
		System.out.println(CLASS_NAME.concat("searchBookConfirm()"));
		
		String nextPage = "user/book/search_book";
		
		List<BookDto> bookDtos = bookService.searchBookConfirm(b_name);
		model.addAttribute("bookDtos", bookDtos);
		
		return nextPage;
		
	}
		
	
}
