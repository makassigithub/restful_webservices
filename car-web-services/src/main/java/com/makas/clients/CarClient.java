package com.makas.clients;

import java.net.URI;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import com.makas.model.Car;
import com.makas.model.CarSearchObject;

public class CarClient {

	private Client client;
	
	public CarClient(){ 
		client = ClientBuilder.newClient();
	}
	
	public List<Car> getCars(){
	WebTarget target = client.target("http://localhost:8090/car-web-services/webapi/");	
	
	//since jersey cannot implicitly bind the returned type to a List,
	//but jax-rs provides a GeneriType for that.
	List<Car> response = target.path("cars").request().get(new GenericType<List<Car>>(){});
	
	return response;
			}
	
	public Car getCar(String id){
			
		WebTarget target = client.target("http://localhost:8090/car-web-services/webapi/");
		//create a request of type get with target and bind it the Car class
		//The default returned object is an XML as if we used request(MediaType.APPLICATION_XML)instead os request();
		// To get JSON returned, use request(MediaType.APPLICATION_JSON)instead of request();
					Response response = target.path("cars/"+id).request().get(Response.class);
					if(response.getStatus()!= 200){
						throw new RuntimeException(response.getStatus()+": "+"An error occured on the server");
					}
		            return response.readEntity(Car.class);		
		}

	public Car createCar(Car c) {
		WebTarget target = client.target("http://localhost:8090/car-web-services/webapi/");
		System.out.println("Inside CarClient");
		Response response = target.path("cars/car")
				.request(MediaType.APPLICATION_JSON)
				.post(Entity.entity(c, MediaType.APPLICATION_JSON));
		if(response.getStatus()!= 200){
			throw new RuntimeException(response.getStatus()+": "+"erroneous data cannot be persisted");
		}
		return response.readEntity(Car.class);	
	}

	public Car putCar(Car c) {
		WebTarget target = client.target("http://localhost:8090/car-web-services/webapi/");
		Response response = target.path("cars/"+ c.getId())
				.request(MediaType.APPLICATION_JSON)
				.put(Entity.entity(c, MediaType.APPLICATION_JSON));
		if(response.getStatus()!= 200){
			throw new RuntimeException(response.getStatus()+": "+"erroneous data cannot be persisted");
		}	
		return response.readEntity(Car.class);
	}

	public Car deleteCar(String carId) {
		
		WebTarget target = client.target("http://localhost:8090/car-web-services/webapi/");
		Response response = target.path("cars/" + carId)
				.request(MediaType.APPLICATION_JSON)
				.delete();
		if(response.getStatus()!= 200){
			throw new RuntimeException(response.getStatus()+": "+"the operation failed");
		}	
		return response.readEntity(Car.class);
		
	}

	public List<Car> searchCarUsingQueryParams(String param, List<String> searchValues, String param2, int ageFrom, String param3, int ageTo) {
		//Use a Uri(java.net.uri) api to build a uri wuth query String
		
		URI uri = UriBuilder.fromUri("http://localhost:8090/car-web-services/webapi/")
				  .path("find/cars")
				  .queryParam(param, searchValues)
				  .queryParam(param2,ageFrom)
				  .queryParam(param3,ageTo)
				  .build();
		WebTarget target = client.target(uri);
		List<Car> response = target.request(MediaType.APPLICATION_JSON).get(new GenericType<List<Car>>(){});		
		System.out.println(response);		
		return response;
	}

	public List<Car> searchCarUsingObject(CarSearchObject searchObject) {
		URI uri = UriBuilder.fromUri("http://localhost:8090/car-web-services/webapi/")
				  .path("find/cars")				 
				  .build();
		WebTarget target = client.target(uri);
		Response response = target.request(MediaType.APPLICATION_JSON).post(Entity.entity(searchObject, MediaType.APPLICATION_JSON));
		
		if(response.getStatus()!= 200){
			throw new RuntimeException(response.getStatus()+": "+"something went wrong on the serverside");		
		}
		
		return response.readEntity(new GenericType<List<Car>>(){});
		
	}
	
	
}
