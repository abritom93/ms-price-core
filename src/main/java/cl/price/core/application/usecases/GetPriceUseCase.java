package cl.price.core.application.usecases;

import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.stereotype.Service;
import cl.price.core.application.port.in.GetPriceQuery;
import cl.price.core.application.port.out.PriceRepository;
import cl.price.core.config.exceptions.ResourceNotFoundException;
import cl.price.core.domain.Price;

@Service
public class GetPriceUseCase implements GetPriceQuery {

	private static final String ERROR_PRICE_NOT_FOUND="Not exist price for product with id %d, brand id %d and application date %s";	
	private final PriceRepository priceRepository;

	public GetPriceUseCase(PriceRepository priceRepository) {
		this.priceRepository = priceRepository;
	}

	@Override
	public Price execute(Long productId, LocalDateTime applicationDate, Long brandId) {
		Optional<Price> price= priceRepository.findPriceByProductIdAndBrandIdAndApplicationDate(applicationDate,productId, brandId);
				
		return	price.orElseThrow(()-> new ResourceNotFoundException(String.format(ERROR_PRICE_NOT_FOUND, productId,brandId,applicationDate)));
	}	
	
}
