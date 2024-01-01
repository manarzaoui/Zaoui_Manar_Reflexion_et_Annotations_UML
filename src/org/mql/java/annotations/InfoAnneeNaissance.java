package org.mql.java.annotations;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface InfoAnneeNaissance {
    int size() default 4; 
}

