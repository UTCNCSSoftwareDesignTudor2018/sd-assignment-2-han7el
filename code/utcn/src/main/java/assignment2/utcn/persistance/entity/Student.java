package assignment2.utcn.persistance.entity;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="students")
public class Student{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer studentid;

    @Column(columnDefinition = "CHAR(50)")
    private String name;

    @Column(columnDefinition = "CHAR(13)")
    private String cnp;

    @Column(columnDefinition = "CHAR(50)")
    private String address;

    @Column(columnDefinition = "CHAR(5)")
    private String groupp;

    @OneToMany(mappedBy = "student")
    private List<StudentCourse> courses;

    public Integer getStudentid() {
        return studentid;
    }

    public void setStudentid(Integer studentid) {
        this.studentid = studentid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCnp() {
        return cnp;
    }

    public void setCnp(String cnp) {
        this.cnp = cnp;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGroupp() {
        return groupp;
    }

    public void setGroupp(String groupp) {
        this.groupp = groupp;
    }

    public List<StudentCourse> getCourses() {
        return courses;
    }

    public void setCourses(List<StudentCourse> courses) {
        this.courses = courses;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentid=" + studentid +
                ", name='" + name + '\'' +
                ", cnp='" + cnp + '\'' +
                ", address='" + address + '\'' +
                ", groupp='" + groupp + '\'' +
                '}';
    }
}
