package assignment2.utcn.business.validators;

import assignment2.utcn.business.functions.Function;
import assignment2.utcn.persistance.entity.Student;

/**
 * Created by Boros on 3/27/2018.
 */
public class NameValidator implements Validator<Student> {
    public void validate(Student student) {
        if(!Function.isWellFormed(student.getName(),Function.ADDRESS_NAME_LENGTH,Function.WEAK)){
            throw new IllegalArgumentException("Name is invalid (<50 characters) for: " + student.getName());
        }
    }
}
