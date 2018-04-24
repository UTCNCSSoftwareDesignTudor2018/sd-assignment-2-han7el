package assignment2.utcn.persistance.mongo;

import assignment2.utcn.persistance.entity.Teacher;
import com.mongodb.*;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class TeachersReportDAO extends ReportDAO implements Report<Teacher> {

    public static final String COLLECTION_NAME = "teachers_list";

    @Override
    public void storeReport(Teacher teacher, String reportName, List<Teacher> teachers) throws UnknownHostException {
        MongoClient mongoClient = new MongoClient(new MongoClientURI(getURI()));
        DB mongoDatabase = mongoClient.getDB(getDBName());
        DBCollection databaseCollection = mongoDatabase.getCollection(COLLECTION_NAME);
        DBObject dbObject = new BasicDBObject("Report Name",reportName)
                .append("by teacher",teacher.getName());
        List<DBObject> dbTeacherObjects = new ArrayList<>();
        for(Teacher t: teachers){
            DBObject dbTeacherObject = new BasicDBObject("teacher name", t.getName())
                    .append("cnp",t.getCnp())
                    .append("address",t.getAddress());
            dbTeacherObjects.add(dbTeacherObject);
        }
        dbObject = ((BasicDBObject)dbObject).append("teachers",dbTeacherObjects);
        databaseCollection.insert(dbObject);
        mongoClient.close();
    }

    @Override
    public List getReport(Teacher teacher) throws UnknownHostException {
        return null;
    }
}
