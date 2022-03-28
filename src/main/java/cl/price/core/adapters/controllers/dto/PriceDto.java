package cl.price.core.adapters.controllers.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;
import cl.price.core.domain.Price;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class PriceDto{	
	
	 Long productId;
	 Long brandId;
	 Long priceList;
	 @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
	 LocalDateTime startDate;
	 @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
	 LocalDateTime endDate;
	 BigDecimal price;
	
	public static PriceDto of(Price price) {
		return PriceDto.builder()
				.productId(price.getProductId())
				.brandId(price.getBrandId())
				.priceList(price.getPriceList())
				.startDate(price.getStartDate())
				.endDate(price.getEndDate())
				.price(price.getPrice())
				.build();
	}
}
