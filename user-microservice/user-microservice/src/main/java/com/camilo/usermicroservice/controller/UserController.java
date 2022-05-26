package com.camilo.usermicroservice.controller;

import com.camilo.usermicroservice.entity.User;
import com.camilo.usermicroservice.model.Bike;
import com.camilo.usermicroservice.model.Car;
import com.camilo.usermicroservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.sql.rowset.CachedRowSet;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getAll(){
        List<User> users  = userService.getAll();
        if (users.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(users);

    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") int id){
        User user  = userService.getUserById(id);
        if (user==null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(user);

    }

    @GetMapping("/cars/{userId}")
    public ResponseEntity<List<Car>> getCars(@PathVariable("userId") int userId){
        List<Car> cars = userService.getCars(userId);
        if (cars.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(cars);

    }

    @GetMapping("/bikes/{userId}")
    public ResponseEntity<List<Bike>> getBikes(@PathVariable("userId") int userId){
        List<Bike> bikes = userService.getBikes(userId);
        if (bikes.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(bikes);

    }

    @GetMapping("/getAll/{userId}")
    public ResponseEntity<Map<String, Object>> getAllVehicles(@PathVariable("userId") int userId){
        Map<String, Object> userAndVehicles = userService.getUserAndVehicles(userId);
        if (userAndVehicles.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(userAndVehicles);

    }

    @PostMapping("")
    public ResponseEntity<User> saveUser(@RequestBody User user){
        User userNew = userService.save(user);
        return ResponseEntity.ok(userNew);

    }

    @PostMapping("/savecar/{userId}")
    public ResponseEntity<Car> saveCar(@PathVariable("userId")  int userId, @RequestBody Car car){
        Car carNew  = userService.saveCar(userId, car);
        return ResponseEntity.ok(carNew);

    }
    @PostMapping("/savebike/{userId}")
    public ResponseEntity<Bike> saveCar(@PathVariable("userId")  int userId, @RequestBody Bike bike){
        Bike bikeNew  = userService.saveBike(userId, bike);
        return ResponseEntity.ok(bikeNew);

    }
}
