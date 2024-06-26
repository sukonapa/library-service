package com.library.net.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.net.entity.BookEntity;
import com.library.net.entity.BorrowerEntity;
import com.library.net.exception.BookEmptyException;
import com.library.net.exception.BorrowerEmptyException;
import com.library.net.repositry.BookRepositry;
import com.library.net.repositry.BorrowerRepositry;

import jakarta.transaction.Transactional;

@Transactional
@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookRepositry bookRepositry;

	@Autowired
	private BorrowerRepositry borrowerRepositry;

	@Override
	public String createOrUpdateBook(BookEntity bookEntity) {
		Optional<BookEntity> bke = findDuplicateBook(bookEntity);
		int bookCount = 0;
		if (bke.isEmpty()) {
			bookEntity.setCount(bookCount++);
			bookRepositry.save(bookEntity);
			return "Book has been placd into librery";
		} else {
			// we can change Optional to book but time being I am using Optional
			int updateStatus = bookRepositry.updateBookCount(bke.get().getCount() + 1, bke.get().getId());
			if (updateStatus != 0) {
				return "Count has been updated for duplicate book";
			}

		}
		return "";
	}

	public Optional<BookEntity> findDuplicateBook(BookEntity book) {
		return bookRepositry.validateBook(book.getIsbn(), book.getAuthor(), book.getTitle());

	}

	@Override
	public String borrowBook(long borrowerid, long bookId) {
		// find the valid book and update the count book table
		Optional<BookEntity> bke = bookRepositry.findById(bookId);
		if (bke.get().getCount() > 0) {
			int i = bookRepositry.updateBookCount(bke.get().getCount() - 1, bookId);

			// Update the borrower table with book Id along with flag
			return updateBorrower(borrowerid, bookId);

		} else {
			throw new BookEmptyException("Book is Empty, Please Register the book...!");
		}

	}

	@Override
	public String updateBorrower(long borrowerId, long bookId) {

		Optional<BorrowerEntity> borrowerEntity = borrowerRepositry.findById(borrowerId);
		if (borrowerEntity.isEmpty()) {
			throw new BorrowerEmptyException("borrower is Empty, Please Enter Values...!");
		} else {
			// flag = Y mean borrow the book ;
			borrowerRepositry.updateBorrower(borrowerId, borrowerId, "Y");
		}
		return "Borrower taken" + bookId + ":new book from librery";
	}

	@Override
	public List<BookEntity> findAllBooks() {
		return bookRepositry.findAll();
	}

}
