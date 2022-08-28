package com.xoriant.bankingapplication.exception;

public class NotExistsException extends Exception {

	private static final long serialVersionUID = 1L;

	public NotExistsException(String msg) {
		super(msg);
	}
}
