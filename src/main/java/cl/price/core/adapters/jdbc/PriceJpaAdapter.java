package cl.price.core.adapters.jdbc;

import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.stereotype.Component;
import cl.price.core.adapters.jdbc.entities.PriceEntity;
import cl.price.core.adapters.jdbc.repositories.PriceJpaRepository;
import cl.price.core.application.port.out.PriceRepository;
import cl.price.core.config.exceptions.GenericException;
import cl.price.core.domain.Price;

@Component
public class PriceJpaAdapter implements PriceRepository{	

	private final PriceJpaRepository priceJpaRepository;

	public PriceJpaAdapter(PriceJpaRepository priceJpaRepository) {
		this.priceJpaRepository = priceJpaRepository;
	}

	@Override
	public Optional<Price> findPriceByProductIdAndBrandIdAndApplicationDate(LocalDateTime applicationDate, Long productId, Long brandId) {
		
		try {		
			return priceJpaRepository.findAllByBrandIdAndProductIdAndApplicationDate(applicationDate, productId, brandId).map(PriceEntity::toDomain);
		}
		catch(Exception error) {
			throw new GenericException(error.getMessage());
		}				
	}	

}
