package com.office.library.book.user;

import java.util.List;

import javax.servlet.http.HttpSession;

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
		
		b_name = b_name == null ? "with" : b_name;
		
		String nextPage = "user/book/search_book";
		
		List<BookDto> bookDtos = bookService.searchBookConfirm(b_name);
		model.addAttribute("bookDtos", bookDtos);
		
		return nextPage;
		
	}
	
	/*
	 * 도서 상세 화면
	 * /book/user/bookDetail
	 */
	@GetMapping("/bookDetail")
	public String bookDetail(@RequestParam("b_no") int b_no, Model model) {
		System.out.println(CLASS_NAME.concat("bookDetail()"));
		
		String nextPage = "user/book/book_detail";
		
		BookDto bookDto = bookService.bookDetail(b_no);
		model.addAttribute("bookDto", bookDto);
		
		return nextPage;
		
	}
	
	/*
	 * 도서 대출 확인
	 * /book/user/rentalBookConfirm
	 */
	@GetMapping("/rentalBookConfirm")
	public String rentalBookConfirm(
			@RequestParam("b_no") int b_no, 
			HttpSession session) {
		System.out.println(CLASS_NAME.concat("rentalBookConfirm()"));
		
		String nextPage = "user/book/rental_book_ok";
		
		/*
		Object object = session.getAttribute("loginedUserMemberId");
		
		if (object != null) {
			int result = bookService.rentalBookConfirm(b_no, 0);
			
		} else {
			nextPage = "redirect:/user/member/loginForm";
			
		}
		*/
		
		int result = bookService.rentalBookConfirm(b_no, 0);

		return nextPage;
		
	}
		
	
}
