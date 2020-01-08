package com.am.springstrategy.controller;

import com.am.springstrategy.manager.IManager;
import com.am.springstrategy.strategy.factory.StrategyFactory;
import com.am.springstrategy.strategy.profiles.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @Autowired
    StrategyFactory factory;

    @GetMapping(value ="/first")
    public String first() {
        Profile profile = Profile.FIRST_STRATEGY;
        String message = factory.get(IManager.class, profile).getExampleMessage();
        return message;
    }

    @GetMapping(value ="/second")
    public String second() {
        Profile profile = Profile.SECOND_STRATEGY;
        String message = factory.get(IManager.class, profile).getExampleMessage();
        return message;
    }

}
