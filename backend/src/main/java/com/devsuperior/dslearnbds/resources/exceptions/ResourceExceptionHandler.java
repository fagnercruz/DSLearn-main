package com.devsuperior.dslearnbds.resources.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.devsuperior.dslearnbds.service.exceptions.DatabaseException;
import com.devsuperior.dslearnbds.service.exceptions.ForbiddenException;
import com.devsuperior.dslearnbds.service.exceptions.ResourceNotFoundException;
import com.devsuperior.dslearnbds.service.exceptions.UnauthorizedException;

@ControllerAdvice
public class ResourceExceptionHandler {

	// Para logs
	//private static Logger logger = LoggerFactory.getLogger(ResourceExceptionHandler.class);

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandardError> entityNotFound(ResourceNotFoundException e, HttpServletRequest requerst) {
		StandardError err = new StandardError();
		HttpStatus status = HttpStatus.NOT_FOUND;

		err.setTimestamp(Instant.now());
		err.setStatus(status.value());
		err.setError("Recurso solicitado não foi encontrado");
		err.setMessage(e.getMessage());
		err.setPath(requerst.getRequestURI());	
		return ResponseEntity.status(status).body(err);
	}

	@ExceptionHandler(DatabaseException.class)
	public ResponseEntity<StandardError> database(DatabaseException e, HttpServletRequest requerst) {
		StandardError err = new StandardError();
		HttpStatus status = HttpStatus.BAD_REQUEST;

		err.setTimestamp(Instant.now());
		err.setStatus(status.value());
		err.setError("Database Exception");
		err.setMessage(e.getMessage());
		err.setPath(requerst.getRequestURI());

		return ResponseEntity.status(status).body(err);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ValidationError> validation(MethodArgumentNotValidException e, HttpServletRequest requerst) {
		ValidationError err = new ValidationError();
		HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;

		err.setTimestamp(Instant.now());
		err.setStatus(status.value());
		err.setError("Validation Exception");
		err.setMessage(e.getMessage());
		err.setPath(requerst.getRequestURI());

		for (FieldError f : e.getBindingResult().getFieldErrors()) {
			err.addError(f.getField(), f.getDefaultMessage());
		}

		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(ForbiddenException.class)
	public ResponseEntity<OAuthCustomError> forbidden(ForbiddenException e, HttpServletRequest requerst) {
		OAuthCustomError err = new OAuthCustomError("Forbidden",e.getMessage());
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(err);
	}
	
	@ExceptionHandler(UnauthorizedException.class)
	public ResponseEntity<OAuthCustomError> forbidden(UnauthorizedException e, HttpServletRequest requerst) {
		OAuthCustomError err = new OAuthCustomError("Unauthorized",e.getMessage());
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(err);
	}
}
