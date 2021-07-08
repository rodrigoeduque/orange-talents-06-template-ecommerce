package br.com.zupacademy.rodrigoeduque.treinomercadolivre.validator.anotationpersonalizada.existeid;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ExisteIdValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExisteId {
    String message() default "ID utilizado não existe ";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
    String atributo();
    Class<?> classe();
}
