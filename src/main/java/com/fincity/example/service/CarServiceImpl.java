package com.fincity.example.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fincity.example.dao.CarDao;
import com.fincity.example.dao.CarSpecification;
import com.fincity.example.dto.CarDto;
import com.fincity.example.dto.CarModelAttributes;
import com.fincity.example.exception.CarEntityNotFoundException;
import com.fincity.example.model.CarEntity;


@Service
public class CarServiceImpl implements CarService {
	
	@Autowired
	private CarDao dao;

	@Override
	public List<CarDto> getAllCarDeatils() {
		List<CarEntity> carModels = dao.findAll();
		List<CarDto> dtos = new ArrayList<CarDto>();
		if (carModels != null) {
			for(CarEntity model : carModels) {
				dtos.add(convertEntityToDto(model));
			}
		}
		return dtos;
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public CarDto saveCarDetails(CarDto dto) {
		dto.setId(null);
		CarEntity entity = converDtoToEntity(dto);
		entity.setCreated(new Date());
		entity.setLastupdated(new Date());
		entity = dao.save(entity);
		return convertEntityToDto(entity);
	}

	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public CarDto updateCarDetails(CarDto dto) {
		Optional<CarEntity> optional = dao.findById(dto.getId());
		if (optional == null ||  !optional.isPresent()) {
			throw new CarEntityNotFoundException("Car Entity Not Found with Id:" + dto.getId());
		}
		CarEntity entity = optional.get();
		entity.setLastupdated(new Date());
		entity = dao.save(entity);
		return convertEntityToDto(entity);
	}
	
	

	private CarDto convertEntityToDto(CarEntity carModel) {
		CarDto dto = new CarDto();
		if(carModel != null)
			BeanUtils.copyProperties(carModel, dto);
		return dto;
	}
	
	private CarEntity converDtoToEntity(CarDto dto) {
		CarEntity entity = new CarEntity();
		if(dto!= null)
			BeanUtils.copyProperties(dto, entity);
		return entity;
	}

	@Override
	public CarDto getCarDetailsById(Long id) {
		Optional<CarEntity> optional = dao.findById(id);
		if (optional == null ||  !optional.isPresent()) {
			throw new CarEntityNotFoundException("Car Entity Not Found with Id:" + id);
		}
		return convertEntityToDto(optional.get());
	}

	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public void deleteCarDetailsById(Long id) {
		Optional<CarEntity> optional = dao.findById(id);
		if (optional == null ||  !optional.isPresent()) {
			throw new CarEntityNotFoundException("Car Entity Not Found with Id:" + id);
		}
		CarEntity entity = optional.get();
		dao.delete(entity);
	}

	@Override
	public List<CarDto> search(CarModelAttributes dto) {
		Specification<CarEntity> carSpecification = CarSpecification.search(dto);
		List<CarEntity> carEntities =  dao.findAll(carSpecification);
		List<CarDto> carDtos = new ArrayList<>();
		if (carEntities != null) {
			for (CarEntity carEntity : carEntities) {
				carDtos.add(convertEntityToDto(carEntity));
			}
		}
		return carDtos;
	}

	

}
