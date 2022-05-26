package com.camilo.usermicroservice.feignclients;

import com.camilo.usermicroservice.model.Car;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "car-microservice", decode404 = true)
public interface CarFeignClient {

    @RequestMapping(method = RequestMethod.POST, value = "/car")
    Car save(@RequestBody Car car);

    @RequestMapping(method = RequestMethod.GET, value = "/car/byuser/{userId}")
    ResponseEntity<List<Car>> getCar(@PathVariable int userId);

}
