package com.nil.transportx.exceptions;

public class DatabaseOperationException extends RuntimeException{

	private static final long serialVersionUID = -6402148148026593046L;

	public DatabaseOperationException(String msg)
	{
		super(msg);
	}
}
