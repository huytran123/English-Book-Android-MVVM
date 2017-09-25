package com.i.englishbook.annotations;

import com.i.englishbook.model.DBType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by huytran on 9/1/2017.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Column {
    String Name() default "";
    DBType Type() default DBType.TEXT;
}
