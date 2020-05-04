package com.fincity.example.service;

import java.util.List;

import com.fincity.example.dto.CarDto;
import com.fincity.example.dto.CarModelAttributes;


public interface CarService {
	
	List<CarDto> getAllCarDeatils();

	CarDto saveCarDetails(CarDto dto);

	CarDto updateCarDetails(CarDto dto);

	CarDto getCarDetailsById(Long id);
	
	void deleteCarDetailsById(Long id);

	List<CarDto> search(CarModelAttributes dto);

}
