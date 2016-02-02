/**
 * Created by Bondi on 2016-02-02.
 */
public class Point {
    /** The contents of the node is public */
    private double x, y, val;

    Point() {
        this(0, 0);
    }

    Point(double x, double y, double val) {
        this.x = x;
        this.y = y;
        this.val = val;
    }

    Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getVal() {
        return val;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setVal(double val) {
        this.val = val;
    }
}