package br.com.fasttrack.desafio.exception;

public class OrderNotExistsException extends Exception{

	private static final long serialVersionUID = 1L;
	
	public OrderNotExistsException(String msg) {
		super(msg);
	}

}
