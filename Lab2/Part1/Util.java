import java.util.List;



public class Util {
    public static double perimeter(Circle circle){

        double r = circle.getRadius();

        return (Math.PI * 2 * r);
    }

    public static double perimeter(Rectangle rect){
        double horizontalDistance = Math.abs(rect.getTopLeft().getX() - rect.getBottomRight().getX());

        double verticalDistance = Math.abs(rect.getTopLeft().getY() - rect.getBottomRight().getY());

        return (2 * horizontalDistance ) + (2 * verticalDistance);
    }

    public static double perimeter(Polygon poly){

        double totalPerimeter = 0;
        List<Point> points = poly.getPoints();

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
