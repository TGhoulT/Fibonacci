package com.example.myfibonacciapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class FibonacciService {

    @Autowired
    private FibonacciRepository repository;

    @Autowired
    private FibonacciCalculator calculator;

    public Integer getFibonacciNumber(int index) {
        //проверка, что индекс больше или равен 1
        if (index < 1) {
            throw new IllegalArgumentException("Index should be greater or equal to 1");
        }

        //поиск в базе данных
        Optional<FibonacciEntity> entityOptional = repository.findByIndex(index);
        if (entityOptional.isPresent()) {
            return entityOptional.get().getValue();
        } else {
            // Расчёт значения и сохранение в базу
            Integer value = calculator.calculate(index);
            FibonacciEntity entity = new FibonacciEntity();
            entity.setIndex(index);
            entity.setValue(value);
            repository.save(entity);
            return value;
        }
    }
}
