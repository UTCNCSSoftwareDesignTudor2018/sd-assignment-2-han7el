package assignment2.utcn.persistance.entity;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer courseid;

    @Column
    private String name;

    @ManyToOne
    @JoinColumn(name = "teacherid")
    private Teacher teacher;

    @OneToMany(mappedBy = "course")
    private List<StudentCourse> students;

    public Integer getCourseid() {
        return courseid;
    }

    public void setCourseid(Integer courseid) {
        this.courseid = courseid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public List<StudentCourse> getStudents() {
        return students;
    }

    public void setStudents(List<StudentCourse> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseid=" + courseid +
                ", name='" + name + '\'' +
                ", teacher=" + teacher.getName() +
                ", students=" + students +
                '}';
    }
}
