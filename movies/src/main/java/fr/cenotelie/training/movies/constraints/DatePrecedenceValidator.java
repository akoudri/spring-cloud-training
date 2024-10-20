package fr.cenotelie.training.movies.constraints;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapperImpl;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class DatePrecedenceValidator implements ConstraintValidator<DatePrecedenceConstraint, Object> {
    private String before;
    private String after;
    private boolean strict = true;

    @Override
    public void initialize(DatePrecedenceConstraint constraint) {
        before = constraint.before();
        after = constraint.after();
        strict = constraint.strict();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        Object first = new BeanWrapperImpl(o).getPropertyValue(before);
        Object second = new BeanWrapperImpl(o).getPropertyValue(after);
        Instant fi, si;
        if (first instanceof LocalDate) fi = ((LocalDate)first).atStartOfDay(ZoneId.systemDefault()).toInstant();
        else if (first instanceof LocalDateTime) fi = ((LocalDateTime)first).atZone(ZoneId.systemDefault()).toInstant();
        else throw new RuntimeException(("expecting date or datetime for comparison"));
        if (second instanceof LocalDate) si = ((LocalDate)second).atStartOfDay(ZoneId.systemDefault()).toInstant();
        else if (second instanceof LocalDateTime) si = ((LocalDateTime)second).atZone(ZoneId.systemDefault()).toInstant();
        else throw new RuntimeException(("expecting date or datetime for comparison"));
        if (strict) return fi.isBefore(si);
        else return fi.isBefore(si) || fi == si;
    }

}
