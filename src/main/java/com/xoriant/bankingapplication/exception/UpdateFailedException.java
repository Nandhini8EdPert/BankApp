package com.xoriant.bankingapplication.exception;

public class UpdateFailedException extends Exception {

	private static final long serialVersionUID = 1L;

	public UpdateFailedException(String msg) {
		super(msg);
	}
}
