import java.util.List;
import java.util.ArrayList;

public class Polygon {
    private final List<Point> points;

    public Polygon (List<Point> points)
    {
        this.points = points;
    }

    public List<Point> getPoints()

    {
        return points;
    }

    public double perimeter(){
        double totalPerimeter = 0;
        List<Point> points = getPoints();

        for (int i = 0; i < points.size(); i++){
            Point p1 = points.get(i);
            Point p2 = (i + 1 >= points.size()) ? points.get(0) : points.get(i+1);

            totalPerimeter += getDistance(p1, p2);
        }

        return totalPerimeter;
    }
    private static double getDistance(Point p1, Point p2){
        double xComp = p2.getX() - p1.getX();
        double yComp = p2.getY() - p1.getY();

        return Math.sqrt(Math.pow(xComp, 2.0) + Math.pow(yComp, 2.0));
    }
}
