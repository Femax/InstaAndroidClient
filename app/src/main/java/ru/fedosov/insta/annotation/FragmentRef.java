package ru.fedosov.insta.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by RelaxedSoul on 17.12.2015.
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Inherited
public @interface FragmentRef {

    /** in */
    int resource() default 0;

    boolean busEnabled() default false;

    boolean knifeEnabled() default false;
}