package com.abhi.atm.common.exception;

/**
 * @author Abhishek Patel M N
 * Aug 8, 2017 9:47:07 PM 2017 
 */
public class BusinessServiceException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public BusinessServiceException(String mesaage) {
		super(mesaage);
	}
	
}