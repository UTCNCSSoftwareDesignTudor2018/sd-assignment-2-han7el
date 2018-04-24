package assignment2.utcn.persistance.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="students_courses")
public class StudentCourse implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "studentid")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "courseid")
    private Course course;

    @Column
    Integer grade;

    @Column
    Date date;



    //@Id
    //@ManyToOne
    //@JoinColumn(name = "studentid")
    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    //@Id
    //@ManyToOne
    //@JoinColumn(name = "courseid")
    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
