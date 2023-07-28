package com.ua.Anton.Anton;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ua.Anton.Anton.controller.CarController;
import com.ua.Anton.Anton.model.Car;
import com.ua.Anton.Anton.service.CarService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CarController.class)
@AutoConfigureMockMvc
public class CarControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CarService carService;

    private List<Car> carList;

    @BeforeEach
    public void setUp() {
        // Prepare test data for CarController
        carList = List.of(
                new Car(1L, "Toyota", "Camry", "Petrol", 10),
                new Car(2L, "Honda", "Civic", "Diesel", 5)
        );
    }

    @Test
    public void testGetAllCars() throws Exception {
        when(carService.getAllCars()).thenReturn(carList);

        mockMvc.perform(get("/car/all"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(carList)));
    }

    @Test
    public void testGetCarById() throws Exception {
        Long carId = 1L;
        Car car = new Car(carId, "Toyota", "Camry", "Petrol", 10);

        when(carService.getCarById(carId)).thenReturn(car);

        mockMvc.perform(get("/car/{id}", carId))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(car)));
    }

    @Test
    public void testCreateCar() throws Exception {
        Car newCar = new Car(null, "Ford", "Focus", "Petrol", 15);
        Car savedCar = new Car(3L, "Ford", "Focus", "Petrol", 15);

        when(carService.addNewCar(newCar)).thenReturn(savedCar);

        mockMvc.perform(put("/car/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newCar)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(savedCar)));
    }

    @Test
    public void testUpdateCar() throws Exception {
        Long carId = 1L;
        Car existingCar = new Car(carId, "Toyota", "Camry", "Petrol", 10);
        Car updatedCar = new Car(carId, "Toyota", "Camry", "Diesel", 12);

        when(carService.updateCar(updatedCar)).thenReturn(updatedCar);

        mockMvc.perform(post("/car/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedCar)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(updatedCar)));
    }

    @Test
    public void testDeleteCarById() throws Exception {
        Long carId = 1L;

        mockMvc.perform(delete("/car/{id}", carId))
                .andExpect(status().isOk());

        verify(carService, times(1)).deleteCarById(carId);
    }
}
