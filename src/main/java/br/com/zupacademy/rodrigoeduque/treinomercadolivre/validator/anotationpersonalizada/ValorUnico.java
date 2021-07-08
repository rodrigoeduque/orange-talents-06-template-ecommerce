package br.com.zupacademy.rodrigoeduque.treinomercadolivre.validator.anotationpersonalizada;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValorUnicoValidator.class)
public @interface ValorUnico {
    String message() default "Valor já exise cadastrado";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String valor() default "";

    String atributo();

    Class<?> classe();

}
