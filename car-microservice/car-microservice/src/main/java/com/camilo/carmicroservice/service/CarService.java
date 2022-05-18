package com.camilo.carmicroservice.service;

import com.camilo.carmicroservice.entity.Car;
import com.camilo.carmicroservice.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {

    @Autowired
    CarRepository carRepository;

    public List<Car> getAll(){
        return carRepository.findAll();
    }

    public Car getCarById(int id){
        return carRepository.findById(id).orElse(null);
    }

    public Car save(Car car){
        Car newCar = carRepository.save(car);
        return newCar;
    }

    public List<Car> getByUserId(int userId){
        return carRepository.findByUserId(userId);
    }
}
