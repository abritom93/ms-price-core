package cl.price.core.adapters.jdbc;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import java.time.LocalDateTime;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import cl.price.core.config.exceptions.GenericException;
import org.hibernate.MappingException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import cl.price.core.adapters.jdbc.repositories.PriceJpaRepository;
import cl.price.core.config.exceptions.ResourceNotFoundException;
import cl.price.core.domain.Price;
import cl.price.core.faker.PriceFaker;

@ExtendWith(MockitoExtension.class)
public class PriceJdbcAdapterTest {

	@Mock
	private PriceJpaRepository priceJpaRepository;
	
	@InjectMocks
	private PriceJpaAdapter priceJdbcAdapter;
	
	@Test
	 void whenFindPriceByProductIdAndBrandIdAndApplicationDateIsOk() {
		when(priceJpaRepository.findAllByBrandIdAndProductIdAndApplicationDate(any(LocalDateTime.class),any(Long.class),any(Long.class))).thenReturn(PriceFaker.createPriceProductEntity());
		
		Price priceCurrent= priceJdbcAdapter.findPriceByProductIdAndBrandIdAndApplicationDate(PriceFaker.createLocalDateTimeMock("2020-06-15 11:00:00"), 35455L, 1L);
		Price priceExpected= PriceFaker.createPriceProduct();
		
		assertEquals(priceExpected, priceCurrent);
	}

	@Test
	 void whenFindPriceByProductIdAndBrandIdAndApplicationDateNotFound() {
		when(priceJpaRepository.findAllByBrandIdAndProductIdAndApplicationDate(PriceFaker.createLocalDateTimeMock("2020-06-15 11:00:00"),35455L,1L)).thenReturn(Optional.empty());

		ResourceNotFoundException priceNotFound= assertThrows(ResourceNotFoundException.class,()->priceJdbcAdapter.findPriceByProductIdAndBrandIdAndApplicationDate(PriceFaker.createLocalDateTimeMock("2020-06-15 11:00:00"), 35455L, 1L)) ;

		String messageErrorNotFoundExpected="Not exist price for product with id 35455, brand id 1 and application date 2020-06-15T11:00";

		assertEquals(messageErrorNotFoundExpected,priceNotFound.getMessage());
	}

	@Test
	void whenFindPriceByProductIdAndBrandIdAndApplicationDateGenericError() {
		when(priceJpaRepository.findAllByBrandIdAndProductIdAndApplicationDate(PriceFaker.createLocalDateTimeMock("2020-06-15 11:00:00"),35455L,1L)).thenThrow(MappingException.class);

		assertThrows(GenericException.class,()->priceJdbcAdapter.findPriceByProductIdAndBrandIdAndApplicationDate(PriceFaker.createLocalDateTimeMock("2020-06-15 11:00:00"), 35455L, 1L)) ;

	}
	
	
}
