package cl.price.core.adapters.controllers.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import cl.price.core.domain.Price;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class PriceDto{	
	
	 Long productId;
	 Long brandId;
	 Long priceList;
	 LocalDateTime applicationDate;
	 BigDecimal price;
	
	public static PriceDto of(Price price,LocalDateTime applicationDate) {
		return PriceDto.builder()
				.productId(price.getProductId())
				.brandId(price.getBrandId())
				.priceList(price.getPriceList())
				.applicationDate(applicationDate)
				.price(price.getPrice())
				.build();
	}
}
