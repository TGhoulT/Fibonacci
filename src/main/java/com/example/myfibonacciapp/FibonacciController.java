package com.example.myfibonacciapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class FibonacciController {

    @Autowired
    private FibonacciService fibonacciService;

    @GetMapping("/fibonacci")
    public Map<String, Integer> getFibonacciNumber(@RequestParam int index) {
        int value = fibonacciService.getFibonacciNumber(index);

        Map<String, Integer> result = new HashMap<>();
        result.put("index", index);
        result.put("value", value);

        return result;
    }
}
