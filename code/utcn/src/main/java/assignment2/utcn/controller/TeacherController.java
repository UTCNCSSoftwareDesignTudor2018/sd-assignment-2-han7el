package assignment2.utcn.controller;

import assignment2.utcn.business.EnrollmentService;
import assignment2.utcn.business.StudentService;
import assignment2.utcn.business.TeacherService;
import assignment2.utcn.persistance.entity.ReportObj;
import assignment2.utcn.persistance.entity.Student;
import assignment2.utcn.persistance.entity.Teacher;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Inject;
import java.util.List;

@Controller
public class TeacherController {

    @Inject
    TeacherService teacherService;
    @Inject
    StudentService studentService;
    @Inject
    EnrollmentService enrolmentService;

    @RequestMapping(value = "/teacher",method = RequestMethod.GET)
    public ModelAndView getTeacher()
    {
        Teacher currentTeacher = teacherService.getTeacherById(1);

        ModelAndView mav = new ModelAndView("teacher_view");
        mav.addObject("teacher", currentTeacher);

        return mav;
    }

    @RequestMapping(value = "/teacher", method = RequestMethod.POST)
    public ModelAndView updateTeacher(@ModelAttribute(value = "updateTeacher") Teacher updateTeacher)
    {
        updateTeacher.setId(1);
        teacherService.update(updateTeacher);

        return new ModelAndView("redirect:teacher");

    }

    @RequestMapping(value = "/teacher/teachers",method = RequestMethod.GET)
    public ModelAndView getAllTeachers()
    {
        List<Teacher> teacherList = teacherService.getAllTeachersWithCourses();

        ModelAndView mav = new ModelAndView("teachers_view");
        mav.addObject("teachersWithCoursesList", teacherList);

        return mav;
    }

    @RequestMapping(value = "/teacher/teachers")
    public ModelAndView createTeacherReport(@ModelAttribute(value = "newReport")ReportObj newReport)
    {
        Teacher currentTeacher = teacherService.getTeacherById(1);
        teacherService.createTeacherReport(newReport,currentTeacher);

        return new ModelAndView("redirect:/teacher/teachers");

    }

    @RequestMapping(value = "/teacher/students",method = RequestMethod.GET)
    public ModelAndView getAllStudents()
    {
        List<Student> studentList = studentService.getAllStudents();

        ModelAndView mav = new ModelAndView("students_view");
        mav.addObject("studentsWithCoursesList", studentList);

        return mav;
    }

    @RequestMapping(value = "/teacher/students", method = RequestMethod.POST)
    public ModelAndView postStudent(@ModelAttribute(value = "newStudent") Student newStudent)
    {
        studentService.addNewStudent(newStudent);

        return new ModelAndView("redirect:/teacher/students");

    }

    @RequestMapping(value = "/teacher/students")
    public ModelAndView createStudentReport(@ModelAttribute(value = "newReport")ReportObj newReport)
    {
        Teacher currentTeacher = teacherService.getTeacherById(1);
        studentService.createStudentReport(newReport,currentTeacher);

        return new ModelAndView("redirect:/teacher/students");

    }

    @RequestMapping(value = "/teacher/enrolments",method = RequestMethod.GET)
    public ModelAndView getAllEnrolments()
    {
        List<Student> studentList = studentService.getAllStudents();

        ModelAndView mav = new ModelAndView("teacher_enrolment_view");
        mav.addObject("studentsWithCoursesList", studentList);

        return mav;
    }

    @RequestMapping(value = "/teacher/enrolments")
    public ModelAndView createEnrolmentReport(@ModelAttribute(value = "newReport")ReportObj newReport)
    {
        Teacher currentTeacher = teacherService.getTeacherById(1);
        enrolmentService.createEnrolmentReport(newReport,currentTeacher);

        return new ModelAndView("redirect:/teacher/enrolments");

    }

}
