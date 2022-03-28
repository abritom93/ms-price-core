package cl.price.core.adapters.controllers;

import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import cl.price.core.adapters.controllers.dto.PriceDto;
import cl.price.core.application.port.in.GetPriceQuery;

@RestController
@RequestMapping(value = "/price")
public class PriceController {	
	
	private final GetPriceQuery getPriceQuery;
	private static final String DATE_PATTERN="yyyy-MM-dd HH:mm:ss";

	public PriceController(GetPriceQuery getPriceQuery) {
		this.getPriceQuery = getPriceQuery;
	}

	@GetMapping
	public ResponseEntity<PriceDto> queryPrice(
			@RequestParam  Long productId,
			@RequestParam @DateTimeFormat(pattern = DATE_PATTERN) LocalDateTime applicationDate,
			@RequestParam  Long brandId ) {			
		PriceDto priceDto= PriceDto.of(getPriceQuery.execute(productId,applicationDate,brandId));
		
		return new ResponseEntity<>(priceDto, HttpStatus.OK);
	}	
}
