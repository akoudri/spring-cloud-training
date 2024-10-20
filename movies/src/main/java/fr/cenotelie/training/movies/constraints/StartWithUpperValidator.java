package fr.cenotelie.training.movies.constraints;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class StartWithUpperValidator implements ConstraintValidator<StartWithUpperCase, Object> {

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        if (o instanceof String) {
            //TODO
        }
        return true;
    }
}
