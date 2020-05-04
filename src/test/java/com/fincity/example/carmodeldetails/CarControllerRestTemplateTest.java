package com.fincity.example.carmodeldetails;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fincity.example.dao.CarDao;
import com.fincity.example.dto.CarDto;
import com.fincity.example.model.CarEntity;
import com.google.gson.Gson;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class CarControllerRestTemplateTest {

    private static final ObjectMapper om = new ObjectMapper();

    //@WithMockUser is not working with TestRestTemplate
    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private CarDao mockRepository;
    
    CarDto dto;
    CarEntity entity;

    @Before
    public void init() {
        entity = new CarEntity(1L, "BMW", "BMW Org Pvt Ltd", "SD100", "2012", "RED",new Date(), new Date());
        dto = new CarDto(1L, "BMW", "BMW Org Pvt Ltd", "SD100", "2012", "RED");
        when(mockRepository.findById(1L)).thenReturn(Optional.of(entity));
        when(mockRepository.save(entity)).thenReturn(entity);
    }
    
	@Test
    public void testGetAllCarDeatils() throws JSONException {
    	when(mockRepository.findAll()).thenReturn(Collections.emptyList());
    	ResponseEntity<List> response = restTemplate
                .withBasicAuth("user@gmail.com", "password")
                .getForEntity("/car", List.class);
    	assertEquals(MediaType.APPLICATION_JSON_UTF8, response.getHeaders().getContentType());
        assertEquals(HttpStatus.OK, response.getStatusCode());

        assertTrue(response.getBody().isEmpty());
    }
    
    @Test
    public void testGetCarDetailsById_nologin_401() throws JSONException {
    	ResponseEntity<CarDto> response = restTemplate
                .getForEntity("/car/1", CarDto.class);
    	
    	printJSON(response);
    	
    	 printJSON(response);
         assertEquals(MediaType.APPLICATION_JSON_UTF8, response.getHeaders().getContentType());
         assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
         
    }
    
    @Test
    public void testGetCarDetailsById_Entity_Not_Found_404() throws JSONException {
    	ResponseEntity<CarDto> response = restTemplate
                .withBasicAuth("user@gmail.com", "password")
                .getForEntity("/car/3", CarDto.class);
    	
    	
    	 printJSON(response);
         assertEquals(MediaType.APPLICATION_JSON_UTF8, response.getHeaders().getContentType());
         assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
         
    }
    
    @Test
    public void testGetCarDetailsById() throws JSONException {
    	String expected = "{id:1,name:\"BMW\",manufactureName:\"BMW Org Pvt Ltd\",model:\"SD100\",manufacturingYear:\"2012\",color:\"RED\"}";
    	ResponseEntity<CarDto> response = restTemplate
                .withBasicAuth("user@gmail.com", "password")
                .getForEntity("/car/1", CarDto.class);
    	
    	printJSON(response);
    	
    	assertEquals(MediaType.APPLICATION_JSON_UTF8, response.getHeaders().getContentType());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        String json = new Gson().toJson(response.getBody());
        JSONAssert.assertEquals(expected, new JSONObject(json), false);
    }
    
    @Test
    public void testDeleteCarDetailsById() {
    	ResponseEntity<CarDto> response = restTemplate
                .withBasicAuth("user@gmail.com", "password")
                .getForEntity("/car/1", CarDto.class);
    	
    	printJSON(response);
    	
    	assertEquals(MediaType.APPLICATION_JSON_UTF8, response.getHeaders().getContentType());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
    
    @Test
    public void testDeleteCarDetailsById_nologin_401() throws JSONException {
    	ResponseEntity<CarDto> response = restTemplate
                .getForEntity("/car/1", CarDto.class);
    	
    	printJSON(response);
    	
         assertEquals(MediaType.APPLICATION_JSON_UTF8, response.getHeaders().getContentType());
         assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
         
    }
    
    @Test
    public void testDeleteCarDetailsById_Entity_Not_Found_404() throws JSONException {
    	ResponseEntity<CarDto> response = restTemplate
                .withBasicAuth("user@gmail.com", "password")
                .getForEntity("/car/3", CarDto.class);
    	
    	printJSON(response);
    	
    	 printJSON(response);
         assertEquals(MediaType.APPLICATION_JSON_UTF8, response.getHeaders().getContentType());
         assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
         
    }
    
    @Test
    public void testSaveCarDetails() {
    	ResponseEntity<CarDto> response = restTemplate
                .withBasicAuth("admin@gmail.com", "password")
                .postForEntity("/car", dto, CarDto.class);
    	
    	assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }
    
    @Test
    public void testSaveCarDetails_Insufficient_Role() {
    	ResponseEntity<CarDto> response = restTemplate
                .withBasicAuth("user@gmail.com", "password")
                .postForEntity("/car", dto, CarDto.class);
    	
    	assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    }
    
    private static void printJSON(Object object) {
        String result;
        try {
            result = om.writerWithDefaultPrettyPrinter().writeValueAsString(object);
            System.out.println(result);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    
}