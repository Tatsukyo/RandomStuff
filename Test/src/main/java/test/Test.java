/*
 *Random Stuff:
 */

package test;

import java.util.Arrays;
import java.util.Comparator;

/**
 *
 * @author Alexis TRAN
 */
public class Test {

    public static void main(String[] args) {
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
