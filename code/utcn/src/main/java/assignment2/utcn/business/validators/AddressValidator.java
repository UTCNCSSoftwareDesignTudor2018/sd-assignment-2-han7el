package assignment2.utcn.business.validators;

import assignment2.utcn.business.functions.Function;
import assignment2.utcn.persistance.entity.Student;

/**
 * Created by Boros on 3/27/2018.
 */
public class AddressValidator implements Validator<Student> {
    public void validate(Student student) {
        if(!Function.isWellFormed(student.getAddress(),Function.ADDRESS_NAME_LENGTH,Function.WEAK)){
            throw new IllegalArgumentException("Address is invalid (<50 characters) for: " + student.getName());
        }
    }
}
