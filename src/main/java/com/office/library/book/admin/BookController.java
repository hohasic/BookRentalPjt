package com.office.library.book.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

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
	public String registerBookForm() {
		System.out.println(CLASS_NAME.concat("registerBookForm()"));
		
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
	
}
