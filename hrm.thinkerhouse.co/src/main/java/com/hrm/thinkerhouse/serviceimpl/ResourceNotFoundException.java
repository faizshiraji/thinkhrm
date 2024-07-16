package com.hrm.thinkerhouse.serviceimpl;

import org.eclipse.jetty.http.HttpException.RuntimeException;

public class ResourceNotFoundException extends RuntimeException {

	public ResourceNotFoundException(int code) {
        super(code);
    }
	
}
