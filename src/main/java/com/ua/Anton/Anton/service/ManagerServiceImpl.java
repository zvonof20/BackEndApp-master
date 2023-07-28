package com.ua.Anton.Anton.service;

import com.ua.Anton.Anton.model.Manager;
import com.ua.Anton.Anton.repository.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ManagerServiceImpl implements ManagerService {

    private final ManagerRepository managerRepository;

    @Autowired
    public ManagerServiceImpl(ManagerRepository managerRepository) {
        this.managerRepository = managerRepository;
    }

    @Override
    public List<Manager> getAllManagers() {
        return managerRepository.findAll();
    }

    @Override
    public Manager getManagerById(Long id) {
        Optional<Manager> carOptional = managerRepository.findById(id);
        if (carOptional.isEmpty()) {
            throw new IllegalStateException("Manager with id " + id + " does not exists");
        }
        return carOptional.get();
    }

    @Override
    public Manager addNewManager(Manager manager) {
        Optional<Manager> carOptional = managerRepository.getManagerByPassportId(manager.getPassportId());
        if (carOptional.isPresent()) {
            throw new IllegalStateException("Manager with passport id " + manager.getPassportId() + " exists");
        }
        return managerRepository.save(manager);
    }

    @Override
    public Manager updateManager(Manager manager) {
        if (manager.getId() != null && managerRepository.existsById(manager.getId())) {
            return managerRepository.save(manager);
        } else {
            throw new IllegalStateException("Manager with id " + manager.getId() + " does not exists");
        }
    }

    @Override
    public void deleteManagerById(Long id) {
        boolean exists = managerRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("Manager with id " + id + " does not exists");
        }
        managerRepository.deleteById(id);
    }
}
