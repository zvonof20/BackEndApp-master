package com.ua.Anton.Anton.controller;

import com.ua.Anton.Anton.model.Sale;
import com.ua.Anton.Anton.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sale")
public class SaleController {

    private final SaleService saleService;

    @Autowired
    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    @GetMapping("/all")
    public List<Sale> getAllSales() {
        return saleService.getAllSales();
    }

    @GetMapping("/{id}")
    public Sale getSaleById(@PathVariable("id") Long id) {
        return saleService.getSaleById(id);
    }

    @PutMapping("/new")
    public Sale createNewSale(@RequestParam("carId") Long carId,
                              @RequestParam("managerId") Long managerId,
                              @RequestParam("quantity") int quantity) {
        return saleService.addSale(carId, managerId, quantity);
    }


    @PutMapping("/update/{id}")
    public Sale updateSale(@PathVariable("id") Long id,
                           @RequestParam("carId") Long carId,
                           @RequestParam("managerId") Long managerId,
                           @RequestParam("quantity") int quantity) {
        return saleService.updateSale(id, carId, managerId, quantity);
    }

    @DeleteMapping("/{id}")
    public void deleteSaleById(@PathVariable("id") Long id) {
        saleService.deleteSaleById(id);
    }
}
