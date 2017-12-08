package com.jla;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Matchers.isNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.jla.controllers.ProductController;
import com.jla.model.Product;
import com.jla.services.interfaces.ProductService;



@RunWith(SpringRunner.class)
@WebMvcTest(value = ProductController.class, secure = false)
public class ProductTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ProductService productService;

	String exampleProductJson = "{\"id\":1,\"code\":\"1\",\"name\":\"Product 1\",\"mark\":\"ACME\",\"uom\":\"Unidad\",\"main_supplier\":\"ACME\"}";

	
	@Test
	public void retrieveAnyProduct() throws Exception {

		Product mockProduct = new Product(1,"1","Product 1","ACME","ACME","Unidad");
		Mockito.when(
				productService.findById(Mockito.anyLong())).thenReturn(mockProduct);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/api/products/1").accept(
				MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println("RESULT: "); 
		System.out.println(result.getResponse());
		String expected = "{\"id\":1,\"code\":\"1\",\"name\":\"Product 1\",\"mark\":\"ACME\",\"uom\":\"Unidad\",\"main_supplier\":\"ACME\"}";
		System.out.println("EXPECTED: ");
		System.out.println(expected);
		// {"id":"Course1","name":"Spring","description":"10 Steps, 25 Examples and 10K Students","steps":["Learn Maven","Import Project","First Example","Second Example"]}
		
		JSONAssert.assertEquals(expected, result.getResponse()
				.getContentAsString(), false);
	}

	@Test
	public void createProduct() throws Exception {
		Product mockProduct = new Product(1,"1","Product 1","ACME","ACME","Unidad");
		

		Mockito.when(
				productService.create(Mockito.any(Product.class))).thenReturn(mockProduct);

		// Send course as body to /students/Student1/courses
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/api/products")
				.accept(MediaType.APPLICATION_JSON).content(exampleProductJson)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.OK.value(), response.getStatus());

	}
	
	@Test
    public void findById_ShouldReturnEmptyBody() throws Exception {
		
		Product mockProduct =null;
        when(productService.findById(1L)).thenReturn(mockProduct);
 
        MvcResult result = mockMvc.perform(get("/api/products/{id}", 1L))
                .andExpect(status().isOk()).andReturn();
 
		assertEquals("", result.getResponse().getContentAsString());
    	
        verify(productService, times(1)).findById(1L);
        verifyNoMoreInteractions(productService);
    }
	@Test
    public void findAllProducts() throws Exception {
		Product p1 = new Product(1,"1","Product 1","ACME","ACME","Unidad");
		Product p2 = new Product(2,"2","Product 2","ACME","ACME","Unidad");
		
 
        Mockito.when(productService.findAll()).thenReturn(Arrays.asList(p1, p2));
 
        mockMvc.perform(get("/api/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("Product 1")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].name", is("Product 2")));
                
 
        verify(productService, times(1)).findAll();
        verifyNoMoreInteractions(productService);
    }
	
	@Test
    public void createdProductShouldReturnCreatedProduct() throws Exception {
		Product p1 = new Product(1,"1","Product 2","ACME","ACME","Unidad");
	
 
        when(productService.create(Mockito.any(Product.class))).thenReturn(p1);
 
        RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/api/products")
				.accept(MediaType.APPLICATION_JSON).content(exampleProductJson)
				.contentType(MediaType.APPLICATION_JSON);
				
        
         mockMvc.perform(requestBuilder).andExpect(status().isOk())
                 .andExpect(jsonPath("$.id", is(1)));
         
               

    }
}