package com.firstexample.democrt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.firstexample.democrt.Exception.CustomerNotFoundException;
import com.firstexample.democrt.Exception.MinBalanceException;
import com.firstexample.democrt.entity.Customer;
import com.firstexample.democrt.entity.PasswordGenerator;
import com.firstexample.democrt.repository.CustomerRepository;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerRepository crepo;
	public Customer saveCustomer(Customer customer) throws MinBalanceException
	{
		if(customer.getBalance()<1000)
		{
			throw new MinBalanceException();
		}
		customer.setPassword(PasswordGenerator.generateRandomPassword(8));
		return crepo.save(customer);
	}
	public Customer fetchCustomerByAccno(Long accno) throws CustomerNotFoundException
	{
		 return crepo.findById(accno).orElseThrow(()-> new CustomerNotFoundException(accno));//Lambda expressions
	}
	public Customer updateCustomer(Customer updated) throws CustomerNotFoundException
	{
		Customer c=fetchCustomerByAccno(updated.getAccno());
		c.setAddress(updated.getAddress());
		c.setName(updated.getName());
		c.setDob(updated.getDob());
		c.setIdproof(updated.getIdproof());
		crepo.save(c);
		return c;
		
	}
	
	public Long deleteCustomer(Long accno) throws CustomerNotFoundException
	{
		Customer c=fetchCustomerByAccno(accno);
		c.setBalance(0);
		crepo.deleteById(accno);
		return accno;
	}
	
	//Service Method for updating password by customer
	public String updatePasswordByAccno(Long accno,String updatedPassword) throws CustomerNotFoundException
	{
		Customer c=fetchCustomerByAccno(accno);
		c.setPassword(updatedPassword);
		crepo.save(c);
		return "Customer with "+accno+" password updated successfully";
	}
	
	public List<Customer> getAllCustomers()
	{
		return crepo.findAll();
	}
	public String validateCustomer(Long accno,String password) throws CustomerNotFoundException
	{
		Customer fetched=fetchCustomerByAccno(accno);
		if(fetched.getAccno().equals(accno))
		{
			if(fetched.getPassword().equals(password))
			{
				return "User Validation Successful";
			}
			return "Please Enter Correct Password";
		}
		return "Wrong Credentials";
	}

	
	
	
	
	

}
