package assignment2.utcn.persistance;

import assignment2.utcn.persistance.mongo.*;

public class ReportFactory {

    //use getReport method to get object of type shape
    public static final String ENROLMENT_REPORT = "EnrolmentReport";
    public static final String STUDENT_REPORT = "StudentReport";
    public static final String COURSE_REPORT = "CourseReport";
    public static final String TEACHER_REPORT = "TeacherReport";

    public Report getReport(String reportType){
        if(reportType == null){
            return null;
        }
        if(reportType.equalsIgnoreCase(ENROLMENT_REPORT)){
            return new EnrolmentsReportDAO();
        }
        if(reportType.equalsIgnoreCase(STUDENT_REPORT)){
            return new StudentsReportDAO();
        }
        if(reportType.equalsIgnoreCase(COURSE_REPORT)){
            return new CoursesReportDAO();
        }
        if(reportType.equalsIgnoreCase(TEACHER_REPORT)){
            return new TeachersReportDAO();
        }

        return null;
    }
}
