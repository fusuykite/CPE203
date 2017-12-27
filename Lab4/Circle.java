import java.awt.Color;
import java.awt.Point;
import java.util.*;

public class Circle implements Shape {
    private double radius;
    private Point center;
    private Color color;

    public Circle(double radius, Point center, Color color) {
        this.radius = radius;
        this.center = center;
        this.color = color;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double newRad) {
        this.radius = newRad;
    }

    public Point getCenter() {
        return center;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color newColor) {
        this.color = newColor;
    }

    public double getArea() {
        double area = Math.PI * Math.pow(radius, 2);
        return area;
    }

    public double getPerimeter() {
        double perim = 2 * Math.PI * radius;
        return perim;
    }

    public void translate(Point point) {
        Point newCenter = new Point((int) (this.center.getX() + point.getX()),
                (int) (this.center.getY() + point.getY()));
        this.center = newCenter;
    }
}
