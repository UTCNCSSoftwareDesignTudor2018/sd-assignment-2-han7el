package assignment2.utcn.persistance.mongo;

import assignment2.utcn.persistance.entity.Teacher;

import java.net.UnknownHostException;
import java.util.List;

public interface Report<T> {

    public void storeReport(Teacher teacher, String reportName, List<T> t) throws UnknownHostException;
    public List<T> getReport(Teacher teacher) throws UnknownHostException;

}
