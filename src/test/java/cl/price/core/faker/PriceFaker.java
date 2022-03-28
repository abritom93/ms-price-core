package cl.price.core.faker;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import cl.price.core.adapters.controllers.dto.PriceDto;

public class PriceFaker {

	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	public static PriceDto createPriceProductDto(Long brandId,Long productId,Long priceList,BigDecimal price,String startDate,String endDate) {
		return PriceDto.builder()
				.brandId(brandId)
				.productId(productId)
				.priceList(priceList)
				.price(price)
				.endDate(LocalDateTime.parse(endDate,formatter))
				.startDate(LocalDateTime.parse(startDate,formatter))
				.build();
	}
	
	public static MultiValueMap<String, String> createParamsQueryPriceProduct(String productId, String brandId, String applicationDate) {
		    MultiValueMap<String, String> params= new LinkedMultiValueMap<>();
	        params.add("productId", productId);
	        params.add("brandId", brandId);
	        params.add("applicationDate", applicationDate);
	        return params;
	}
}
