package com.makas.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.makas.model.Car;
import com.makas.model.Driver;
import com.makas.resources.repository.CarRepositoryInterface;
import com.makas.resources.repository.carRepository;

@Path("cars")

public class CarService {

	CarRepositoryInterface cri = new carRepository();

	// A a single car from the DB
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path("{car}")
	public Response getCar(@PathParam("car") String car) {

		// Ensure the query String is in the appropriate format
		if (car == null || car.length() < 2) {
			return Response.status(Status.BAD_REQUEST).build();
		}

		Car cr = cri.getSingleCar(car);
		return Response.ok().entity(cr).build();
	}

	// service that helps creating a car from CarClient
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path("car")
	public Response getCarParams(Car car) {
		if (car == null) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		/*
		 * the logics to call dao's api to create the car in the database
		 */
		System.out.println("from OptimizedCarService");
		System.out.println(car.getId());//just to ensure my URL is well posted
		
		Car cr = cri.createCar(); // in case you want cr to be returned for unit test
		System.out.println(cr);
		return Response.ok().entity(car).build();

	}

	// Get all cars from DB
	@GET
	// @Produces(MediaType.APPLICATION_XML)
	// @Produces(MediaType.APPLICATION_JSON)
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<Car> getAllCars() {
		return cri.getCars();// this has a static implementation in CarRepository.
	}

	// get a nested object of a car
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path("{car}/driver")
	public Driver getDriver(@PathParam("car") String car) {
		Car c = cri.getSingleCar(car);
		Driver d = c.getDriver();
		return d;
	}

	// write to a DB through URl encoding
	// Can be tested using postman
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path("car")
	public Car getCarParams(MultivaluedMap<String, String> carParams) {
		int carId = Integer.parseInt(carParams.getFirst("id"));
		String owner = carParams.getFirst("owner");
		int age = Integer.parseInt(carParams.getFirst("age"));
		Car c = new Car(carId,owner,age,null);
		System.out.println(c);  // just uri test.
		
		Car car = cri.createCarFromUrlEncoded(carParams);
 
		return car;

	}

	// write to a DB Json Object binding to tes with postman
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path("writeJsonCar")
	public Car getCarObject(Car car) {
		System.out.println(car.getId());
		System.out.println(car.getOwner());
		System.out.println(car.getAge());
		
		
        Car cr = cri.createFromJson(car);
        return cr; // just uri test.
	}
	 @PUT
	 @Consumes(MediaType.APPLICATION_JSON)
	 @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	 @Path("{car_id}")		 
	 public Response putCar ( Car car){				 
		 if(car==null ){
			 return Response.status(Status.BAD_REQUEST).build();			 
		 }
	    
		  Car cr = cri.updateCar(car);// task for the Dao layer just for illustration
		  System.out.println("id: "+car.getId());
		  System.out.println("age: "+car.getAge());
		  
	    return Response.ok().entity(car).build();
		
	}
	 
	 @DELETE
	 @Consumes(MediaType.APPLICATION_JSON)
	 @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	 @Path("{car_id}")		 
	 public Response deleteCar (@PathParam("car_id") String carId){				 
		 if(carId==null ){
			 return Response.status(Status.BAD_REQUEST).build();			 
		 }
	    
		  cri.deleteCar(carId);// task for the Dao layer just for illustration
		  System.out.println("id of car to be deleted : "+ carId);
		  
	    return Response.ok().build();
		
	}
}
