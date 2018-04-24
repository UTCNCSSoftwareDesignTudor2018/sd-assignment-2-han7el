package assignment2.utcn.controller;


import assignment2.utcn.business.services.CourseService;
import assignment2.utcn.business.services.EnrollmentService;
import assignment2.utcn.business.services.StudentService;
import assignment2.utcn.persistance.entity.Course;
import assignment2.utcn.persistance.entity.Student;
import assignment2.utcn.persistance.entity.StudentCourse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Inject;
import java.util.List;

@Controller
public class StudentController {

    @Inject
    StudentService studentService;
    @Inject
    CourseService courseService;
    @Inject
    EnrollmentService enrollmentService;

    @RequestMapping(value = "/student",method = RequestMethod.GET)
    public ModelAndView getStudent()
    {
        Student currentStudent = studentService.getStudentById(1);

        ModelAndView mav = new ModelAndView("student_view");
        mav.addObject("student", currentStudent);

        return mav;
    }

    @RequestMapping(value = "/student", method = RequestMethod.POST)
    public ModelAndView updateStudent(@ModelAttribute(value = "updateStudent") Student updateStudent)
    {
        updateStudent.setStudentid(1);
        studentService.updateStudent(updateStudent);

        return new ModelAndView("redirect:student");

    }

    @RequestMapping(value= "/student/enrollment",method = RequestMethod.GET)
    public ModelAndView viewEnrolment(ModelMap model)
    {
        List<StudentCourse> studentCourse = enrollmentService.getEnrolledCoursesOfStudent(1);
        List<Course> allCourses = courseService.getAllCourses();
        ModelAndView mav = new ModelAndView("enrolment_view");
        model.addAttribute("enrollmentOptions",allCourses);
        mav.addObject("enrollmentList",studentCourse);

        return mav;

    }

    @RequestMapping(value="/student/enrollment/enrollmentOptions",method = RequestMethod.POST)
    public ModelAndView enrollToCourse(@ModelAttribute(value="enrollmentOptions") @RequestParam Integer courseid)
    {
        enrollmentService.enrollStudentToCourse(studentService.getStudentById(1),courseService.getCourseById(courseid));
        return new ModelAndView("redirect:/student/enrollment");
    }

    /*
    @RequestMapping(value="/enrollment/enrollmentOptions", method=RequestMethod.POST)
	public String enrollToCourse(@ModelAttribute ("enrollmentOptions") @RequestParam Integer courseId) {
			studentService.enrollMe(studentService.getStudentByUser(1).getStudentId(), courseId);
		return "redirect:/enrollment";
	}

    @RequestMapping(value = "/enrollment", method = RequestMethod.GET)
	public ModelAndView displayEnrolledCourses(ModelMap model) {
		ModelAndView mav= new ModelAndView("enrollment_view");
		model.addAttribute("enrollmentOptions", studentService.getNotEnrolled(studentService.getStudentByUser(1).getStudentId()));
		mav.addObject("enrolledCourses",studentService.getCoursesForStudent(studentService.getStudentByUser(1).getStudentId()));
		return mav;
	}

    */
}
