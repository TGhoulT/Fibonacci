package com.example.myfibonacciapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class FibonacciService {

    @Autowired
    private FibonacciRepository repository;

    private Map<Integer, BigInteger> memoizationMap = new HashMap<>();

    @Autowired
    private FibonacciCalculator calculator;

    public BigInteger getFibonacciNumber(int index) {
        //проверка, что индекс больше или равен 1
        if (index < 1) {
            throw new IllegalArgumentException("Index should be greater or equal to 1");
        }

        if (memoizationMap.containsKey(index)) {
            return memoizationMap.get(index);
        }

        //поиск в базе данных
        Optional<FibonacciEntity> entityOptional = repository.findByIndex(index);
        if (entityOptional.isPresent()) {
            BigInteger value = entityOptional.get().getValue();
            memoizationMap.put(index, value); //кэш в памяти
            return value;
        } else {
            //расчёт значения и сохранение в базу
            BigInteger value = calculateFibonacci(index);
            FibonacciEntity entity = new FibonacciEntity();
            entity.setIndex(index);
            entity.setValue(value);
            repository.save(entity);
            return value;
        }
    }

    private BigInteger calculateFibonacci(int index) {
        if (index == 1 || index == 2) {
            return BigInteger.ONE;
        }

        //итерационно с BigInteger
        BigInteger a = BigInteger.ONE, b = BigInteger.ONE, c = BigInteger.ZERO;
        for (int i = 3; i <= index; i++) {
            c = a.add(b);
            a = b;
            b = c;
        }
        return c;
    }
}
