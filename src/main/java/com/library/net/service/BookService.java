package com.library.net.service;

import java.util.List;

import com.library.net.entity.BookEntity;
import com.library.net.entity.BorrowerEntity;

public interface BookService {

	public String createOrUpdateBook(BookEntity book);

	public String borrowBook(long borrowerId,long bookId);
	
	public String updateBorrower(long borrowerId,long bookId);

	public List<BookEntity> findAllBooks();

}
