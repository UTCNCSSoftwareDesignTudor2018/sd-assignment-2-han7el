package assignment2.utcn.persistance.mongo;

import assignment2.utcn.persistance.entity.Student;
import assignment2.utcn.persistance.entity.StudentCourse;
import assignment2.utcn.persistance.entity.Teacher;
import com.mongodb.*;

import java.net.UnknownHostException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class EnrolmentsReportDAO extends ReportDAO implements Report<StudentCourse> {

    public static final String COLLECTION_NAME = "enrolments_list";

    public void storeReport(Teacher teacher, String reportName, List<StudentCourse> enrollments) throws UnknownHostException {

        MongoClient mongoClient = new MongoClient(new MongoClientURI(getURI()));
        DB mongoDatabase = mongoClient.getDB(getDBName());
        DBCollection databaseCollection = mongoDatabase.getCollection(COLLECTION_NAME);
        DBObject dbObject = new BasicDBObject("reportName",reportName)
                .append("by teacher: ",teacher.getName());
        List<DBObject> dbEnrollmentObjects = new ArrayList<DBObject>();
        for(StudentCourse e : enrollments){
            DBObject dbEnrollmentObject = new BasicDBObject("student name: ", e.getStudent().getName())
                    .append("address",e.getStudent().getCnp())
                    .append("cnp",e.getStudent().getCnp())
                    .append("grade",e.getGrade())
                    .append("date",e.getDate())
                    .append("course",e.getCourse().getName());
            dbEnrollmentObjects.add(dbEnrollmentObject);
        }
        dbObject = ((BasicDBObject)dbObject).append("enrolments",dbEnrollmentObjects);
        databaseCollection.insert(dbObject);
        mongoClient.close();
    }

    @SuppressWarnings("unchecked")
    public List<StudentCourse> getReport(Teacher teacher) throws UnknownHostException{

        MongoClient mongoClient = new MongoClient(new MongoClientURI(getURI()));
        DB mongoDatabase = mongoClient.getDB(getDBName());
        DBCollection databaseCollection = mongoDatabase.getCollection(COLLECTION_NAME);
        DBObject query = new BasicDBObject("id",teacher.getId());
        DBCursor cursor = databaseCollection.find(query);
        List<StudentCourse> enrolmentsToReturn = new ArrayList<>();
        while(cursor.hasNext()){
            DBObject dbo = cursor.next();
            List<DBObject> dboEnrollments = (List<DBObject>)dbo.get("enrollments");
            for(DBObject dboEnrollment : dboEnrollments) {

                Student student = new Student();
                String name = (String)dboEnrollment.get("studentName");
                student.setAddress((String)dboEnrollment.get("address"));
                student.setCnp((String)dboEnrollment.get("cnp"));
                String gradeString = dboEnrollment.get("grade").toString();
                Integer grade = Integer.parseInt(gradeString);
                StudentCourse enrollment = new StudentCourse();
                enrollment.setStudent(student);
                enrollment.setGrade(grade);
                enrollment.setDate((Date)(dboEnrollment.get("date")));
                enrolmentsToReturn.add(enrollment);
            }
        }
       return enrolmentsToReturn;
    }
}
