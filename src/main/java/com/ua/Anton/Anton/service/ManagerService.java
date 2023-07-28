package com.ua.Anton.Anton.service;

import com.ua.Anton.Anton.model.Car;
import com.ua.Anton.Anton.model.Manager;

import java.util.List;

public interface ManagerService {
    List<Manager> getAllManagers();

    Manager getManagerById(Long id);

    Manager addNewManager(Manager manager);

    Manager updateManager(Manager manager);

    void deleteManagerById(Long id);
}
