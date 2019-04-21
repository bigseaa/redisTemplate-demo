package com.bigsea.service.impl;

import com.bigsea.entity.Animal1;
import com.bigsea.mapper.Animal1Mapper;
import com.bigsea.service.Animal1Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class Animal1ServiceImpl implements Animal1Service {
    @Autowired
    private Animal1Mapper animal1Mapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void add(Animal1 animal1) {
        animal1Mapper.insert(animal1);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void addWithException(Animal1 animal1) {
        animal1Mapper.insert(animal1);
        throw new RuntimeException();
    }
}
