package assignment2.utcn.business.services;

import assignment2.utcn.persistance.mongo.ReportFactory;
import assignment2.utcn.persistance.entity.ReportObj;
import assignment2.utcn.persistance.entity.Teacher;
import assignment2.utcn.persistance.mongo.Report;
import assignment2.utcn.persistance.repo.TeacherRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.net.UnknownHostException;
import java.util.List;

@Service
public class TeacherService {

    @Inject
    TeacherRepository teacherRepository;

    public List<Teacher> getAllTeachersWithCourses(){
        return teacherRepository.findAll();
    }

    public Teacher update(Teacher updateTeacher){
        return teacherRepository.save(updateTeacher);
    }

    public Teacher getTeacherById(Integer id){
        return teacherRepository.findById(id).get();
    }

    public void createTeacherReport(ReportObj report, Teacher teacher){
        ReportFactory reportFactory = new ReportFactory();

        Report teachersReportDAO = reportFactory.getReport(ReportFactory.TEACHER_REPORT);
        try {
            teachersReportDAO.storeReport(teacher,report.getReportName(),teacherRepository.findAll());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
