package com.ua.Anton.Anton.service;

import com.ua.Anton.Anton.model.Car;

import java.util.List;

public interface CarService {
    List<Car> getAllCars();

    Car getCarById(Long id);

    Car addNewCar(Car car);

    Car updateCar(Car car);

    void deleteCarById(Long id);
}
