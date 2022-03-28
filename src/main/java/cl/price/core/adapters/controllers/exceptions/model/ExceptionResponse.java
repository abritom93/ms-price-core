package cl.price.core.adapters.controllers.exceptions.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ExceptionResponse {
	
	String requestUrl;
	String errorMessage;
	String methodHttp;
}
