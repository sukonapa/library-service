package com.library.net.service;

import java.util.List;

import com.library.net.entity.BookEntity;
import com.library.net.entity.BorrowerEntity;

public interface BookService {

	public String createOrUpdateBook(BookEntity book);

	public String manageLibraryBook(long borrowerId,long bookId,String type);
	
	public String updateBorrowerStatus(long borrowerId,long bookId,String type);

	public List<BookEntity> findAllBooks();

}
