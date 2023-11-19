package dao;

import java.sql.SQLException;
import java.util.ArrayList;

public interface DAOInterface <T> {
    int insert(T t) throws SQLException;
    int update(T t);
//    int delete(T t);
    ArrayList<T> selectAll();
    ArrayList<T> selectByCondition(String condition);
//    T selectByID(String ID);

}
