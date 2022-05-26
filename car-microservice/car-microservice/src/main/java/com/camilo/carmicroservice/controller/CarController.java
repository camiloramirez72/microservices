package com.camilo.carmicroservice.controller;

import com.camilo.carmicroservice.entity.Car;
import com.camilo.carmicroservice.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/car")
public class CarController {

    @Autowired
    CarService carService;

    @GetMapping
    public ResponseEntity<List<Car>> getAll(){
        List<Car> cars = carService.getAll();
        if (cars.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(cars);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> getCar(@PathVariable("id") int id){
        Car car = carService.getCarById(id);
        if (car ==null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(car);

    }

    @GetMapping("/byuser/{userId}")
    public ResponseEntity<List<Car>> getByUser(@PathVariable("userId") int userId){
        List<Car> cars = carService.getByUserId(userId);
        if (cars.isEmpty())
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(cars);

    }

    @PostMapping
    public ResponseEntity<Car> save(@RequestBody Car car){
        Car carNew = carService.save(car);
        return ResponseEntity.ok(carNew);

    }
}
