package assignment2.utcn.persistance.repo;

import assignment2.utcn.persistance.entity.StudentCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentCourseRepository extends JpaRepository<StudentCourse, Integer> {

    public List<StudentCourse> findAllByStudentStudentid(Integer id);//findAllByStudentStudentid

    public List<StudentCourse> findAllByCourseCourseid(Integer id);//findAllByCourseCourseid
}
