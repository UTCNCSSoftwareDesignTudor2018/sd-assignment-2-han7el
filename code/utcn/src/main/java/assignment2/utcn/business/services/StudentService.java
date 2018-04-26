package assignment2.utcn.business.services;

import assignment2.utcn.business.validators.Validator;
import assignment2.utcn.business.validators.ValidatorFactory;
import assignment2.utcn.persistance.mongo.ReportFactory;
import assignment2.utcn.persistance.entity.*;
import assignment2.utcn.persistance.mongo.Report;
import assignment2.utcn.persistance.repo.StudentCourseRepository;
import assignment2.utcn.persistance.repo.StudentRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.net.UnknownHostException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {

    @Inject
    StudentRepository studentRepository;

    private List<Validator<Student>> validators;

    public Student addNewStudent(Student newStudent){
        validateStudent(newStudent);
        return studentRepository.save(newStudent);
    }

    public Student updateStudent(Student updateStudent){
        validateStudent(updateStudent);
        return studentRepository.save(updateStudent);
    }

    public Student getStudentById(Integer id){
       return studentRepository.findById(id).get();
    }

    public List<Student> getAllStudents(){return studentRepository.findAll();}

    public void createStudentReport(ReportObj report, Teacher teacher){
        ReportFactory reportFactory = ReportFactory.getInstance();

        Report studentsReportDAO = reportFactory.getReport(ReportFactory.STUDENT_REPORT);
        try {
            studentsReportDAO.storeReport(teacher,report.getReportName(),studentRepository.findAll());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    //--------------------------------------------

    public void validateStudent(Student student){
        ValidatorFactory validatorFactory = ValidatorFactory.getInstance();

        validators = new ArrayList<Validator<Student>>();

        validators.add(validatorFactory.getValidator(ValidatorFactory.ADDRESS_VALIDATOR));
        validators.add(validatorFactory.getValidator(ValidatorFactory.NAME_VALIDATOR));
        validators.add(validatorFactory.getValidator(ValidatorFactory.CNP_VALIDATOR));
        validators.add(validatorFactory.getValidator(ValidatorFactory.GROUP_VALIDATOR));

        for(Validator<Student> sv: validators){
            sv.validate(student);
        }
    }
}
