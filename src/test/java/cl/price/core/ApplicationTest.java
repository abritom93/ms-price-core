package cl.price.core;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import cl.price.core.adapters.controllers.PriceController;
import cl.price.core.adapters.jdbc.PriceJpaAdapter;
import cl.price.core.application.usecases.GetPriceUseCase;

@SpringBootTest
public class ApplicationTest {

	@Autowired
	private PriceController priceController;
	
	@Autowired
	private GetPriceUseCase getProductPriceUseCase;
	
	@Autowired
	private PriceJpaAdapter priceJdbcAdapter;
	
	@Test
	void loadContext(){
		Application.main(new String[] {});
		assertNotNull(priceController);
		assertNotNull(getProductPriceUseCase);
		assertNotNull(priceJdbcAdapter);
	}
}
