package com.camilo.usermicroservice.feignclients;

import com.camilo.usermicroservice.model.Bike;
import com.camilo.usermicroservice.model.Car;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "bike-service", url = "http://localhost:8004", path = "/bike")
public interface BikeFeignClient {

    @PostMapping()
    Bike save(@RequestBody Bike bike);

}
