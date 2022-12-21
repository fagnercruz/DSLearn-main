package com.devsuperior.dslearnbds.service.exceptions;
/**
 * Para tratar erro 404
 * */
public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 6563888947114371405L;
	
	public ResourceNotFoundException(String msg) {
		super(msg);
	}

}
