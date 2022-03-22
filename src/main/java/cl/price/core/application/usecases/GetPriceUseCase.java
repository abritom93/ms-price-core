package cl.price.core.application.usecases;

import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cl.price.core.application.port.in.GetPriceQuery;
import cl.price.core.application.port.out.PriceRepository;
import cl.price.core.domain.Price;

@Service
public class GetPriceUseCase implements GetPriceQuery {

	private final PriceRepository priceRepository;

	@Autowired
	public GetPriceUseCase(PriceRepository priceRepository) {
		this.priceRepository = priceRepository;
	}

	@Override
	public Price execute(Long productId, LocalDateTime applicationDate, Long brandId) {
		return priceRepository.findPriceByProductIdAndBrandIdAndApplicationDate(applicationDate,productId, brandId);	
	}	
	
}
