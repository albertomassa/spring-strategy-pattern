package com.am.springstrategy.strategy.annotation;


import com.am.springstrategy.strategy.profiles.Profile;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Documented
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Component
public @interface Strategy {

    Class type();

    Profile[] profiles() default {};

}
