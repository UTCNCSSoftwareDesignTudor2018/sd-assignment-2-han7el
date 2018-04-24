package assignment2.utcn.persistance.mongo;

import assignment2.utcn.persistance.entity.Course;
import assignment2.utcn.persistance.entity.Teacher;
import com.mongodb.*;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class CoursesReportDAO extends ReportDAO implements Report<Course> {

    public static final String COLLECTION_NAME = "courses_list";

    @Override
    public void storeReport(Teacher teacher, String reportName, List<Course> courses) throws UnknownHostException {

        MongoClient mongoClient = new MongoClient(new MongoClientURI(getURI()));
        DB mongoDatabase = mongoClient.getDB(getDBName());
        DBCollection databaseCollection = mongoDatabase.getCollection(COLLECTION_NAME);
        DBObject dbObject = new BasicDBObject("Report Name",reportName)
                .append("by teacher",teacher.getName());
        List<DBObject> dbCourseObjects = new ArrayList<DBObject>();
        for(Course c: courses){
            DBObject dbCourseObject = new BasicDBObject("course name: ",c.getName()).
                    append("teacher name: ",c.getTeacher().getName());
            dbCourseObjects.add(dbCourseObject);
        }
        dbObject = ((BasicDBObject)dbObject).append("courses",dbCourseObjects);
        databaseCollection.insert(dbObject);
        mongoClient.close();

    }

    @Override
    public List<Course> getReport(Teacher teacher) throws UnknownHostException {
        return null;
    }
}
