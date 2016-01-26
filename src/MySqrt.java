public class MySqrt {

    double TOLERANCE = 0.000001; // fault tolerance constant

    public static void main(String[] args) {
        System.out.println("Hello World!");
    }

    // Calculates square root of x with fault tolerance epsilon, using a loop
    public static double mySqrtLoop(double x, double epsilon) {
        if (x < 0) {
            return Double.NaN; // if negative argument there's no square root; return NaN
        }

        double yMin;
        double yMax;
        double yMid;

        if (x <= 1) {
            yMin = x;
            yMax = 1;
            yMid = (yMin + yMax)/2;

            while ( (yMid*yMid) < epsilon ) {

            }
        } else {
            yMin = 1;
            yMax = x;
            yMid = (yMin + yMax)/2;

            while ( (yMid*yMid) < epsilon ) {

            }
        }

        return yMid;
    }

    // Calculates square root of x with fault tolerance epsilon, using recursion
    public static double mySqrtRecurse(double x, double epsilon) {
        if (x < 0) {
            return Double.NaN; // if negative argument there's no square root; return NaN
        }

        double yMin;
        double yMax;
        double yMid;

        if (x <= 1) {
            yMin = x;
            yMax = 1;
            yMid = (yMin + yMax)/2;

            return 0;
        } else {
            yMin = 1;
            yMax = x;
            yMid = (yMin + yMax)/2;

            return 0;
        }
    }

}
