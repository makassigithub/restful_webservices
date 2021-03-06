package com.makas.clients_test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.makas.clients.CarClient;
import com.makas.model.Car;
import com.makas.model.CarSearchObject;
import com.makas.model.Driver;
import com.makas.model.SearchType;

public class CarClientTest {

	@Test
	public void testGetCars() {

		CarClient client = new CarClient();
		List<Car> cars = client.getCars();

		assertNotNull(cars);
		assertEquals(cars.size(), 2);
	}

	@Test // This assumes the request is well formed
	public void testGetCar() {
		CarClient client = new CarClient();
		Car car = client.getCar("12"); 
																
		assertNotNull(car);
		assertEquals(car.getId(), 123);
	}

	@Test(expected = RuntimeException.class)
	public void testExceptionThrowing() {
		CarClient client = new CarClient();
		      client.getCar("1");

	}

	@Test
	public void testcreateCar() {
		CarClient client = new CarClient();
		Car c = new Car(); // in real world the id is generated by the server
		c.setAge(143);
		c.setOwner("Claudine");
		c.setDriver(new Driver(45, "Zanga", "Somalia"));

		Car car = client.createCar(c);
		assertNotNull(car);
		assertEquals(car.getAge(), 143);
		assertEquals(car.getDriver().getName(), "Zanga");
	}

	@Test
	public void testPutCar() {
		CarClient client = new CarClient();
		Car c = new Car();
		c.setId(111);
		c.setAge(12);
		c.setDriver(null);
		c.setOwner("Salif");
		Car car = client.putCar(c);

		assertEquals(car.getId(), 111);
	}
	
	@Test
	public void testDeleteCar() {
		CarClient client = new CarClient();
		// most of the time we delete based on the Id
		// Let's assume my id are String this time
		client.deleteCar("99");
	}
	
	@Test
	public void testCarSearch(){
		CarClient client = new CarClient();
		String param = "owner";
		List <String> searchValues = new ArrayList<String>();
		              searchValues.add("Pierre");
		              searchValues.add("Paul");
		String param2 = "ageFrom";
		int ageFrom = 10;
		String param3 = "ageTo";
		int ageTo = 30;
	List<Car> cars = 	client.searchCarUsingQueryParams(param,searchValues,param2,ageFrom,param3,ageTo);
		System.out.println(cars);
		assertNotNull(cars);
		assertEquals(cars.size(),2);
		
	}
	
	@Test
	public void testCarSearchObject(){
		CarClient client = new CarClient();		
		CarSearchObject cso = new CarSearchObject();
		                cso.setAgeFrom(10);
		                cso.setAgeTo(30);
		                cso.setSearchType(SearchType.SEARCH_BY_CAR_AGE);// just to describe the query.
		ArrayList <String> owners = new ArrayList<String>();
		                   owners.add("Komolo");
		                   owners.add("Simplice");
		                 cso.setOwners(owners);
		  List<Car> cars = client.searchCarUsingObject(cso);
	         assertNotNull(cars);
	         assertEquals(cars.size(),1);
	         assertEquals(cars.get(0).getOwner(),"Komolo");
	         
	      
	} 
	
}
