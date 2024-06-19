package com.example.myfibonacciapp;

import javax.persistence.*;

@Entity
@Table(name = "fibonacci_entity")
public class FibonacciEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "`index`", unique = true, nullable = false)
    private Integer index;

    @Column(nullable = false)
    private Integer value;

    // Геттеры и сеттеры
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
