/*
 *Random Stuff:
 */

package tatsukyo.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alexis TRAN
 */
public class Test {

    public static void main(String[] args) {
        connectBDD("com.mysql.cj.jdbc.Driver", "root", "", "jdbc:mysql://localhost/test_tatsukyo");
    }

    /**
     * Access to the test DB
     * com.mysql.cj.jdbc.Driver FOR MYSQL
     * test test
     * root 
     * @param pClassToUse
     * @param pUser
     * @param pPassword
     * @param pURL 
     */
    public static void connectBDD(String pClassToUse, String pUser, String pPassword, String pURL) {
        // Loading Access Class
        try {
            Class.forName(pClassToUse);
        } catch (ClassNotFoundException e) {
            System.out.println("Access class not found");
            System.exit(-1);
        }
        Connection lConnection = null;
        
        // Connect
        try {
            lConnection = DriverManager.getConnection(pURL, pUser, pPassword);
            lConnection.setAutoCommit(false);
        } catch (SQLException ex) {
            System.out.println("Connection failed");
            System.exit(-1);
        }

        // Insert value;
        try {
            String lRequest = "INSERT INTO user(firstname,lastname) VALUES (?,?)";
            PreparedStatement lStatement = lConnection.prepareStatement(lRequest);
            lStatement.setString(1, "test");
            lStatement.setString(2, "test");
            lStatement.execute();
            lStatement.close();
        } catch (SQLException ex) {
            System.out.println("INSERT failed");
            System.exit(-1);
        }
     

        // Get value;
        try {
            String lRequest = "SELECT * FROM user";
            Statement lStatement = lConnection.createStatement();
            ResultSet lResultSet = lStatement.executeQuery(lRequest);
            
            ResultSetMetaData lMetaData = lResultSet.getMetaData();
            int lNbColumn = lMetaData.getColumnCount();
            while(lResultSet.next()){
                for(int i=1;i<lNbColumn+1;i++){
                     System.out.println(lResultSet.getString(i));
                }
            }
            lResultSet.close();
            lStatement.close();
            lConnection.commit();
        } catch (SQLException ex) {
            System.out.println("SELECT failed");
            System.exit(-1);
        }
       
    }
    /**
     * Linear Search
     *
     * @param <T>
     * @param pArrays
     * @param pValue
     * @return true if present
     */
    public static <T> boolean linearSearch(T[] pArrays, T pValue) {
        boolean found = false;
        if (pArrays != null) {
            int lNbEntry = pArrays.length;
            for (int i = 0; i < lNbEntry && !found; i++) {
                T lCurrentValue = pArrays[i];
                if (lCurrentValue == pValue) {
                    found = true;
                }
            }
        }
        return found;
    }

    /**
     * Binary Search - Must be sorted
     *
     * @param <T>
     * @param pArrays
     * @param pComparator
     * @param pValue
     * @return true if present
     */
    public static <T> boolean binarySearch(T[] pArrays, Comparator<T> pComparator, T pValue) {
        boolean found = false;
        if (pArrays != null) {
            Arrays.sort(pArrays, pComparator);
            found = Arrays.binarySearch(pArrays, pValue, pComparator) >= 0;
        }
        return found;
    }
    
    /**
     * Find Closest to
     * Linear Method
     * 
     * @param pArrays
     * @param pLookingFor
     * @return the closest value to the searched value
     * 
     * 10,2,-14,3,2,8 4
     */
    public static int findClosestToLinear(int[] pArrays, int pLookingFor){
        //LINEAR SEARCH
        int lRet = Integer.MAX_VALUE;
        for (int lInt : pArrays) {
            int lShiftedInt = lInt - pLookingFor;
            int lShiftedRet = lRet - pLookingFor;
             if (Math.abs(lShiftedInt) <= Math.abs(lShiftedRet)) {
                int lRetABS = Math.abs(lShiftedRet);
                int lIntABS = Math.abs(lShiftedInt);
                if (lRetABS == lIntABS && lShiftedInt > lShiftedRet) {
                    lRet = lInt;
                } else {
                    lRet = lInt;
                }
            }
        }
        return lRet;
    }
    
    /**
     * Find Closest to
     * Linear Method
     * 
     * @param pArrays
     * @param pLookingFor
     * @return the closest value to the searched value
     */
    public static int findClosestToBinary(int[] pArrays, int pLookingFor){
        //BINARY SEARCH
        int lRet = 0;
        Arrays.sort(pArrays);
        int lIndexBinarySearch = Arrays.binarySearch(pArrays, pLookingFor);
        if (lIndexBinarySearch < 0) {//Found so its 0;
            lIndexBinarySearch = -lIndexBinarySearch - 1;
            int lUpperValue = pArrays[lIndexBinarySearch];
            int lLowerValue = Integer.MIN_VALUE;
            if (lIndexBinarySearch > 0) {
                lLowerValue = pArrays[lIndexBinarySearch - 1];
            }
            if (Math.abs(lUpperValue) < Math.abs(lLowerValue)) {
                lRet = lUpperValue;
            } else if (Math.abs(lUpperValue) == Math.abs(lLowerValue)) {
                lRet = lUpperValue > lLowerValue ? lUpperValue : lLowerValue;
            } else {
                lRet = lLowerValue;
            }
        }
        return lRet;
    }
}
