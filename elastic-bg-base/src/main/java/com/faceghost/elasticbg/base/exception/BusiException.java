package com.faceghost.elasticbg.base.exception;

public class BusiException extends RuntimeException {

	public BusiException(){}
	
	public BusiException(String msg){
		super(msg);
	}
	
	public BusiException(Throwable throwable){
		super(throwable);
	}
	
	public BusiException(String msg,Throwable throwable){
		super(msg,throwable);
	}

}
