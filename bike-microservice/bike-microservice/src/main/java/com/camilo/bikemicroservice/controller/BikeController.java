package com.camilo.bikemicroservice.controller;

import com.camilo.bikemicroservice.entity.Bike;
import com.camilo.bikemicroservice.service.BikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bike")
public class BikeController {

    @Autowired
    BikeService bikeService;

    @GetMapping
    public ResponseEntity<List<Bike>> getAll(){
        List<Bike> bikes= bikeService.getAll();
        if (bikes.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(bikes);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Bike> getCar(@PathVariable("id") int id){
        Bike bike = bikeService.getBikeById(id);
        if (bike ==null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(bike);

    }

    @GetMapping("/byuser/{userId}")
    public ResponseEntity<List<Bike>> getByUser(@PathVariable("userId") int userId){
        List<Bike> bikes = bikeService.getByUserId(userId);
        if (bikes.isEmpty())
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(bikes);

    }

    @PostMapping
    public ResponseEntity<Bike> save(@RequestBody Bike bike){
        Bike bikeNew = bikeService.save(bike);
        return ResponseEntity.ok(bikeNew);

    }
}
