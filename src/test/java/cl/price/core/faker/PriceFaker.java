package cl.price.core.faker;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import cl.price.core.adapters.controllers.dto.PriceDto;
import cl.price.core.adapters.jdbc.entities.PriceEntity;
import cl.price.core.domain.Price;

public class PriceFaker {

	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	public static Price createPriceProduct() {
		return Price.builder()
				.id(1L)
				.brandId(1L)
				.productId(35455L)
				.currency("EUR")
				.priceList(3L)
				.priority(1)
				.startDate(LocalDateTime.parse("2020-06-15 00:00:00",formatter))
				.endDate(LocalDateTime.parse("2020-06-15 11:00:00",formatter))
				.price(new BigDecimal("30.50"))
				.build();
	}
	
	public static Optional<PriceEntity> createPriceProductEntity() {
		return Optional.of(PriceEntity.builder()
				.id(1L)
				.brandId(1L)
				.productId(35455L)
				.currency("EUR")
				.priceList(3L)
				.priority(1)
				.startDate(LocalDateTime.parse("2020-06-15 00:00:00",formatter))
				.endDate(LocalDateTime.parse("2020-06-15 11:00:00",formatter))
				.price(new BigDecimal("30.50"))
				.build()
				);
	}
	
	public static PriceDto createPriceProductDto() {
		return PriceDto.builder()
				.brandId(1L)
				.productId(35455L)
				.priceList(3L)
				.price(new BigDecimal("30.50"))
				.applicationDate(LocalDateTime.parse("2020-06-15 11:00:00",formatter))
				.build();
	}
	
	public static MultiValueMap<String, String> createParamsQueryPriceProduct() {
		    MultiValueMap<String, String> params= new LinkedMultiValueMap<>();
	        params.add("productId", "1");
	        params.add("brandId", "1");
	        params.add("applicationDate", "2020-06-15 11:00:00");	        
	        return params;
	}

	public static MultiValueMap<String, String> createParamsQueryPriceProductMissingParam() {
		MultiValueMap<String, String> params= new LinkedMultiValueMap<>();
		params.add("brandId", "1");
		params.add("applicationDate", "2020-06-15 11:00:00");
		return params;
	}

	public static MultiValueMap<String, String> createParamsQueryPriceProductArgumentTypeMismatch() {
		MultiValueMap<String, String> params= new LinkedMultiValueMap<>();
		params.add("productId", "abc");
		params.add("brandId", "1");
		params.add("applicationDate", "2020-06-15 11:00:00");
		return params;
	}
	
	public static LocalDateTime createLocalDateTimeMock(String date) {
		return LocalDateTime.parse(date,formatter);
	}
}
