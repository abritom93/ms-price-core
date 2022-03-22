package cl.price.core.config.exceptions;

import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import cl.price.core.config.exceptions.models.ExceptionResponse;
import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class ErrorHandler{		
	
	private static final String ERROR_TYPE_VALUE_TEMPLATE="Invalid value for field %s, value: %s";
	private static final String ERROR_MISSING_PARAM_VALUE_TEMPLATE="Param %s not is present";
	private static final String ERROR_INTERNAL_SERVER="Internal server error, please contact with the administrator";

	@ExceptionHandler(ResourceNotFoundException.class)
	private ResponseEntity<ExceptionResponse> handleErrorNotFound(ResourceNotFoundException error, HttpServletRequest request) {
		log.error(error.getMessage());
		return new ResponseEntity<>(buildMessageError(error, request), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(MissingServletRequestParameterException.class)
	private ResponseEntity<ExceptionResponse> handleErrorBadRequest(MissingServletRequestParameterException error, HttpServletRequest request) {
		log.error(error.getMessage());
		String msgError= String.format(ERROR_MISSING_PARAM_VALUE_TEMPLATE, error.getParameterName());
		return new ResponseEntity<>(buildMessageError(msgError, request), HttpStatus.BAD_REQUEST);
	}	
	
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	private ResponseEntity<ExceptionResponse> handleErrorMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException error, HttpServletRequest request) {
		log.error(error.getMessage());
		String msgError= String.format(ERROR_TYPE_VALUE_TEMPLATE, error.getName(),error.getValue());
		return new ResponseEntity<>(buildMessageError(msgError,request),HttpStatus.BAD_REQUEST);
	}	
	
	@ExceptionHandler({GenericException.class,Throwable.class})
	private ResponseEntity<ExceptionResponse> handleErrorGenericException(Throwable error, HttpServletRequest request) {
		log.error(error.getMessage());
		return new ResponseEntity<>(buildMessageError(ERROR_INTERNAL_SERVER,request),HttpStatus.INTERNAL_SERVER_ERROR);
	}

	private ExceptionResponse buildMessageError(Throwable error,HttpServletRequest request) {
		return ExceptionResponse.builder()
				.errorMessage(error.getLocalizedMessage())
				.requestUrl(request.getRequestURI())
				.methodHttp(request.getMethod())
				.build();
	}
	
	private ExceptionResponse buildMessageError(String error,HttpServletRequest request) {
		return ExceptionResponse.builder()
				.errorMessage(error)
				.requestUrl(request.getRequestURI())
				.methodHttp(request.getMethod())
				.build();
	}
}
