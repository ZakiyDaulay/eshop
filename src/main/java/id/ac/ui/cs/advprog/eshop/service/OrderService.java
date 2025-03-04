package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@Service
public interface OrderService {
    public Order createOrder(Order order) ;
    public Order updateStatus(String orderId,String status);
    public Order findById(String orderId) ;
    public List<Order> findAllByAuthor(String author);
}

