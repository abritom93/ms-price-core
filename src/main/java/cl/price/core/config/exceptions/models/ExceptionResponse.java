package cl.price.core.config.exceptions.models;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ExceptionResponse {
	
	String requestUrl;
	String errorMessage;
	String methodHttp;
}
