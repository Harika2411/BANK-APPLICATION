package com.firstexample.democrt.Exception;

public class RecordNotFoundException extends Exception {

	public RecordNotFoundException(Long tid) {
		super("Record  with tid"+ tid +"not found");
	}
	

}
