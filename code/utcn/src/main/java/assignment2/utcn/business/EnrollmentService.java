package assignment2.utcn.business;

import assignment2.utcn.persistance.ReportFactory;
import assignment2.utcn.persistance.entity.ReportObj;
import assignment2.utcn.persistance.entity.Teacher;
import assignment2.utcn.persistance.mongo.Report;
import assignment2.utcn.persistance.repo.StudentCourseRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.net.UnknownHostException;

@Service
public class EnrollmentService {

    @Inject
    StudentCourseRepository studentCourseRepository;

    public void createEnrolmentReport(ReportObj report, Teacher teacher){

        ReportFactory reportFactory = new ReportFactory();

        Report enrolmentsReportDAO = reportFactory.getReport(ReportFactory.ENROLMENT_REPORT);
        try {
            enrolmentsReportDAO.storeReport(teacher,report.getReportName(),studentCourseRepository.findAll());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
