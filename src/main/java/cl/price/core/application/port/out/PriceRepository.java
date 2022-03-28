package cl.price.core.application.port.out;

import java.time.LocalDateTime;
import java.util.Optional;
import cl.price.core.domain.Price;

public interface PriceRepository {
	
	Optional<Price> findPriceByProductIdAndBrandIdAndApplicationDate(LocalDateTime applicationDate, Long productId, Long brandId);
	
}
