package com.jazzkuh.modulemanager.velocity.handlers.tasks;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface TaskInfo {

    TimeUnit timeType() default TimeUnit.SECONDS;
    long period() default -1;
    boolean repeating() default false;
    long delay() default 0;

}
