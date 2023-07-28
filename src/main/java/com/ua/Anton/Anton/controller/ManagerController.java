package com.ua.Anton.Anton.controller;


import com.ua.Anton.Anton.model.Manager;
import com.ua.Anton.Anton.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/manager")
public class ManagerController {

    private final ManagerService managerService;

    @Autowired
    public ManagerController(ManagerService managerService) {
        this.managerService = managerService;
    }

    @GetMapping("/all")
    public List<Manager> getManagerCars() {
        return managerService.getAllManagers();
    }

    @GetMapping("/{id}")
    public Manager getManagerById(@PathVariable("id") Long id) {
        return managerService.getManagerById(id);
    }

    @PutMapping("/create")
    public Manager createManager(@RequestBody Manager manager) {
        return managerService.addNewManager(manager);
    }

    @PostMapping("/update")
    public Manager updateManager(@RequestBody Manager manager) {
        return managerService.updateManager(manager);
    }

    @DeleteMapping("/{id}")
    public void deleteManagerById(@PathVariable("id") Long id) {
        managerService.deleteManagerById(id);
    }
}
