package com.makas.resources.repository;

import java.util.List;

import javax.ws.rs.core.MultivaluedMap;

import com.makas.model.Car;
import com.makas.model.CarSearchObject;

public interface CarRepositoryInterface {
	//retrieving all object from the repository
	List<Car> getCars();

	// retrieving an element by id
	Car getSingleCar(String carId);

	Car createCar();

	Car updateCar(Car car);

	void deleteCar(String carId);

	List<Car> searchCarByOwner(List<String> owners, int car_age_from, int car_age_to);

	List<Car> searchUsingObject(CarSearchObject searchObj);

	Car createCarFromUrlEncoded(MultivaluedMap<String, String> carParams);

	Car createFromJson(Car car);

	
	
}