package com.bigsea.service.impl;

import com.bigsea.entity.Animal2;
import com.bigsea.mapper.Animal2Mapper;
import com.bigsea.service.Animal2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class Animal2ServiceImpl implements Animal2Service {
    @Autowired
    private Animal2Mapper animal2Mapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void add(Animal2 animal2) {
        animal2Mapper.insert(animal2);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void addWithException(Animal2 animal2) {
        animal2Mapper.insert(animal2);
        throw new RuntimeException();
    }
}
