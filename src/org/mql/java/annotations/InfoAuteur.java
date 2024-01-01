package org.mql.java.annotations;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface InfoAuteur {
    String nom();
    String prénom();
    int anneeNaissance() default -1;
}

