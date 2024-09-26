package com.firstexample.democrt.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.firstexample.democrt.Exception.CustomerNotFoundException;
import com.firstexample.democrt.Exception.MinBalanceException;
import com.firstexample.democrt.entity.Customer;
import com.firstexample.democrt.service.CustomerService;

@RestController
public class CustomerController {
	
	@Autowired
	private CustomerService service;
	
	@PostMapping("/savecustomer")
	public Customer saveCustomer(@RequestBody Customer c)throws CustomerNotFoundException,MinBalanceException
	{
		 return service.saveCustomer(c);
		 
		
	}
	
	@GetMapping("/customer/{accno}")
	public Customer getCustomerByAcc(@PathVariable Long accno)throws CustomerNotFoundException
	{
		return service.fetchCustomerByAccno(accno);
		
	}
	@GetMapping("/customer")
	public List<Customer>getAllCustomers()
	{
	return service.getAllCustomers();	
	}
	
	@PutMapping("/customer")
	public Customer updateCustomerByAcc(@RequestBody Customer updatedcustomer)throws CustomerNotFoundException
	{
	   return service.updateCustomer(updatedcustomer);
	}
	
	@DeleteMapping("/customer/{accno}")
	public String deleteCustomerByAcc(@PathVariable Long accno)throws CustomerNotFoundException
	{
		long no=service.deleteCustomer(accno);
		return "customer with id" + no +  "deleted";
	}
	@PutMapping("/customer/{accno}/{password}")
	public String updatePassword(@PathVariable Long accno,@PathVariable String password)throws CustomerNotFoundException
	{
		return service.updatePasswordByAccno(accno, password);
	}
	
	@PostMapping("/validateCustomer/{accno}/{password}")
    public String validateCustomer(@PathVariable Long accno,@PathVariable String password)throws CustomerNotFoundException
    {
		return service.validateCustomer(accno, password);
    }
}
