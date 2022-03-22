package cl.price.core.adapters.jdbc.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import java.math.BigDecimal;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import cl.price.core.adapters.jdbc.entities.PriceEntity;
import cl.price.core.faker.PriceFaker;

@DataJpaTest
public class PriceJpaRepositoryTest {

	 @Autowired
	 private PriceJpaRepository priceJpaRepository;

	 @Test
	 public void whenFindPriceProductIsOk() {

		    Optional<PriceEntity> priceProduct = priceJpaRepository.findAllByBrandIdAndProductIdAndApplicationDate(PriceFaker.createLocalDateTimeMock("2020-06-17 11:00:00"),35455L,1L);

			if(priceProduct.isPresent()){
				assertEquals(new BigDecimal("38.95"), priceProduct.get().getPrice());
			}
			else{
				fail("Price not found");
			}

	 }
	 
	 @Test
	 public void whenFindPriceProductIsEmpty() {
	        Optional<PriceEntity> priceProduct = priceJpaRepository.findAllByBrandIdAndProductIdAndApplicationDate(PriceFaker.createLocalDateTimeMock("2021-06-17 11:00:00"),35455L,1L);

			assertEquals(priceProduct, Optional.empty());
	 }

}
