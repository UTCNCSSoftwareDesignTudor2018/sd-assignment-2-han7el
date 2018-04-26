package assignment2.utcn.business.services;

import assignment2.utcn.persistance.mongo.ReportFactory;
import assignment2.utcn.persistance.entity.Course;
import assignment2.utcn.persistance.entity.ReportObj;
import assignment2.utcn.persistance.entity.Teacher;
import assignment2.utcn.persistance.mongo.Report;
import assignment2.utcn.persistance.repo.CourseRepository;


import org.springframework.stereotype.Service;


import javax.inject.Inject;
import java.net.UnknownHostException;
import java.util.List;

@Service()
public class CourseService {

    @Inject
    CourseRepository courseRepository;

    public List<Course> getAllCourses(){return courseRepository.findAll();}

   public Course getCourseById(Integer id)
   {
       return courseRepository.findById(id).get();
   }

    public void createCourseReport(ReportObj report, Teacher teacher){

        ReportFactory reportFactory = ReportFactory.getInstance();

        Report courseReportDAO = reportFactory.getReport(ReportFactory.COURSE_REPORT);
        try {
            courseReportDAO.storeReport(teacher,report.getReportName(),courseRepository.findAll());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
