package com.example.mutants.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Created by mnsantos on 10/9/17.
 */

@Documented
@Constraint(validatedBy = DnaSequenceValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DnaSequenceConstraint {
    String message() default "Invalid dna sequence";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

