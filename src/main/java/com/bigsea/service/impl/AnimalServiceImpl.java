package com.bigsea.service.impl;

import com.bigsea.entity.Animal;
import com.bigsea.entity.Animal1;
import com.bigsea.entity.Animal2;
import com.bigsea.mapper.AnimalMapper;
import com.bigsea.service.Animal1Service;
import com.bigsea.service.Animal2Service;
import com.bigsea.service.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AnimalServiceImpl implements AnimalService {
    @Autowired
    private AnimalMapper animalMapper;

    @Autowired
    private Animal1Service animal1Service;

    @Autowired
    private Animal2Service animal2Service;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void add(Animal animal) {
        animalMapper.insert(animal);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void addWithException(Animal animal) {
        animalMapper.insert(animal);
        throw new RuntimeException();
    }

    /**
     * 1.外层没有开启事务，但最后一行抛出异常
     * 2.内层两个事务传播行为均为REQURED，其中一个有抛出异常
     * 3.结果为animal1表插入了“狮子1”，animal2表未插入成功
     */
    public  void testRequiredNoTransactionalThrowException() {
        Animal1 animal1 = new Animal1();
        animal1.setName("狮子1");
        animal1Service.add(animal1);

        Animal2 animal2 = new Animal2();
        animal2.setName("狮子2");
        animal2Service.addWithException(animal2);

        throw new RuntimeException();
    }

    /**
     * 1.外层没有开启事务，且没有抛出异常
     * 2.内层两个事务传播行为均为REQURED，其中一个有抛出异常
     * 3.结果为animal1表插入了“狮子1”，animal2表未插入成功
     */
    @Override
    public void testRequiredNoTransactional() {
        Animal1 animal1 = new Animal1();
        animal1.setName("狮子1");
        animal1Service.add(animal1);

        Animal2 animal2 = new Animal2();
        animal2.setName("狮子2");
        animal2Service.addWithException(animal2);
    }

    /**
     * 1.外层开启事务，且没有抛出异常
     * 2.内层两个事务传播行为均为REQURED，其中一个有抛出异常
     * 3.结果为内层两个事务都发生了回滚，没有插入成功
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void testRequiredTransactional() {
        Animal1 animal1 = new Animal1();
        animal1.setName("狮子1");
        animal1Service.add(animal1);

        Animal2 animal2 = new Animal2();
        animal2.setName("狮子2");
        animal2Service.addWithException(animal2);
    }

    /**
     * 1.外层开启事务，且抛出异常
     * 2.内层两个事务传播行为均为REQURED，且都正常执行
     * 3.结果为内层两个事务都发生了回滚，没有插入成功
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void testRequiredTransactionalThrowException() {
        Animal1 animal1 = new Animal1();
        animal1.setName("狮子1");
        animal1Service.add(animal1);

        Animal2 animal2 = new Animal2();
        animal2.setName("狮子2");
        animal2Service.add(animal2);

        throw new RuntimeException();
    }

    /**
     * 1.内层两个事务传播行为均为REQURED，其中一个有抛出异常
     * 2.外层捕获异常，不主动抛出异常
     * 3.结果为内层两个事务都发生了回滚，没有插入成功
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void testRequiredTransactionalTryException() {
        Animal1 animal1 = new Animal1();
        animal1.setName("狮子1");
        animal1Service.add(animal1);

        Animal2 animal2 = new Animal2();
        animal2.setName("狮子2");
        try {
            animal2Service.addWithException(animal2);
        } catch (Exception e) {
            System.out.println("捕获异常，回滚");
        }
    }
}
