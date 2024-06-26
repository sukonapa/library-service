package com.library.net.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.library.net.entity.BookEntity;
import com.library.net.exception.BookEmptyException;
import com.library.net.exception.BooksNotFoundException;
import com.library.net.service.BookService;

@RestController
@RequestMapping("v1/api/book")
public class BookController {

	@Autowired
	private BookService bookService;

	@PostMapping("/register")
	public String registerBook(@RequestBody BookEntity bookEntity) {

		if (bookEntity != null) {
			return bookService.createOrUpdateBook(bookEntity);
		} else {
			throw new BookEmptyException("Book is Empty, Please Enter Values...!");
		}

	}

	
	  @PutMapping("/borrowBook")
	  public ResponseEntity<BookEntity> borrowBook(@RequestParam long id,@RequestParam long bookId) {
	  
		  bookService.borrowBook(id,bookId);
		  
		  return null;
	  
	  }
	 

	@GetMapping("/list")
	public List<BookEntity> getAllBooks() {
		List<BookEntity> listOfBooks = bookService.findAllBooks();
		if(listOfBooks != null && listOfBooks.size()>0) {
			return listOfBooks;
		}else {
			throw new BooksNotFoundException("No Books Available in The Liberary");
		}
	}

}
