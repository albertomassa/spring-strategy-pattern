package com.am.springstrategy.strategy.factory;

import com.am.springstrategy.strategy.annotation.Strategy;
import com.am.springstrategy.strategy.profiles.Profile;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;

@Log

@Component
public class StrategyFactory {

    @Autowired
    private ApplicationContext context;

    private Map<Class, List<Object>> types = new HashMap<>();
    private Map<Class, Strategy> strategies = new HashMap<>();

    @PostConstruct
    public void init() {
        Map<String, Object> annotatedBeans = context.getBeansWithAnnotation(Strategy.class);
        cache(annotatedBeans.values());
        for (Object bean : annotatedBeans.values()) {
            Strategy strategyAnnotation = strategies.get(bean.getClass());
            List<Object> strategyBeans = getBeans(strategyAnnotation);
            strategyBeans.add(bean);
        }
    }

    private List<Object> getBeans(Strategy annotation) {
        List<Object> beans;
        beans = types.get(annotation.type());
        if (beans != null) {
            return beans;
        }
        beans =  new ArrayList<>();
        types.put(annotation.type(), beans);
        return beans;
    }

    private void cache(Collection<Object> beans) {
        for (Object bean : beans) {
            Strategy strategyAnnotation = AnnotationUtils.findAnnotation(bean.getClass(), Strategy.class);
            strategies.put(bean.getClass(), strategyAnnotation);
        }
    }

    public <T> T get(Class<T> type, Profile profile) {
        List<Object> strategyBeans = types.get(type);
        if(strategyBeans.isEmpty()) {
            throw new RuntimeException("no strategies found for  " + type );
        }
        Object strategy = find(strategyBeans, profile);
        return (T) strategy;
    }

    private Object find(List<Object> strategyBeans, Profile current) {
        for (Object bean : strategyBeans) {
            Strategy annotation = strategies.get(bean.getClass());
            if (current != null) {
                for (Profile profile : annotation.profiles()) {
                    if (profile == current) {
                        log.info("found: " + annotation.type() + "profile: " + current);
                        return bean;
                    }
                }
            }
        }
        throw new RuntimeException("strategy not found");
    }

}
