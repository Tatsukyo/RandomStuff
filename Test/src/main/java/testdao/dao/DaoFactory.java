package testdao.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alexis TRAN
 */
public class DaoFactory {
    private String mURL;
    private String mUser;
    private String mPassword;
    
    private static DaoFactory mInstance;

    public DaoFactory(String pURL, String pUser, String pPassword) {
        mURL = pURL;
        mUser = pUser;
        mPassword = pPassword;
    }
    
    public static synchronized  DaoFactory getInstance(){
        if(mInstance == null){
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException ex) {
            }
            mInstance = new DaoFactory("jdbc:mysql://localhost/test_tatsukyo", "test", "test");
        }
        return mInstance;
    }
    
    public Connection getConnection() throws SQLException{
        return DriverManager.getConnection(mURL, mUser, mPassword);
    }
    
    public UserDao getUserDao(){
        return new UserDao(this);
    }
}
