package com.fincity.example.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.fincity.example.model.CarEntity;


public interface CarDao extends JpaRepository<CarEntity, Long>, JpaSpecificationExecutor<CarEntity>{


}
