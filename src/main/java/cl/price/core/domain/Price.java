package cl.price.core.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Price{	
	
	Long id;
	
    Long brandId;
	
    LocalDateTime startDate;
	
	LocalDateTime endDate;
	
	Long priceList;
	
	Long productId;
	
	Integer priority;
	
	BigDecimal price;
	
	String currency;
	
}
