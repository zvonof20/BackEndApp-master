package com.ua.Anton.Anton.repository;

import com.ua.Anton.Anton.model.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ManagerRepository extends JpaRepository<Manager, Long> {
    Optional<Manager> getManagerByPassportId(String passportId);
}
