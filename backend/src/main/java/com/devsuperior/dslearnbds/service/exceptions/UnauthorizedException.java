package com.devsuperior.dslearnbds.service.exceptions;

/**
 * Para tratar erros 401 - Não autorizado (token inválido)
 * */
public class UnauthorizedException extends RuntimeException {

	private static final long serialVersionUID = 2804040570812160471L;

	public UnauthorizedException(String msg) {
		super(msg);
	}

}
