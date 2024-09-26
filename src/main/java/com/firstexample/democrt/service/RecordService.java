package com.firstexample.democrt.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.firstexample.democrt.Exception.CustomerNotFoundException;
import com.firstexample.democrt.Exception.InsufficientFundsException;
import com.firstexample.democrt.Exception.MinBalanceException;
import com.firstexample.democrt.Exception.RecordNotFoundException;
import com.firstexample.democrt.entity.Customer;
import com.firstexample.democrt.entity.Records;
import com.firstexample.democrt.repository.CustomerRepository;
import com.firstexample.democrt.repository.RecordRepo;

@Service
public class RecordService {
	
	
	@Autowired
	private RecordRepo rr;
	
	@Autowired
	private CustomerService cs;
	
	@Autowired
	private CustomerRepository cr;
	
	
	public Records saveRecordByAccno(Records r,Long accno)throws CustomerNotFoundException,MinBalanceException,InsufficientFundsException
	{
		
		Customer c=cs.fetchCustomerByAccno(accno);
		System.out.println(r);
		
		// 1 for withdraw
		if(r.getRecordtype()==1)
		{
		double res=c.getBalance()-r.getAmount();
		if(res>=0)
		c.setBalance(res);
		else
			throw new InsufficientFundsException("insufficientfunds");
		}
		else if(r.getRecordtype()==2)
		{
			c.setBalance(c.getBalance()+r.getAmount());	
		}
		r.setCustomer(c);//set customer for transact
		cs.saveCustomer(c);//updating balance of trans
		r.setTime(LocalDateTime.now());
		return rr.save(r);//Saving record persistently
	}
	
	public Records getRecordByTid(Long tid)throws RecordNotFoundException
	{
		return rr.findById(tid).orElseThrow(()->new RecordNotFoundException(tid));
	}
	public List<Records> get10RecordsByAccno(Long accno)
	{
		return rr.findTop10ByCustomerAccnoOrderByTimeDesc(accno);
	}

}
