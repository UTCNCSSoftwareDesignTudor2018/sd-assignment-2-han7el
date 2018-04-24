package assignment2.utcn.controller;

import assignment2.utcn.business.CourseService;
import assignment2.utcn.business.TeacherService;
import assignment2.utcn.persistance.entity.Course;
import assignment2.utcn.persistance.entity.ReportObj;
import assignment2.utcn.persistance.entity.Teacher;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Inject;
import java.util.List;

@Controller
public class CourseController {

    @Inject
    private CourseService courseService;

    @Inject
    private TeacherService teacherService;

    @RequestMapping(value = "/courses", method = RequestMethod.GET)
    public ModelAndView getCourses() {
        List<Course> courseList = courseService.getAllCourses();

        ModelAndView mav = new ModelAndView("course_view");
        mav.addObject("courseWithStudentTeachersList", courseList);

        return mav;
    }

    @RequestMapping(value = "/courses", method = RequestMethod.POST)
    public ModelAndView createReport(@ModelAttribute(value = "newReport") ReportObj newReport)
    {
        Teacher currentTeacher = teacherService.getTeacherById(1);
        courseService.createCourseReport(newReport,currentTeacher);

        return new ModelAndView("redirect:/courses");

    }
}
