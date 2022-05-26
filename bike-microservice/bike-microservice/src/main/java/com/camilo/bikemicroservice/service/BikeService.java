package com.camilo.bikemicroservice.service;

import com.camilo.bikemicroservice.entity.Bike;
import com.camilo.bikemicroservice.repository.BikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BikeService {

    @Autowired
    BikeRepository bikeRepository;

    public List<Bike> getAll(){
        return bikeRepository.findAll();
    }

    public Bike getBikeById(int id){
        return bikeRepository.findById(id).orElse(null);
    }

    public Bike save(Bike car){
        Bike newCar = bikeRepository.save(car);
        return newCar;
    }

    public List<Bike> getByUserId(int userId){
        return bikeRepository.findByUserId(userId);
    }
}
