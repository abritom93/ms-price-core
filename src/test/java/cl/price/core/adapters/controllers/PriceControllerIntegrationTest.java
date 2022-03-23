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
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import com.fasterxml.jackson.databind.ObjectMapper;
import cl.price.core.adapters.controllers.dto.PriceDto;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,classes = Application.class)
@AutoConfigureMockMvc
public class PriceControllerIntegrationTest {

	private static final String URL_GET_PRICE= "/price";
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	 
	 @Test
	 @DisplayName("Test 1: peticion a las 10:00 del dia 14 del producto 35455 para la brand 1 (ZARA)")
	 void getPriceProductSuccess() throws Exception{
		 
		 MultiValueMap<String, String> params1= new LinkedMultiValueMap<>();
		 params1.add("productId", "35455");
		 params1.add("brandId", "1");
		 params1.add("applicationDate", "2020-06-14 10:00:00");
		 
		 PriceDto priceDto1= PriceDto.builder()
				 .applicationDate(PriceFaker.createLocalDateTimeMock("2020-06-14 10:00:00"))
				 .brandId(1L)
				 .productId(35455L)
				 .price(new BigDecimal("35.50"))
				 .priceList(1L)
				 .build();
		 
	        this.mockMvc.perform(
	        					get(URL_GET_PRICE).params(params1)
	                      )
	                .andDo(print())
	                .andExpect(status().isOk())
	                .andExpect(content().json(objectMapper.writeValueAsString(priceDto1)));
	    }

	@Test
	@DisplayName("Test 2: peticion a las 16:00 del dia 14 del producto 35455 para la brand 1 (ZARA)")
	void getPriceProductSuccess2() throws Exception{

		MultiValueMap<String, String> params2= new LinkedMultiValueMap<>();
		params2.add("productId", "35455");
		params2.add("brandId", "1");
		params2.add("applicationDate", "2020-06-14 16:00:00");

		PriceDto priceDto1= PriceDto.builder()
				.applicationDate(PriceFaker.createLocalDateTimeMock("2020-06-14 16:00:00"))
				.brandId(1L)
				.productId(35455L)
				.price(new BigDecimal("25.45"))
				.priceList(2L)
				.build();

		this.mockMvc.perform(
						get(URL_GET_PRICE).params(params2)
				)
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().json(objectMapper.writeValueAsString(priceDto1)));
	}

	@Test
	@DisplayName("Test 3: peticion a las 21:00 del dia 14 del producto 35455 para la brand 1 (ZARA)")
	void getPriceProductSuccess3() throws Exception{

		MultiValueMap<String, String> params3= new LinkedMultiValueMap<>();
		params3.add("productId", "35455");
		params3.add("brandId", "1");
		params3.add("applicationDate", "2020-06-14 21:00:00");

		PriceDto priceDto1= PriceDto.builder()
				.applicationDate(PriceFaker.createLocalDateTimeMock("2020-06-14 21:00:00"))
				.brandId(1L)
				.productId(35455L)
				.price(new BigDecimal("35.50"))
				.priceList(1L)
				.build();

		this.mockMvc.perform(
						get(URL_GET_PRICE).params(params3)
				)
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().json(objectMapper.writeValueAsString(priceDto1)));
	}

	@Test
	@DisplayName("Test 4: peticion a las 10:00 del dia 15 del producto 35455 para la brand 1 (ZARA)")
	void getPriceProductSuccess4() throws Exception{

		MultiValueMap<String, String> params4= new LinkedMultiValueMap<>();
		params4.add("productId", "35455");
		params4.add("brandId", "1");
		params4.add("applicationDate", "2020-06-15 10:00:00");

		PriceDto priceDto1= PriceDto.builder()
				.applicationDate(PriceFaker.createLocalDateTimeMock("2020-06-15 10:00:00"))
				.brandId(1L)
				.productId(35455L)
				.price(new BigDecimal("30.50"))
				.priceList(3L)
				.build();

		this.mockMvc.perform(
						get(URL_GET_PRICE).params(params4)
				)
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().json(objectMapper.writeValueAsString(priceDto1)));
	}

	@Test
	@DisplayName("Test 5: peticion a las 21:00 del dia 16 del producto 35455 para la brand 1 (ZARA)")
	void getPriceProductSuccess5() throws Exception{

		MultiValueMap<String, String> params5= new LinkedMultiValueMap<>();
		params5.add("productId", "35455");
		params5.add("brandId", "1");
		params5.add("applicationDate", "2020-06-16 21:00:00");

		PriceDto priceDto1= PriceDto.builder()
				.applicationDate(PriceFaker.createLocalDateTimeMock("2020-06-16 21:00:00"))
				.brandId(1L)
				.productId(35455L)
				.price(new BigDecimal("38.95"))
				.priceList(4L)
				.build();

		this.mockMvc.perform(
						get(URL_GET_PRICE).params(params5)
				)
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().json(objectMapper.writeValueAsString(priceDto1)));
	}
	
}
