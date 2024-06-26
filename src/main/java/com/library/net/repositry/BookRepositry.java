package com.library.net.repositry;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.library.net.entity.BookEntity;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface BookRepositry extends JpaRepository<BookEntity, Long> {
	@Query(value = "SELECT bk FROM BookEntity bk where bk.isbn=:isbn and bk.author=:author and bk.title=:title")
	public Optional<BookEntity> validateBook(String isbn, String author, String title);

	@Modifying
	@Query(value = "UPDATE BookEntity bk SET bk.count=:count Where bk.id=:id")
	public int updateBookCount(int count, long id);

}
