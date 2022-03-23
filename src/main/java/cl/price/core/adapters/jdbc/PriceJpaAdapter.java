package cl.price.core.adapters.jdbc;

import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import cl.price.core.adapters.jdbc.entities.PriceEntity;
import cl.price.core.adapters.jdbc.repositories.PriceJpaRepository;
import cl.price.core.application.port.out.PriceRepository;
import cl.price.core.config.exceptions.GenericException;
import cl.price.core.config.exceptions.ResourceNotFoundException;
import cl.price.core.domain.Price;

@Component
public class PriceJpaAdapter implements PriceRepository{

	private static final String ERROR_PRICE_NOT_FOUND="Not exist price for product with id %d, brand id %d and application date %s";

	private final PriceJpaRepository priceJpaRepository;

	@Autowired
	public PriceJpaAdapter(PriceJpaRepository priceJpaRepository) {
		this.priceJpaRepository = priceJpaRepository;
	}

	@Override
	public Price findPriceByProductIdAndBrandIdAndApplicationDate(LocalDateTime applicationDate, Long productId, Long brandId) {
		
		try {
		
			return priceJpaRepository.findAllByBrandIdAndProductIdAndApplicationDate(applicationDate, productId, brandId)
					.map(PriceEntity::toDomain)
					.orElseThrow(()-> new ResourceNotFoundException(String.format(ERROR_PRICE_NOT_FOUND, productId,brandId,applicationDate)))	;
		}
		catch(ResourceNotFoundException error) {
			throw error;
		}
		catch(Exception error) {
			throw new GenericException(error.getMessage());
		}
				
	}	

}
