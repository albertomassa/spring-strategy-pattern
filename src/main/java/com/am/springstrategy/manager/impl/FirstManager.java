package com.am.springstrategy.manager.impl;

import com.am.springstrategy.manager.IManager;
import com.am.springstrategy.strategy.annotation.Strategy;
import com.am.springstrategy.strategy.profiles.Profile;
import lombok.extern.java.Log;

@Log

@Strategy(type= IManager.class, profiles = {Profile.FIRST_STRATEGY})
public class FirstManager implements IManager {

    @Override
    public String getExampleMessage() {
        log.info("first manager called");
        return "I am the first manager";
    }

}
