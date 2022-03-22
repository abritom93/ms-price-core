package cl.price.core.application.port.out;

import java.time.LocalDateTime;

import cl.price.core.domain.Price;

public interface PriceRepository {
	
	Price findPriceByProductIdAndBrandIdAndApplicationDate(LocalDateTime applicationDate, Long productId, Long brandId);
	
}
