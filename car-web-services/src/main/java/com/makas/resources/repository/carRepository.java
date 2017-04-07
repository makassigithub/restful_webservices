package com.makas.resources.repository;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MultivaluedMap;

import com.makas.model.Car;
import com.makas.model.CarSearchObject;
import com.makas.model.Driver;

public class carRepository implements CarRepositoryInterface {

	
	/* (non-Javadoc)
	 * @see com.makas.resources.repository.CarRepositoryInterface#getCars()
	 */
	@Override
	public List<Car> getCars (){
		
		List<Car> carList = new ArrayList<Car>();
		
		Car c1 = new Car(1,"Brahima",45,null);
		Car c2 = new Car(2,"Claudine",43,null);
		        carList.add(c1);
		        carList.add(c2);
		
		return carList;
	}
	@Override
	public Car getSingleCar(String carId) {
		//return new Car(123,"Hedi",26,null);
		 Car c = new Car();
		     c.setId(123);
		     c.setOwner("Hedi");
		     c.setAge(26);
		 Driver driver = new Driver();
		        driver.setDriverId(1);
		        driver.setName("Millogo");
		        driver.setCountry("Mali");
		     c.setDriver(driver);
		     return c;		
	}
	
	@Override
	public Car createCarFromUrlEncoded(MultivaluedMap<String, String> carParams) {
		// get the properies from the map, create a car and use the PP to persist and return it.
		return null;
	}
	
	@Override
	public Car createFromJson(Car car) {
		// use PP(Persistence Provider) to save carand return it
		return car;
	}
	
	@Override
	public Car createCar() {
		// call the Persistence provider API to create the Car here	
		return null;
	}
	
	@Override
	public Car updateCar(Car car) {
		// search for the car in the database 
		//if found , update it, and return it	
		
		return car;
	}	
	
	@Override
	public void deleteCar(String carId) {
		// call the Persistence provider API to create the Car here	
	
	}
	@Override
	public List<Car> searchCarByOwner(List<String> owners,int start_age,int end_age) {
		// something like SELECT * FROM car WHERE owner in "owners" and age between start_age and end_age
		//We will simulate data here
		
		List <Car> cars = new ArrayList<Car>();
		     Car car1 = new Car();
		        car1.setId(50);
		        car1.setAge(14);
		        car1.setOwner("Zanga");
		        car1.setDriver(null);
		        
		        Car car2 = new Car();
		        car2.setId(70);
		        car2.setAge(147);
		        car2.setOwner("Zulu");
		        car2.setDriver(null);
		    cars.add(car1);
		    cars.add(car2);
		  return cars;
	}
	
	@Override
	public List<Car> searchUsingObject(CarSearchObject searchObj) {
		   System.out.println("Inside repository; searchObject :"+ searchObj);
		   System.out.println("Inside repository searchObject searchType: "+ searchObj.getSearchType());
		   
		      // we will create a Car using some fields of searchObj and send it back.
		   Car cr = new Car();
		            cr.setId(searchObj.getAgeFrom());
		            cr.setAge(searchObj.getAgeTo());
		            cr.setOwner(searchObj.getOwners().get(0));
		         		    
		List <Car> cars = new ArrayList<Car>();
		           cars.add(cr);
	     
	  return cars;
	}
}
