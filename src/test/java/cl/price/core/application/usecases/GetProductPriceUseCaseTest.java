package cl.price.core.application.usecases;


import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import java.time.LocalDateTime;
import cl.price.core.config.exceptions.GenericException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import cl.price.core.application.port.out.PriceRepository;
import cl.price.core.config.exceptions.ResourceNotFoundException;
import cl.price.core.domain.Price;
import cl.price.core.faker.PriceFaker;

@ExtendWith(MockitoExtension.class)
public class GetProductPriceUseCaseTest {

	@InjectMocks
	private GetPriceUseCase getProductPriceUseCase;
	
	@Mock
	private PriceRepository priceRepository;	

	@Test
	@DisplayName("When GetProductPriceUseCaseTest is called execute should return a response ok with a product")
	public void whenExecuteIsOk() {
		when(priceRepository.findPriceByProductIdAndBrandIdAndApplicationDate( any(LocalDateTime.class), any(Long.class), any(Long.class))).thenReturn(PriceFaker.createPriceProduct());
		
		Price priceCurrent= getProductPriceUseCase.execute(35455L, PriceFaker.createLocalDateTimeMock("2020-06-15 11:00:00"), 1L);
		Price priceExpected= PriceFaker.createPriceProduct();
		
		assertEquals(priceExpected, priceCurrent);
		
	}

	@Test
	@DisplayName("When GetProductPriceUseCaseTest is called execute should return a error not found")
	public void whenExecuteReturnNotFound() {
		when(priceRepository.findPriceByProductIdAndBrandIdAndApplicationDate( any(LocalDateTime.class), any(Long.class), any(Long.class))).thenThrow(new ResourceNotFoundException());
        assertThrows(ResourceNotFoundException.class ,()-> getProductPriceUseCase.execute(35455L, PriceFaker.createLocalDateTimeMock("2020-06-15 11:00:00"), 1L));
	}

	@Test
	@DisplayName("When GetProductPriceUseCaseTest is called execute should return a generic exception")
	public void whenExecuteReturnGenericError() {
		when(priceRepository.findPriceByProductIdAndBrandIdAndApplicationDate( any(LocalDateTime.class), any(Long.class), any(Long.class))).thenThrow(new GenericException());
		assertThrows(GenericException.class ,()-> getProductPriceUseCase.execute(35455L, PriceFaker.createLocalDateTimeMock("2020-06-15 11:00:00"), 1L));
	}
}
