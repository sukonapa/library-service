package com.library.net.repositry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.library.net.entity.BorrowerEntity;

import jakarta.transaction.Transactional;

@Repository
public interface BorrowerRepositry extends JpaRepository<BorrowerEntity, Long> {

	@Modifying
	@Transactional
	@Query(value = "UPDATE BorrowerEntity be SET be.bookId=:bookId,be.borrower =:borrower where be.id=:borrowerId")
	public int updateBorrower(long borrowerId, long bookId, String borrower);

}
