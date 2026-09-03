package com.office.library.book.admin;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.office.library.book.BookDto;
import com.office.library.book.admin.util.UploadFileService;

@Controller
@RequestMapping("/book/admin")   // /book/admin/registerBookForm
public class BookController {
	
	final private String CLASS_NAME = "[BookController] ";
	
	final private BookService bookService;
	final private UploadFileService uploadFileService;
	
	public BookController(BookService bookService, 
			UploadFileService uploadFileService) {
		this.bookService = bookService;
		this.uploadFileService = uploadFileService;
		
	}
	
	/*
	 * 도서 등록 양식
	 * /book/admin/registerBookForm
	 */
	@GetMapping("/registerBookForm")
	public String registerBookForm(HttpSession session) {
		System.out.println(CLASS_NAME.concat("registerBookForm()"));
		
		if (session.getAttribute("loginedAdminMemberId") == null) {
			return "redirect:/admin/member/loginForm";
		}
		
		String nextPage = "admin/book/register_book_form";
		
		return nextPage;
		
	}
	
	/*
	 * 도서 등록 확인
	 * /book/admin/registerBookConfirm
	 */
	@PostMapping("/registerBookConfirm")
	public String registerBookConfirm(BookDto bookDto, 
			@RequestParam("file") MultipartFile file) {
		System.out.println(CLASS_NAME.concat("registerBookConfirm()"));
		
		String nextPage = "admin/book/register_book_ok";
		
		// SAVE FILE
//		UploadFileService uploadFileService = new UploadFileService();
		String savedFileName = uploadFileService.upload(file);
		
		if (savedFileName != null) {
			bookDto.setB_thumbnail(savedFileName);
			int result = bookService.registerBookConfirm(bookDto);
			
			if (result <= 0)
				nextPage = "admin/book/register_book_ng";
			
		} else {
			nextPage = "admin/book/register_book_ng";
			
		}
		
		return nextPage;
		
	}
	
	/*
	 * 도서 검색 확인
	 * /book/admin/searchBookConfirm
	 */
	/*
	@GetMapping("/searchBookConfirm")
	public String searchBookConfirm(
			@RequestParam("b_name") String b_name, 
			Model model) {
		System.out.println(CLASS_NAME.concat("searchBookConfirm()"));
		
		String nextPage = "admin/book/search_book";
		
		List<BookDto> bookDtos = bookService.searchBookConfirm(b_name);
		model.addAttribute("bookDtos", bookDtos);
		
		return nextPage;
		
	}
	*/
	
	@GetMapping("/searchBookConfirm")
	public ModelAndView searchBookConfirm(
			@RequestParam("b_name") String b_name) {
		System.out.println(CLASS_NAME.concat("searchBookConfirm()"));
		
		String nextPage = "admin/book/search_book";
		
		List<BookDto> bookDtos = bookService.searchBookConfirm(b_name);
		
		ModelAndView modelAndView = new ModelAndView();
		// 뷰 설정
		modelAndView.setViewName(nextPage);
		
		// 데이터 주입
		modelAndView.addObject("bookDtos", bookDtos);
		
		return modelAndView;
		
	}
	
	/*
	 * 도서 상세 페이지
	 * /book/admin/bookDetail
	 */
	@GetMapping("/bookDetail")
	public String bookDetail(
			@RequestParam("b_no") int b_no, 
			Model model,
			HttpSession session) {
		System.out.println(CLASS_NAME.concat("bookDetail()"));
		
		if (session.getAttribute("loginedAdminMemberId") == null) {
			return "redirect:/admin/member/loginForm";
		}
		
		String nextPage = "admin/book/book_detail";
		
		BookDto bookDto = bookService.bookDetail(b_no);
		model.addAttribute("bookDto", bookDto);
		
		return nextPage;
		
	}
	
	/*
	 * 도서 수정 양식
	 * /book/admin/modifyBookForm
	 */
	@GetMapping("/modifyBookForm")
	public String modifyBookForm(
			@RequestParam("b_no") int b_no, 
			Model model, 
			HttpSession session) {
		System.out.println(CLASS_NAME.concat("modifyBookForm()"));
		
		if (session.getAttribute("loginedAdminMemberId") == null) {
			return "redirect:/admin/member/loginForm";
		}
		
		String nextPage = "admin/book/modify_book_form";
		
		BookDto bookDto = bookService.modifyBookForm(b_no);
		model.addAttribute("bookDto", bookDto);
		
		return nextPage;
				
	}
	
	/*
	 * 도서 수정 확인
	 * /book/admin/modifyBookConfirm
	 */
	@PostMapping("/modifyBookConfirm")
	public String modifyBookConfirm(
			BookDto bookDto, 
			@RequestParam("file") MultipartFile file) {
		System.out.println(CLASS_NAME.concat("modifyBookConfirm()"));
		
		String nextPage = "admin/book/modify_book_ok";
		
		// SAVE FILE
		if (!file.getOriginalFilename().equals("")) {
			
			String savedFileName = uploadFileService.upload(file);
			if (savedFileName != null) {
				bookDto.setB_thumbnail(savedFileName);
				
			}
			
		}
		
		int result = bookService.modifyBookConfirm(bookDto);
		if (result <= 0)
			nextPage = "admin/book/modify_book_ng";
		
		return nextPage;
		
	}
	
	/*
	 * 도서 삭제 확인
	 * /book/admin/deleteBookConfirm
	 */
	@GetMapping("/deleteBookConfirm")
	public String deleteBookConfirm(
			@RequestParam("b_no") int b_no, 
			HttpSession session) {
		System.out.println(CLASS_NAME.concat("deleteBookConfirm()"));
		
		if (session.getAttribute("loginedAdminMemberId") == null) {
			return "redirect:/admin/member/loginForm";
		}
		
		String nextPage = "admin/book/delete_book_ok";
		
		int result = bookService.deleteBookConfirm(b_no);
		
		if (result <= 0)
			nextPage = "admin/book/delete_book_ng";
		
		return nextPage;
		
	}
	
	
}









