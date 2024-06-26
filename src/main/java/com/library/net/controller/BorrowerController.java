package com.library.net.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.net.entity.BorrowerEntity;
import com.library.net.exception.BorrowerEmptyException;
import com.library.net.service.BorrowerService;

@RestController
@RequestMapping("/v1/api/borrower")
public class BorrowerController {

	@Autowired
	private BorrowerService borrowerService;

	@PostMapping("/register")
	public BorrowerEntity registerBorrower(@RequestBody BorrowerEntity borrower) {

		if (borrower != null) {
			return borrowerService.createBorrower(borrower);
		} else {
			throw new BorrowerEmptyException("Borrower is Empty, Please Enter Values...!");
		}

	}

	
}
