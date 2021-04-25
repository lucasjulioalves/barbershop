package br.com.barbershop.exception;

public class AppBusinessException extends Exception{

	private static final long serialVersionUID = -7743838440102290744L;

	public AppBusinessException(String exception) {
        super(exception);
    }
}
