package com.devsuperior.dslearnbds.service.exceptions;
/**
 *  Para tratar erros 403 - usuário não tem perfil para acessar recurso
 *  
 * */
public class ForbiddenException extends RuntimeException {

	private static final long serialVersionUID = 2804040570812160471L;

	public ForbiddenException(String msg) {
		super(msg);
	}

}
