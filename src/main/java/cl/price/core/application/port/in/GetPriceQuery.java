package cl.price.core.application.port.in;

import java.time.LocalDateTime;

import cl.price.core.domain.Price;

public interface GetPriceQuery {

	Price execute(Long productId, LocalDateTime applicationDate, Long brandId);
}
