import java.awt.Color;
import java.awt.Point;
import java.util.*;

public class Rectangle implements Shape {
    private double width;
    private double height;
    private Point topLeftCorner;
    private Color color;

    public Rectangle(double width, double height, Point topLeftCorner, Color color) {
        this.width = width;
        this.height = height;
        this.topLeftCorner = topLeftCorner;
        this.color = color;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double newWidth) {
        this.width = newWidth;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double newHeight) {
        this.height = newHeight;
    }

    public Point getTopLeft() {
        return topLeftCorner;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color newColor) {
        this.color = newColor;
    }

    public double getArea() {
        double area = width * height;
        return area;
    }

    public double getPerimeter() {
        double perim = (2 * width) + (2 * height);
        return perim;
    }

    public void translate(Point point) {
        Point newTopLeft = new Point((int) (this.topLeftCorner.getX() + point.getX()),
                (int) (this.topLeftCorner.getY() + point.getY()));
        this.topLeftCorner = newTopLeft;
    }
}