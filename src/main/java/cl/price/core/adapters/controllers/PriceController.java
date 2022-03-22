package cl.price.core.adapters.controllers;

import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import cl.price.core.adapters.controllers.dto.PriceDto;
import cl.price.core.application.port.in.GetPriceQuery;
import cl.price.core.config.exceptions.models.ExceptionResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/api/v1.0/price")
public class PriceController {	
	
	private final GetPriceQuery getPriceQuery;
	private static final String DATE_PATTERN="yyyy-MM-dd HH:mm:ss";
	
	@Autowired
	public PriceController(GetPriceQuery getPriceQuery) {
		this.getPriceQuery = getPriceQuery;
	}

	@ApiOperation(value = "Query price for a one product")
	@ApiResponses({
		@ApiResponse(code = 200,response = PriceDto.class, message = "OK" ),
		@ApiResponse(code = 404,response = ExceptionResponse.class,message = "Price not found" ),
		@ApiResponse(code = 400,response = ExceptionResponse.class,message = "Bad request" ),
		@ApiResponse(code = 500,response = ExceptionResponse.class,message = "Internal server error" )
	})	
	@GetMapping
	public ResponseEntity<PriceDto> queryPrice(
			@RequestParam  Long productId,
			@RequestParam @DateTimeFormat(pattern = DATE_PATTERN) LocalDateTime applicationDate,
			@RequestParam  Long brandId ) {			
		PriceDto priceDto= PriceDto.of(getPriceQuery.execute(productId,applicationDate,brandId),applicationDate);
		
		return new ResponseEntity<>(priceDto, HttpStatus.OK);
	}	
}
