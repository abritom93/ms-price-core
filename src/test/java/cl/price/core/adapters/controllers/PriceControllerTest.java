package cl.price.core.adapters.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.time.LocalDateTime;
import cl.price.core.config.exceptions.GenericException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import cl.price.core.application.port.in.GetPriceQuery;
import cl.price.core.config.exceptions.ResourceNotFoundException;
import cl.price.core.faker.PriceFaker;

@WebMvcTest(controllers = PriceController.class)
public class PriceControllerTest {

	private static final String URL_GET_PRICE= "/api/v1.0/price";
	
	@MockBean
	private GetPriceQuery getProductPriceQuery;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	 
	 @Test
	 @DisplayName("When PriceControllerTest is called queryPrice should return a response ok with a product")
	 void getPriceProductSuccess() throws Exception{

	        when(getProductPriceQuery.execute( any(Long.class), any(LocalDateTime.class),any(Long.class) ) ).thenReturn(PriceFaker.createPriceProduct());	       
	       
	        this.mockMvc.perform(
	        					get(URL_GET_PRICE).params(PriceFaker.createParamsQueryPriceProduct())
	                      )
	                .andDo(print())
	                .andExpect(status().isOk())
	                .andExpect(content().json(objectMapper.writeValueAsString(PriceFaker.createPriceProductDto())));
	    }

	@Test
	@DisplayName("When PriceControllerTest is called queryPrice should return a bad request for missing header")
	void getPriceProductBadRequestParamsMissing() throws Exception{

		this.mockMvc.perform(
						get(URL_GET_PRICE).params(PriceFaker.createParamsQueryPriceProductMissingParam())
				)
				.andDo(print())
				.andExpect(status().is4xxClientError());
	}

	@Test
	@DisplayName("When PriceControllerTest is called queryPrice should return a bad request for missing header")
	void getPriceProductBadRequestArgumentTypeMismatch() throws Exception{

		this.mockMvc.perform(
						get(URL_GET_PRICE).params(PriceFaker.createParamsQueryPriceProductArgumentTypeMismatch())
				)
				.andDo(print())
				.andExpect(status().is4xxClientError());
	}

	@Test
	@DisplayName("When PriceControllerTest is called queryPrice should return a not found exception")
	void getPriceProductNotFound() throws Exception{
		when(getProductPriceQuery.execute( any(Long.class), any(LocalDateTime.class),any(Long.class) ) ).thenThrow(ResourceNotFoundException.class);

		this.mockMvc.perform(
						get(URL_GET_PRICE).params(PriceFaker.createParamsQueryPriceProduct())
				)
				.andDo(print())
				.andExpect(status().isNotFound());
	}

	@Test
	@DisplayName("When PriceControllerTest is called queryPrice should return a not found exception")
	void getPriceProductSqlException() throws Exception{
		when(getProductPriceQuery.execute( any(Long.class), any(LocalDateTime.class),any(Long.class) ) ).thenThrow(GenericException.class);

		this.mockMvc.perform(
						get(URL_GET_PRICE).params(PriceFaker.createParamsQueryPriceProduct())
				)
				.andDo(print())
				.andExpect(status().isInternalServerError());
	}
}
