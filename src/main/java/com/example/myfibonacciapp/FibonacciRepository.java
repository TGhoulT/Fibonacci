package com.example.myfibonacciapp;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface FibonacciRepository extends JpaRepository<FibonacciEntity, Long> {
    Optional<FibonacciEntity> findByIndex(Integer index);
}
