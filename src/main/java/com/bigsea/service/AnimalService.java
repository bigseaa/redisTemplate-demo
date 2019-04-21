package com.bigsea.service;

import com.bigsea.entity.Animal;

public interface AnimalService {
    void add(Animal animal);

    void addWithException(Animal animal);

    void testRequiredNoTransactionalThrowException();

    void testRequiredNoTransactional();

    void testRequiredTransactional();

    void testRequiredTransactionalThrowException();

    void testRequiredTransactionalTryException();
}
