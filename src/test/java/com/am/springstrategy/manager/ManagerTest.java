package com.am.springstrategy.manager;

import com.am.springstrategy.strategy.factory.StrategyFactory;
import com.am.springstrategy.strategy.profiles.Profile;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ManagerTest {

    @Autowired
    StrategyFactory factory;

    @Test
    public void testFirstStrategy() {
        Profile profile = Profile.FIRST_STRATEGY;
        String message = factory.get(IManager.class, profile).getExampleMessage();
        Assert.assertEquals(true, message.equals("I am the first manager"));
    }

    @Test
    public void testSecondStrategy() {
        Profile profile = Profile.SECOND_STRATEGY;
        String message = factory.get(IManager.class, profile).getExampleMessage();
        Assert.assertEquals(true, message.equals("I am the second manager"));
    }

    @Test
    public void testNotImplementedStrategy() {
        Profile profile = Profile.NOT_IMPLEMENTED_STRATEGY;
        Assertions.assertThrows(RuntimeException.class, () -> {
            String message = factory.get(IManager.class, profile).getExampleMessage();
        });
    }

}
