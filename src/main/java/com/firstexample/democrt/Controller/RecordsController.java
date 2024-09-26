package com.firstexample.democrt.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.firstexample.democrt.Exception.CustomerNotFoundException;
import com.firstexample.democrt.Exception.InsufficientFundsException;
import com.firstexample.democrt.Exception.MinBalanceException;
import com.firstexample.democrt.Exception.RecordNotFoundException;
import com.firstexample.democrt.entity.Records;
import com.firstexample.democrt.service.RecordService;

@RestController
public class RecordsController {
	
	@Autowired
	private RecordService service;
	
	@PostMapping("/record/{accno}")
	public Records saveRecord(@RequestBody Records r,@PathVariable Long accno)
	throws CustomerNotFoundException,MinBalanceException,InsufficientFundsException
	{
		return service.saveRecordByAccno(r, accno);
	}
	@GetMapping("/record/{tid}")
	public Records getRecord(@PathVariable Long tid )throws RecordNotFoundException
	{
		return service.getRecordByTid(tid);
	}
	
	@GetMapping("/allrecords/{accno}")
	public List<Records> getRecordByAccno(@PathVariable Long accno)throws RecordNotFoundException
	{
		return  service.get10RecordsByAccno(accno);
	}

}
