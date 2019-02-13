package com.faceghost.elasticbg.base.exception;

public class RootException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public RootException(){}
	
	
	public RootException(String msg){
		super(msg);
	}
	
	public RootException(Throwable throwable){
		super(throwable);
	}
	
	public RootException(String msg,Throwable throwable){
		super(msg,throwable);
	}

}
