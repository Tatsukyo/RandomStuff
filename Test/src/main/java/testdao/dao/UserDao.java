package testdao.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import testdao.dto.User;

/**
 *
 * @author Alexis TRAN
 */
public class UserDao {
    private final DaoFactory mDaoFactory;
    
    public UserDao(DaoFactory pDaoFactory){
        mDaoFactory = pDaoFactory;
    }
    
    public boolean addUser(User pUser){
        boolean added = false;
        
        try{
            Connection lConnection = mDaoFactory.getConnection();
            String lInsert = "INSERT INTO user(firstname, lastname) VALUES(?, ?);";
            PreparedStatement lPreparedStatement = lConnection.prepareStatement(lInsert, Statement.RETURN_GENERATED_KEYS);
            lPreparedStatement.setString(1, pUser.getFirstname());
            lPreparedStatement.setString(2, pUser.getLastname());
            lPreparedStatement.executeUpdate();

            try ( ResultSet lGeneratedKeys = lPreparedStatement.getGeneratedKeys()) {
                if (lGeneratedKeys.next()) {
                    pUser.setId(lGeneratedKeys.getInt(1));
                }
            }
            lPreparedStatement.close();
            lConnection.close();
            
            added = true;
        }catch(SQLException e){
            System.out.println("Add user failed");
        }
        return added;
    }
    
    public User findUser(int pID){
        User lUser = null;
         try{
            Connection lConnection = mDaoFactory.getConnection();
            String lSelect = "SELECT * FROM user WHERE id = ?;";
            PreparedStatement lPreparedStatement = lConnection.prepareStatement(lSelect);
            lPreparedStatement.setInt(1, pID);
            ResultSet lResultSet = lPreparedStatement.executeQuery();
            if(lResultSet.next()){
                lUser = new User();
                lUser.setId(lResultSet.getInt("id"));
                lUser.setFirstname(lResultSet.getString("firstname"));
                lUser.setLastname(lResultSet.getString("lastname"));
            }
            
            lPreparedStatement.close();
            lConnection.close();
        }catch(SQLException e){
             System.out.println("find user failed");
        }
        return lUser;
    }
    
    public List<User> getUsers(){
        List<User> lUsers = new ArrayList();
         try{
            Connection lConnection = mDaoFactory.getConnection();
            String lSelect = "SELECT * FROM user";
            Statement lStatement = lConnection.createStatement();
            ResultSet lResultSet = lStatement.executeQuery(lSelect);
            if(lResultSet.next()){
                User lUser = new User();
                lUser.setId(lResultSet.getInt("id"));
                lUser.setFirstname(lResultSet.getString("firstname"));
                lUser.setLastname(lResultSet.getString("lastname"));
                lUsers.add(lUser);
            }
            lStatement.close();
            lConnection.close();
        }catch(SQLException e){
            System.out.println("find users failed");
        }
        return lUsers;
    }
    
    public boolean removeUser(int pID){
         boolean removed = false;
        
        try{
            Connection lConnection = mDaoFactory.getConnection();
            String lDelete = "DELETE FROM user WHERE id = ?;";
            PreparedStatement lPreparedStatement = lConnection.prepareStatement(lDelete);
            lPreparedStatement.setInt(1, pID);
            lPreparedStatement.executeUpdate();
            
            lPreparedStatement.close();
            lConnection.close();
            
            removed = true;
        }catch(SQLException e){
            System.out.println("delete user failed");
        }
        return removed;
    }
    
}
