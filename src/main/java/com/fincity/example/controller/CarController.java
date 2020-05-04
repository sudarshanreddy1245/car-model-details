package com.fincity.example.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fincity.example.dto.CarDto;
import com.fincity.example.dto.CarModelAttributes;
import com.fincity.example.service.CarService;

@RestController
public class CarController {
	
	private static final Logger LOGGER=LoggerFactory.getLogger(CarController.class);

	@Autowired
	private CarService service;

	@GetMapping("/car")
	public ResponseEntity<List<CarDto>> getAllCarModelDeatils() {
		List<CarDto> CarDtos = service.getAllCarDeatils();
		return ResponseEntity.ok(CarDtos);
	}

	@GetMapping("/car/{id}")
	public Resource<CarDto> getCarDetailsById(@PathVariable Long id) {
		CarDto CarDto = service.getCarDetailsById(id);
		Resource<CarDto> resource = new Resource<CarDto>(CarDto);
		ControllerLinkBuilder builder = linkTo(methodOn(this.getClass()).getAllCarModelDeatils());
		resource.add(builder.withRel("all-car-details"));
		return resource;
	}

	@PostMapping("/car")
	public ResponseEntity<CarDto> saveCarDetails(@Valid @RequestBody CarDto dto) {
		dto = service.saveCarDetails(dto);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}

	@PutMapping("/car")
	public Resource<CarDto> updateCarDetails(@Valid @RequestBody CarDto dto) {
		dto = service.updateCarDetails(dto);
		Resource<CarDto> resource = new Resource<CarDto>(dto);
		ControllerLinkBuilder builder = linkTo(methodOn(this.getClass()).getAllCarModelDeatils());
		resource.add(builder.withRel("all-car-details"));
		return resource;
	}

	@DeleteMapping("/car/{id}")
	public Resource<String> deleteCarDetailsById(@PathVariable Long id) {
		service.deleteCarDetailsById(id);
		Resource<String> resource = new Resource<String>("Successfully Deleted");
		ControllerLinkBuilder builder = linkTo(methodOn(this.getClass()).getAllCarModelDeatils());
		resource.add(builder.withRel("all-car-details"));
		return resource;
	}

	@GetMapping("/car/search")
	public Resources<List<CarDto>> search(@ModelAttribute CarModelAttributes dto) {
		List<CarDto> carDtos = service.search(dto);
		Resources<List<CarDto>> resource = new Resources(carDtos);
		ControllerLinkBuilder builder = linkTo(methodOn(this.getClass()).getAllCarModelDeatils());
		resource.add(builder.withRel("all-car-details"));
		return resource;
	}

}
