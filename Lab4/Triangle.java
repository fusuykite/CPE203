import java.awt.Color;
import java.awt.Point;
import java.util.*;

public class Triangle implements Shape {
    private Point v1;
    private Point v2;
    private Point v3;
    private Color color;

    public Triangle(Point v1, Point v2, Point v3, Color color) {
        this.v1 = v1;
        this.v2 = v2;
        this.v3 = v3;
        this.color = color;
    }

    public Point getVertexA() {
        return this.v1;
    }

    public Point getVertexB() {
        return this.v2;
    }

    public Point getVertexC() {
        return this.v3;
    }

    public Color getColor() {
        return this.color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public double getArea() {
        double a = Math
                .sqrt(Math.pow((this.v2.getX() - this.v1.getX()), 2) + Math.pow((this.v2.getY() - this.v1.getY()), 2));
        double b = Math
                .sqrt(Math.pow((this.v3.getX() - this.v2.getX()), 2) + Math.pow((this.v3.getY() - this.v2.getY()), 2));
        double c = Math
                .sqrt(Math.pow((this.v3.getX() - this.v1.getX()), 2) + Math.pow((this.v3.getY() - this.v1.getY()), 2));

        //Heron's Formula
        double s = 0.5 * (a + b + c);
        double area = Math.sqrt(s * (s - a) * (s - b) * (s - c));
        return area;
    }

    public double getPerimeter() {
        double a = Math
                .sqrt(Math.pow((this.v2.getX() - this.v1.getX()), 2) + Math.pow((this.v2.getY() - this.v1.getY()), 2));
        double b = Math
                .sqrt(Math.pow((this.v3.getX() - this.v2.getX()), 2) + Math.pow((this.v3.getY() - this.v2.getY()), 2));
        double c = Math
                .sqrt(Math.pow((this.v3.getX() - this.v1.getX()), 2) + Math.pow((this.v3.getY() - this.v1.getY()), 2));

        double perim = a + b + c;
        return perim;
    }

    public void translate(Point point) {
        Point newV1 = new Point((int) (this.v1.getX() + point.getX()), (int) (this.v1.getY() + point.getY()));
        Point newV2 = new Point((int) (this.v2.getX() + point.getX()), (int) (this.v2.getY() + point.getY()));
        Point newV3 = new Point((int) (this.v3.getX() + point.getX()), (int) (this.v3.getY() + point.getY()));

        this.v1 = newV1;
        this.v2 = newV2;
        this.v3 = newV3;
    }
}