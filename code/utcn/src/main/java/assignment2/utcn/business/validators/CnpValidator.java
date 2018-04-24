package assignment2.utcn.business.validators;

import assignment2.utcn.business.functions.Function;
import assignment2.utcn.persistance.entity.Student;

/**
 * Created by Boros on 3/27/2018.
 */
public class CnpValidator implements Validator<Student> {

    public void validate(Student student){
        if(!Function.isNumeric(student.getCnp())
                || !Function.isWellFormed(student.getCnp(),Function.CNP_LENGTH,Function.STRONG)){
            throw new IllegalArgumentException("CNP is invalid (exactly 13 characters) for: " + student.getName());
        }
    }
}
