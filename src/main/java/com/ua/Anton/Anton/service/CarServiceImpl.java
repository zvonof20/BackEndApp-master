package com.ua.Anton.Anton.service;

import com.ua.Anton.Anton.model.Car;
import com.ua.Anton.Anton.model.Manager;
import com.ua.Anton.Anton.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;

    @Autowired
    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    @Override
    public Car getCarById(Long id) {
        Optional<Car> carOptional = carRepository.findById(id);
        if (carOptional.isEmpty()) {
            throw new IllegalStateException("Car with id " + id + " does not exists");
        }
        return carOptional.get();
    }

    @Override
    public Car addNewCar(Car car) throws IllegalStateException {
        Optional<Car> carOptional = carRepository.findCarByModel(car.getModel());
        if (carOptional.isPresent()) {
            throw new IllegalStateException("Model exists");
        }
        return carRepository.save(car);
    }

    @Override
    public Car updateCar(Car car) {
        if (car.getId() != null && carRepository.existsById(car.getId())) {
            return carRepository.save(car);
        } else {
            throw new IllegalStateException("Car with id " + car.getId() + " does not exists");
        }
    }

    @Override
    public void deleteCarById(Long id) {
        boolean exists = carRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("Car with id " + id + " does not exists");
        }
        carRepository.deleteById(id);
    }
}
