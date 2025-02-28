package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Car;
import java.util.List;
public interface CarService extends CarCreationService,CarRetrievalService,CarUpdateService,CarDeleteService {
}
    interface CarCreationService {
        Car create(Car car);
    }
    interface CarRetrievalService {
        List<Car> findAll();
        Car findById(String carId);
    }
    interface CarUpdateService {
        void update(String carId, Car car);

    }
    interface CarDeleteService {
        void delete(String carId);
    }
