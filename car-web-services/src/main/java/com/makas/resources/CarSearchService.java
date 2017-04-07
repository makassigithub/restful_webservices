package com.makas.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.makas.model.Car;
import com.makas.model.CarSearchObject;
import com.makas.resources.repository.CarRepositoryInterface;
import com.makas.resources.repository.carRepository;


@Path("find/cars")
public class CarSearchService {
	
 CarRepositoryInterface  cri = new carRepository();	 
 @GET
 @Consumes(MediaType.APPLICATION_JSON)
 @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	 public Response searchCarbyPam (@QueryParam(value = "owner") List <String> owners,
			                         @QueryParam(value = "ageFrom") int car_age_from,
	                                 @QueryParam(value = "ageTo") int car_age_to){
	 System.out.println(owners);
       List<Car> cars  =  cri.searchCarByOwner(owners,car_age_from,car_age_to);
        if(cars==null || cars.size()<=0){
        	return Response.status(Status.NOT_FOUND).build();
        }
        
		return Response.ok().entity(new GenericEntity<List<Car>>(cars){}).build();
	}
 
 @POST
 @Consumes(MediaType.APPLICATION_JSON)
 @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	 public Response searchCarbyObject (CarSearchObject searchObj){
	 System.out.println(searchObj);
	 
      List<Car> cars  =  cri.searchUsingObject(searchObj);
        if(cars==null || cars.size()<=0){
        	return Response.status(Status.NOT_FOUND).build();
        }
        
		return Response.ok().entity(new GenericEntity<List<Car>>(cars){}).build();
	}
 
   
}
