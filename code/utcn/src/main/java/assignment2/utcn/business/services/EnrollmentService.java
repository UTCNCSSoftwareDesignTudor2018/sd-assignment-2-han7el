package assignment2.utcn.business.services;

import assignment2.utcn.persistance.entity.*;
import assignment2.utcn.persistance.mongo.ReportFactory;
import assignment2.utcn.persistance.mongo.Report;
import assignment2.utcn.persistance.repo.StudentCourseRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.net.UnknownHostException;
import java.sql.Date;
import java.util.List;

@Service
public class EnrollmentService {

    @Inject
    StudentCourseRepository studentCourseRepository;

    public void enrollStudentToCourse(Student student, Course course){

        StudentCourse enrolment = new StudentCourse();
        enrolment.setStudent(student);
        enrolment.setCourse(course);
        enrolment.setDate(new Date(2018,04,18));

        studentCourseRepository.save(enrolment);
    }

    public List<StudentCourse> getEnrolledCoursesOfStudent(Integer id)
    {
        return studentCourseRepository.findAllByStudentStudentid(id);
    }

    public void createEnrolmentReport(ReportObj report, Teacher teacher){

        ReportFactory reportFactory = ReportFactory.getInstance();

        Report enrolmentsReportDAO = reportFactory.getReport(ReportFactory.ENROLMENT_REPORT);
        try {
            enrolmentsReportDAO.storeReport(teacher,report.getReportName(),studentCourseRepository.findAll());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
