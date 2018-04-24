package assignment2.utcn.business.validators;

import assignment2.utcn.business.functions.Function;
import assignment2.utcn.persistance.entity.Student;

/**
 * Created by Boros on 3/27/2018.
 */
public class GnValidator implements Validator<Student> {
    public void validate(Student student) {
        if(!Function.isNumeric(student.getGroupp())
                || !Function.isWellFormed(student.getGroupp(),Function.GN_LENGTH,Function.STRONG)){
            throw new IllegalArgumentException("Group number is invalid for: " + student.getName());
        }
    }
}
