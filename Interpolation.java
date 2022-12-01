import java.util.ArrayList;
import java.io.*;

public class Interpolation
{
    public String fileName;
    public double[][] dTable;
    public ArrayList<Double> storeY = new ArrayList<Double>();

    // this is the parameterized constructor
    public Interpolation(String fileName)
    {
        setFileName(fileName);
    }

    // this function creates a table.
    public void createTable()
    {
        String[] x = null;
        String[] fx = null;
        FileInputStream fStream = null;

        // check to see if file exists or not
        try
        {
            fStream = new FileInputStream(getFileName());
        }
        catch (FileNotFoundException err)
        {
            System.out.println("ERROR: The file does not exist...");
        }

        DataInputStream dis = new DataInputStream (fStream);
        BufferedReader br = new BufferedReader(new InputStreamReader(dis));

        //Check to see the contents of the file
        try {
            x = br.readLine().split("\\s+");
            fx = br.readLine().split("\\s+");
        }

        catch(IOException io){
            System.out.println("There is nothing to read");
        }

        dTable = new double[x.length][fx.length + 1];

        //setting the x and y values
        for(int i = 0; i < x.length; ++i) {
            dTable[i][0] = Double.parseDouble(x[i]);
        }

        for(int i = 0; i < fx.length; ++i){
            dTable[i][1] = Double.parseDouble(fx[i]);
        }

    }

    public void divideDifference(){
        int n = dTable[0].length;
        /*make a table using the algorithm in the book
         *However, making j = 1 for the loop would not give the right result
         *
         */

        for(int j = 2; j < n; j++){
            for(int i = 0; i < n - j; i++){
                dTable[i][j] = (dTable[i+1][j-1] - dTable[i][j-1]) /(dTable[i + (j-1)][0] - dTable[i][0]);
            }
        }

        for (int i = 1; i < dTable[0].length; ++i) {
            storeY.add(dTable[0][i]);
        }
    }

    //Making the interpolation function
    public void interpolation() {
        ArrayList<String> x = new ArrayList<String>();
        String sign = "";
        for (int i = 0; i < this.dTable.length - 1; ++i)
        {
            double xValue = this.dTable[i][0];

            if (xValue < 0)
            {
                sign = "+";
            }
            else if (xValue > 0)
            {
                sign = "-";
            }
            if ((Math.round(xValue * 1000) / 1000) == 0)
            {
                x.add("(x)");
            }
            else
            {
                x.add(String.format("(x%s%.3f)", sign, xValue));
            }
        }

        String polynomial = String.format("%.3f", storeY.get(0));

        for (int i = 1; i < x.size() + 1; ++i) {
            double yValue = storeY.get(i);
            if (yValue != 0) {
                if (yValue > 0) {
                    sign = "+";
                }
                else {
                    sign = "-";
                }
                String combine = "";
                for (int j = 0; j < i; ++j) {
                    combine += x.get(j);
                }
                polynomial += String.format(" %s %.3f%s", sign, Math.abs(yValue), combine);
            }
        }
        System.out.println(" ");
        System.out.println("\nThe interpolating polynomial is: " + polynomial);
    }

    public void print(){
        int n = dTable[0].length;
        System.out.printf(" x y f(,) f(,,) f(,,,) ");
        System.out.printf("\n____________________________________________________________\n");
        for(int i = 0; i < n - 1; i++){
            System.out.printf("\n");
            for(int j = 0; j < n - i; j++){
                System.out.printf(" ");
                System.out.printf("\t %.3f", dTable[i][j]);
            }
        }
    }

    //Simplified Polynomial
    public void simplifiedPolynomial(){
        Polynomial polynomial = new Polynomial();
        ArrayList<Double> value = new ArrayList<Double>();
        ArrayList<ArrayList<Double>> array = new ArrayList<ArrayList<Double>>();

        for(int i = 0; i < dTable[0].length - 1; i++){
            value.add(0.0);
        }

        value.add(0, storeY.get(0));
        array.add(value);

        for(int i = 1; i < storeY.size(); i++){
            value = new ArrayList<Double>();
            double yvalue = storeY.get(i);
            for(int j = 0; j < i; j++){
                value.add(dTable[j][0]);
            }
            array.add(polynomial.polyFunction(yvalue, value, dTable[0].length));
        }

        value = polynomial.combineLike(array);


        System.out.println("The simplified polynomial is: " + printString(value));
    }

    private String printString(ArrayList<Double> array) {
        String polynomial = "";
        String power = "";
        for (int i = 0; i < array.size() - 1; i++) {
            Double f = array.get(i);
            power = String.format("x^%d", i);
            if (f != 0) {
                if (i == 0) {
                    polynomial += String.format(" %.3f", f);
                }
                else {
                    polynomial += String.format(" %+.3f%s", f, power);
                }
            }
        }

        return polynomial;
    }

    public void setFileName(String fileName)
    {
        this.fileName = fileName;
    }

    public String getFileName()
    {
        return this.fileName;
    }
}