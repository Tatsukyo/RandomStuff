package testmontecarlopi;

/**
 *
 * @author Alexis TRAN
 */
public class Test {

    public static void main(String[] args) {
        int lNbEntry = 100000;
        double[][] lPoints = new double[lNbEntry][];
        for (int i = 0; i < lNbEntry; i++) {
            lPoints[i]= new double[2];
            lPoints[i][0] = Math.random();
            lPoints[i][1] = Math.random();
        }
        
        int lNbPoints = lPoints.length;
        int lNbPointsInside = 0;
        for(double[] lPoint:lPoints){
            double lX=lPoint[0];
            double lY=lPoint[1];
            if(((lX*lX) + (lY*lY))<1){
                lNbPointsInside++;
            }
        }
        
        double lPI = lNbPointsInside/(double)lNbPoints;
        System.out.println(lPI*4);
    }
    
}
