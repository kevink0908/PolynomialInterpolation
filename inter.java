// Java program for implementing
// Newton divided difference formula
import java.text.*;
import java.math.*;

class inter{

public static void main(String[] args){
    // number of inputs given
    int n = 4;
    double value, sum;
    double y[][]=new double[10][10];
    double x[] = {1, 1.5, 0, 2}; //use this
 

    y[0][0] = 3;
    y[1][0] = 3.25;
    y[2][0] = 3;
    y[3][0] = 1.67;  
 
    // calculating divided difference table
    dividedDiffTable(x, y, n);
 
    // displaying divided difference table
    printDiffTable(y,n,x);
 
    /*double [][] polyMatrix = {
        {1,1.5,0,2},
        {3,3.25,3,1.67}
    };
    */
    double [][] polyMatrix = {
        {0.33,0.25,1},
        {2,-1,7}
    };
    System.out.println();
    //calling lagrange
    lagrange(polyMatrix);
}

// divided difference table
public static void dividedDiffTable(double x[], double y[][], int n){
    for (int i = 1; i < n; i++) {
        for (int j = 0; j < n - i; j++) {
            y[j][i] = (y[j][i - 1] - y[j + 1][i - 1]) / (x[j] - x[i + j]);
        }
    }
}
 

 
// Function for displaying divided difference table
public static void printDiffTable(double y[][],int n, double x[]){ //might also need to input the original matrix
    DecimalFormat df = new DecimalFormat("#.####");
    df.setRoundingMode(RoundingMode.HALF_UP);
     
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n - i; j++) {
            String str1 = df.format(y[i][j]);
            System.out.print(str1+"\t ");
        }
        System.out.println("");
    }
    //System.out.println(y[0][0]); //row1 column 1
    //System.out.println(y[0][1]); //row2 column 2
    //System.out.println(y[0][2]); //row3 column 3

    System.out.print("Newton Equation: " + y[0][0]); 
    int ec = 1; 
    int newtonIndex = 1;
    for(int i = 0; i < x.length - 1; i++){
        System.out.print(" + ");
        System.out.print(y[0][ec]); //good
        for(int matrixIndex = 0; matrixIndex < x.length; matrixIndex++){
        if(matrixIndex < newtonIndex){
        if(matrixIndex == 0){
            System.out.print("x");
        } else if(matrixIndex > 0){
            System.out.print("(x - " + x[matrixIndex] + ")");
        } else {
            System.out.print("(x + " + x[matrixIndex] + ")");
        }
    }
    }
        newtonIndex++;
        ec++;
    }
}



    public static void lagrange(double[][] matrix){
        double[] lC = new double [matrix[0].length];
        
        //System.out.print("\n\nLagrange Form: ");
        System.out.print("Lagrange Form: ");
        for(int lagrangeIndex = 0; lagrangeIndex < lC.length; lagrangeIndex++){    
            if(lagrangeIndex > 0){
                    System.out.print("+"); 
            }
            lC[lagrangeIndex] = 1;
            for(int i = 0; i < lC.length; i++){
                if (lagrangeIndex == i)
                    i++;
                if(i < lC.length)
                    lC[lagrangeIndex] *= 1 / (matrix[0][lagrangeIndex] - matrix[0][i]);
            }
            lC[lagrangeIndex] *= matrix[1][lagrangeIndex];
            System.out.print(lC[lagrangeIndex]);
            for(int i = 0; i < lC.length; i++){
                if(lagrangeIndex == i)
                    i++;
                if(i < lC.length){
                    if(matrix[0][i] == 0)
                        System.out.print("x");
                    else if(matrix[0][i] > 0)
                        System.out.print("(x - " + matrix[0][i] + ")");
                    else
                        System.out.print("(x + " + matrix[0][i] + ")");
                    
                }
            }
        } 
        System.out.println();

    }

 

}