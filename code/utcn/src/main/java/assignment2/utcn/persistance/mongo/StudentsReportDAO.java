package assignment2.utcn.persistance.mongo;

import assignment2.utcn.persistance.entity.Student;
import assignment2.utcn.persistance.entity.Teacher;
import com.mongodb.*;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class StudentsReportDAO extends ReportDAO implements Report<Student>{

    public static final String COLLECTION_NAME = "students_list";

    @Override
    public void storeReport(Teacher teacher, String reportName, List<Student> students) throws UnknownHostException {
        MongoClient mongoClient = new MongoClient(new MongoClientURI(getURI()));
        DB mongoDatabase = mongoClient.getDB(getDBName());
        DBCollection databaseCollection = mongoDatabase.getCollection(COLLECTION_NAME);
        DBObject dbObject = new BasicDBObject("Report Name",reportName)
                .append("by teacher",teacher.getName());
        List<DBObject> dbStudentObjects = new ArrayList<>();
        for(Student s: students){
            DBObject dbStudentObject = new BasicDBObject("student name", s.getName())
                    .append("cnp",s.getCnp())
                    .append("address",s.getAddress())
                    .append("group",s.getGroupp());
            dbStudentObjects.add(dbStudentObject);
        }
        dbObject = ((BasicDBObject)dbObject).append("students",dbStudentObjects);
        databaseCollection.insert(dbObject);
        mongoClient.close();
    }

    @Override
    public List<Student> getReport(Teacher teacher) throws UnknownHostException {
        return null;
    }
}
