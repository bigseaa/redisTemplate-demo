package com.bigsea.controller;

import com.bigsea.entity.Animal;
import com.bigsea.entity.Animal1;
import com.bigsea.service.Animal1Service;
import com.bigsea.service.Animal2Service;
import com.bigsea.service.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = {"/testAnimal"})
public class TestAnimalController {
    @Autowired
    private Animal1Service animal1Service;

    @Autowired
    private Animal2Service animal2Service;

    @Autowired
    private AnimalService animalService;

    /*@RequestMapping(value = {"/test"})
    public void test(@RequestParam("name1") String name1, @RequestParam("name2") String name2) {
        animal1Service.
    }*/

    @RequestMapping(value = {"/test"})
    public void test() {
        animalService.testRequiredTransactionalTryException();
    }
}
