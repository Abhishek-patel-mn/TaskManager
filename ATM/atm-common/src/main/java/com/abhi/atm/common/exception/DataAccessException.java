package com.abhi.atm.common.exception;

/**
 * @author Abhishek Patel M N
 * Aug 8, 2017 9:47:07 PM 2017 
 */
public class DataAccessException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public DataAccessException(String mesaage) {
		super(mesaage);
	}
	
}