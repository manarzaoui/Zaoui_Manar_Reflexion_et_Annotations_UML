package org.mql.java.annotations;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface InfoLivre {
    String titre();
    String auteur() default "Inconnu";
}
