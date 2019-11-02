package net.qianqiuxi.register.auth.annotation;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Skip auth
 */
@Documented
@Retention(RUNTIME)
@Target(METHOD)
public @interface NoneAuth {

}