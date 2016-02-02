import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Lab2a {
    public static double[] simplifyShape(double[] poly, int k) {
        ArrayList<Point2D> simpleList = new ArrayList();
        for (int i = 0; i < poly.length; i+=2) {
          simpleList.add(new Point2D.Double(poly[i], poly[i+1]));
        }

        while (simpleList.size() > k) {
            int i = 1;
            double dist = calcVal(simpleList.get(1), simpleList.get(0), simpleList.get(2));

            for (int j = 2; j < simpleList.size()-1; j++) {
                double newDist = calcVal(simpleList.get(j), simpleList.get(j-1), simpleList.get(j+1));
                if (newDist < dist) {
                    dist = newDist;
                    i = j;
                }
            }

            simpleList.remove(i);
        }

        double[] newArray = new double[simpleList.size()*2];
        for (int i = 0; i < simpleList.size(); i++) {
            newArray[i*2] = simpleList.get(i).getX();
            newArray[i*2+1] = simpleList.get(i).getY();
        }

        return newArray;
    }

    public static double calcVal(Point2D p, Point2D l, Point2D r) {
        return Point2D.distance(l.getX(), l.getY(),p.getX(), p.getY()) +
                Point2D.distance(p.getX(), p.getY(), r.getX(), r.getY()) -
                Point2D.distance(l.getX(), l.getY(), r.getX(), r.getY());
    }
}
