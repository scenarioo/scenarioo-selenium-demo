package org.scenarioo.selenium.infrastructure.db;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * Annotation for test methods to define a different dataset to use.
 * See enum Datasets for available datasets that can be used.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Dataset {
    public DatasetDefinition value();    
}