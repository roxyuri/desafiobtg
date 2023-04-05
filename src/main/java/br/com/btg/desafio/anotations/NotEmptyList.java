package br.com.btg.desafio.anotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NotEmptyListValidator.class)
@Documented
public @interface NotEmptyList {
    String message() default "A lista não pode ser vazia ou nula.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
