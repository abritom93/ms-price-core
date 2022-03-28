package cl.price.core.adapters.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.math.BigDecimal;
import cl.price.core.Application;
import cl.price.core.faker.PriceFaker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.MultiValueMap;
import com.fasterxml.jackson.databind.ObjectMapper;
import cl.price.core.adapters.controllers.dto.PriceDto;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,classes = Application.class)
@AutoConfigureMockMvc
class PriceControllerIntegrationTest {

	private static final String URL_GET_PRICE= "/price";
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	 
	 @Test
	 @DisplayName("Test 1: request to the 10:00 of day 14 given product with id 35455 and id brand 1 (ZARA)")
	 void getPriceProductFirstTest() throws Exception{

		 MultiValueMap<String, String> params=  PriceFaker.createParamsQueryPriceProduct("35455","1","2020-06-14 10:00:00");
		 PriceDto priceDto= PriceFaker.createPriceProductDto(1L,35455L,1L,new BigDecimal("35.50"),"2020-06-14 00:00:00","2020-12-31 23:59:59");

		 this.mockMvc.perform(
	        					get(URL_GET_PRICE).params(params)
	                      )
	                .andDo(print())
	                .andExpect(status().isOk())
	                .andExpect(content().json(objectMapper.writeValueAsString(priceDto)));
	    }

	@Test
	@DisplayName("Test 2: request to the 16:00 of day 14 given product with id 35455 and id brand 1 (ZARA)")
	void getPriceProductSuccessSecondTest() throws Exception{

		MultiValueMap<String, String> params=  PriceFaker.createParamsQueryPriceProduct("35455","1","2020-06-14 16:00:00");
		PriceDto priceDto= PriceFaker.createPriceProductDto(1L,35455L,2L,new BigDecimal("25.45"),"2020-06-14 15:00:00","2020-06-14 18:30:00");

		this.mockMvc.perform(
						get(URL_GET_PRICE).params(params)
				)
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().json(objectMapper.writeValueAsString(priceDto)));
	}

	@Test
	@DisplayName("Test 3: request to the 21:00 of day 14 given product with id 35455 and id brand 1 (ZARA)")
	void getPriceProductSuccessThirdTest() throws Exception{

		MultiValueMap<String, String> params=  PriceFaker.createParamsQueryPriceProduct("35455","1","2020-06-14 21:00:00");

		PriceDto priceDto= PriceFaker.createPriceProductDto(1L,35455L,1L,new BigDecimal("35.50"),"2020-06-14 00:00:00","2020-12-31 23:59:59");

		this.mockMvc.perform(
						get(URL_GET_PRICE).params(params)
				)
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().json(objectMapper.writeValueAsString(priceDto)));
	}

	@Test
	@DisplayName("Test 4: request to the 10:00 of day 15 given product with id 35455 and id brand 1 (ZARA)")
	void getPriceProductSuccessFourthTest() throws Exception{

		MultiValueMap<String, String> params=  PriceFaker.createParamsQueryPriceProduct("35455","1","2020-06-15 10:00:00");
		PriceDto priceDto= PriceFaker.createPriceProductDto(1L,35455L,3L,new BigDecimal("30.50"),"2020-06-15 00:00:00","2020-06-15 11:00:00");

		this.mockMvc.perform(
						get(URL_GET_PRICE).params(params)
				)
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().json(objectMapper.writeValueAsString(priceDto)));
	}

	@Test
	@DisplayName("Test 5: request to the 21:00 of day 16 given product with id 35455 and id brand 1 (ZARA)")
	void getPriceProductSuccessFifthTest() throws Exception{

		MultiValueMap<String, String> params=  PriceFaker.createParamsQueryPriceProduct("35455","1","2020-06-16 21:00:00");
		PriceDto priceDto= PriceFaker.createPriceProductDto(1L,35455L,4L,new BigDecimal("38.95"),"2020-06-15 16:00:00","2020-12-31 23:59:59");

		this.mockMvc.perform(
						get(URL_GET_PRICE).params(params)
				)
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().json(objectMapper.writeValueAsString(priceDto)));
	}
	
	@Test
	@DisplayName("Test not found price: request to the 21:00 of day 16 of year 2021 given product with id 35455 and id brand 1 (ZARA) return error for not found")
	void getPriceProductNotFoundFifthTest() throws Exception{
		MultiValueMap<String, String> params=  PriceFaker.createParamsQueryPriceProduct("35455","1","2021-06-16 21:00:00");
		this.mockMvc.perform(
						get(URL_GET_PRICE).params(params)
				)
				.andDo(print())
				.andExpect(status().isNotFound());
	}
	
	@Test
	@DisplayName("Test missing params: request without params return error for missing params")
	void getPriceProductMissingParamsFifthTest() throws Exception{

		this.mockMvc.perform(
						get(URL_GET_PRICE)
				)
				.andDo(print())
				.andExpect(status().isBadRequest());
	}
	
	@Test
	@DisplayName("Test method argument type mismatch: request to the 21:00 of day 16 given product with id 35455 and id brand test return error for method argument type mismatch")
	void getPriceProductMethodArgumentTypeMismatchFifthTest() throws Exception{
		MultiValueMap<String, String> params=  PriceFaker.createParamsQueryPriceProduct("35455","test","2020-06-16 21:00:00");
		
		this.mockMvc.perform(
						get(URL_GET_PRICE).params(params)
				)
				.andDo(print())
				.andExpect(status().isBadRequest());
	}
	
}
