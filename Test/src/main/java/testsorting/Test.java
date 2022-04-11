package testsorting;

import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author Alexis TRAN
 */
public class Test {

    public static void main(String[] args) {
        int lNbEntry = 100000;
        int[] lArraysToSort = new int[lNbEntry];
        Random lRandom = new Random();
        for (int i = 0; i < lNbEntry; i++) {
            lArraysToSort[i] = lRandom.nextInt();
        }
        int[] lArraysSorted = new int[lNbEntry];
        int lCurrentIndex = 0;
        int lCurrentValue = 0;
        long lStartTime = System.nanoTime();
        for (int i = 0; i < lNbEntry; i++) {
            int lValueToInsert = lArraysToSort[i];

            int lIndexStartSearch = (lCurrentValue > lValueToInsert) ? 0 : lCurrentIndex;
            int lIndexEndSearch = (lCurrentValue > lValueToInsert) ? lCurrentIndex : i;

            int lIndexToInsert = Arrays.binarySearch(lArraysSorted, lIndexStartSearch, lIndexEndSearch, lValueToInsert);
            System.out.printf("Looking from index %s to %s\n", lIndexStartSearch, lIndexEndSearch);
            if (lIndexToInsert < 0) {
                lIndexToInsert = -lIndexToInsert - 1;
            }

            System.arraycopy(lArraysSorted, lIndexToInsert, lArraysSorted, lIndexToInsert+1, i-lIndexToInsert);
            System.out.printf("Moving %s values\n",i-lIndexToInsert);
            
            lArraysSorted[lIndexToInsert] = lValueToInsert;
            lCurrentIndex = lIndexToInsert;
            lCurrentValue = lValueToInsert;
        }
        System.out.printf("SORTING TOOK %s ms\n",(System.nanoTime()-lStartTime)/1e6);
        lStartTime = System.nanoTime();
        Arrays.sort(lArraysToSort);
        System.out.printf("SORTING TOOK %s ms\n",(System.nanoTime()-lStartTime)/1e6);
       
    }

}
