package br.com.fasttrack.desafio.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.fasttrack.desafio.config.validation.ErrorRequestDTO;
import br.com.fasttrack.desafio.config.validation.ExceptionResponse;
import br.com.fasttrack.desafio.exception.OrderNotExistsException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class ErrorHandler {

	@Autowired
	private MessageSource messageSource;
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<ErrorRequestDTO> handle(MethodArgumentNotValidException exception) {
		List<ErrorRequestDTO> dto = new ArrayList<>();
		
		List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
		fieldErrors.forEach(e -> {
			String message = messageSource.getMessage(e, LocaleContextHolder.getLocale());
			ErrorRequestDTO erro = new ErrorRequestDTO(e.getField(), message);
			dto.add(erro);
		});
		
		return dto;
	}
	
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	@ExceptionHandler(OrderNotExistsException.class)
	public ExceptionResponse handle(OrderNotExistsException exception) {
		return new ExceptionResponse(HttpStatus.NOT_FOUND.value(), exception.getMessage());
	}
	
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	public ExceptionResponse handle(Exception exception) {
		log.error("ERROR:" +exception.getMessage());
		return new ExceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "An unexpected error has occurred. Try again");
	}
}
