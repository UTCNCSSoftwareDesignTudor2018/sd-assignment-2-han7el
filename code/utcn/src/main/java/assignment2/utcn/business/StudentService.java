package assignment2.utcn.business;

import assignment2.utcn.persistance.ReportFactory;
import assignment2.utcn.persistance.entity.*;
import assignment2.utcn.persistance.mongo.Report;
import assignment2.utcn.persistance.repo.StudentCourseRepository;
import assignment2.utcn.persistance.repo.StudentRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.net.UnknownHostException;
import java.sql.Date;
import java.util.List;

@Service
public class StudentService {

    @Inject
    StudentRepository studentRepository;
    @Inject
    StudentCourseRepository studentCourseRepository;

    public Student update(Student updateStudent){
        return studentRepository.save(updateStudent);
    }

    public Student getStudentById(Integer id){
       return studentRepository.findById(id).get();
    }

    public List<StudentCourse> getEnrolledCoursesOfStudent(Integer id)
    {
        return studentCourseRepository.findAllByStudentStudentid(id);
    }

    public List<Student> getAllStudents(){return studentRepository.findAll();}

    public Student addNewStudent(Student newStudent){return studentRepository.save(newStudent);}

    public void enrollStudentToCourse(Student student, Course course){

        StudentCourse enrolment = new StudentCourse();
        enrolment.setStudent(student);
        enrolment.setCourse(course);
        enrolment.setDate(new Date(2018,04,18));

        studentCourseRepository.save(enrolment);
    }

    public void createStudentReport(ReportObj report, Teacher teacher){
        ReportFactory reportFactory = new ReportFactory();

        Report studentsReportDAO = reportFactory.getReport(ReportFactory.STUDENT_REPORT);
        try {
            studentsReportDAO.storeReport(teacher,report.getReportName(),studentRepository.findAll());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
