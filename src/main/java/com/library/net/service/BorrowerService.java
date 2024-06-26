package com.library.net.service;

import java.util.List;
import java.util.Optional;

import com.library.net.entity.BorrowerEntity;

public interface BorrowerService {

	public BorrowerEntity createBorrower(BorrowerEntity borrower);

	public Optional<BorrowerEntity> findBorrowerById(long id);

	public List<BorrowerEntity> findAllBorrowers();

}
