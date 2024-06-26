package com.library.net.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.net.entity.BorrowerEntity;
import com.library.net.exception.BorrowerEmptyException;
import com.library.net.repositry.BorrowerRepositry;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class BorrowerServiceImpl implements BorrowerService {

	@Autowired
	private BorrowerRepositry borrowerRepositry;

	@Override
	public BorrowerEntity createBorrower(BorrowerEntity borrower) {
		// TODO Auto-generated method stub
		if (borrower != null) {
			return borrowerRepositry.save(borrower);
		} else {
			throw new BorrowerEmptyException("borrower is Empty, Please Enter Values...!");
		}
	}

	@Override
	public Optional<BorrowerEntity> findBorrowerById(long id) {
		// TODO Auto-generated method stub
		return borrowerRepositry.findById(id);

	}

	@Override
	public List<BorrowerEntity> findAllBorrowers() {
		// TODO Auto-generated method stub

		List<BorrowerEntity> allBorrower = borrowerRepositry.findAll();

		return allBorrower;

	}

}
