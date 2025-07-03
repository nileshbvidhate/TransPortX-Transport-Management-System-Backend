package com.nil.transportx.exceptions;

public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 4142093601849761875L;

	public ResourceNotFoundException(String msg)
	{
		super(msg);
	}
}
