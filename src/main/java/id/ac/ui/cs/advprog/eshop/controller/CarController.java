package id.ac.ui.cs.advprog.eshop.controller;
import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.CarService;
import id.ac.ui.cs.advprog.eshop.service.CarServiceImpl;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/car")
public class CarController {
    @Autowired
    private CarService carservice;


    @GetMapping("/create")
    public String createCarPage(Model model) {
        model.addAttribute("car", new Car());
        return "createCar";
    }

    @PostMapping("/create")
    public String createCarPost(@ModelAttribute Car car) {
        carservice.create(car);
        return "redirect:/car/listCar";
    }

    @GetMapping("/listCar")
    public String carListPage(Model model) {
        model.addAttribute("cars", carservice.findAll());
        return "carList";
    }

    @GetMapping("/edit/{carId}")
    public String editCarPage(@PathVariable String carId, Model model) {
        model.addAttribute("car", carservice.findById(carId));
        return "editCar";
    }

    @PostMapping("/edit")
    public String editCarPost(@ModelAttribute Car car) {
        carservice.update(car.getCarId(), car);
        return "redirect:/car/listCar";
    }

    @PostMapping("/delete")
    public String deleteCar(@RequestParam("carId") String carId) {
        carservice.deleteCarById(carId);
        return "redirect:/car/listCar";
    }
}