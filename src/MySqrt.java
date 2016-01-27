public class MySqrt {

    public static double TOLERANCE = 0.000001; // fault tolerance constant

    public static void main(String[] args) {
        System.out.println("Running tests for loop!");
        System.out.println("Should be around 0.316, answer is: " + mySqrtLoop(0.1, TOLERANCE));
        System.out.println("Should be around 0.707, answer is: " + mySqrtLoop(0.5, TOLERANCE));
        System.out.println("Should be around 1, answer is: " + mySqrtLoop(1, TOLERANCE));
        System.out.println("Should be around 1.414, answer is: " + mySqrtLoop(2, TOLERANCE));
        System.out.println("Should be around 3, answer is: " + mySqrtLoop(9, TOLERANCE));
        System.out.println("Should return Double.NaN, answer is: " + mySqrtLoop(-2, TOLERANCE));
        System.out.println("DONE with tests for loop!");

        System.out.println("***************");

        System.out.println("Running tests for recursion!");
        System.out.println("Should be around 0.316, answer is: " + mySqrtRecurse(0.1, TOLERANCE));
        System.out.println("Should be around 0.707, answer is: " + mySqrtRecurse(0.5, TOLERANCE));
        System.out.println("Should be around 1, answer is: " + mySqrtRecurse(1, TOLERANCE));
        System.out.println("Should be around 1.414, answer is: " + mySqrtRecurse(2, TOLERANCE));
        System.out.println("Should be around 3, answer is: " + mySqrtRecurse(9, TOLERANCE));
        System.out.println("Should return Double.NaN, answer is: " + mySqrtRecurse(-2, TOLERANCE));
        System.out.println("DONE with tests for recursion!");
    }

    // Calculates square root of x with fault tolerance epsilon, using a loop
    public static double mySqrtLoop(double x, double epsilon) {
        if (x < 0) {
            return Double.NaN; // if negative argument there's no square root; return NaN
        }

        double yMin;
        double yMax;
        double yMid;
        double ySquare;

        if (x <= 1) {
            yMin = x;
            yMax = 1;
        } else {
            yMin = 1;
            yMax = x;
        }

        yMid = (yMin + yMax)/2;
        ySquare = yMid*yMid;

        while ( ySquare > (x + epsilon) || ySquare < (x - epsilon) ) {
            if ( ySquare > (x + epsilon) ) {
                yMax = yMid;
            } else {
                yMin = yMid;
            }
            yMid = (yMin + yMax)/2;
            ySquare = yMid*yMid;
        }

        return yMid;
    }

    // Calculates square root of x with fault tolerance epsilon, using recursion
    public static double mySqrtRecurse(double x, double epsilon) {
        if (x < 0) {
            return Double.NaN; // if negative argument there's no square root; return NaN
        } else if (x <= 1) {
            return mySqrtRecurseCalc(x, epsilon, x, 1);
        } else {
            return mySqrtRecurseCalc(x, epsilon, 1, x);
        }
    }

    public static double mySqrtRecurseCalc(double x, double epsilon, double yMin, double yMax) {
        double yMid = (yMax+yMin)/2;
        double ySquare = yMid * yMid;

        if ( ySquare > (x + epsilon) || ySquare < (x - epsilon) ) {
            if ( ySquare > (x + epsilon) ) {
                return mySqrtRecurseCalc(x, epsilon, yMin, yMid);
            } else {
                return mySqrtRecurseCalc(x, epsilon, yMid, yMax);
            }
        } else {
            return yMid;
        }
    }

}
