package com.camilo.usermicroservice.service;

import com.camilo.usermicroservice.entity.User;
import com.camilo.usermicroservice.feignclients.BikeFeignClient;
import com.camilo.usermicroservice.feignclients.CarFeignClient;
import com.camilo.usermicroservice.model.Bike;
import com.camilo.usermicroservice.model.Car;
import com.camilo.usermicroservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    CarFeignClient carFeignClient;

    @Autowired
    BikeFeignClient bikeFeignClient;

    public List<User> getAll(){
        return userRepository.findAll();
    }

    public User getUserById(int id){
        return userRepository.findById(id).orElse(null);
    }

    public User save(User user){
        User newUser = userRepository.save(user);
        return newUser;
    }

    public List<Car> getCars(int userId){
        //List<Car> cars = restTemplate.getForObject("http://localhost:8003/car/byuser/" + userId, List.class);
        ResponseEntity<List<Car>> response = carFeignClient.getCar(userId);
        if (response.getStatusCode()== HttpStatus.OK)
            return response.getBody();
        return Collections.emptyList();
    }

    public List<Bike> getBikes(int userId){
        List<Bike> bikes = restTemplate.getForObject("http://localhost:8004/bike/byuser/" + userId, List.class);
        return bikes;
    }

    public Car saveCar(int userId, Car car){
        car.setUserId(userId);
        Car carNew = carFeignClient.save(car);
        return carNew;
    }

    public Bike saveBike(int userId, Bike bike){
        bike.setUserId(userId);
        Bike bikeNew = bikeFeignClient.save(bike);
        return bikeNew;
    }

    public Map<String, Object> getUserAndVehicles(int userId){
        Map<String, Object> result = new HashMap<>();
        User user = userRepository.findById(userId).orElse(null);
        if(user != null){
            List<Car> cars = this.getCars(userId);
            if(cars.isEmpty())
                result.put("Cars", "Este usuario no tiene coches");
            else
                result.put("Cars", cars);
            List<Bike> bikes = this.getBikes(userId);
            if(cars.isEmpty()) {
                result.put("Bikes", "Este usuario no tiene motos");
            }
            else {
                result.put("Bikes", bikes);
            }
            result.put("User", user);
        }else{
            result.put("Mensaje", "No existe el usuario");
            return result;
        }

        return result;
    }

}
