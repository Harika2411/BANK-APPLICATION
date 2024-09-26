package com.firstexample.democrt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.firstexample.democrt.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
	
	
	

}

