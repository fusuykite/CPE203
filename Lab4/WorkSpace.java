import org.w3c.dom.css.Rect;

import java.awt.Color;
import java.awt.Point;
import java.util.*;

public class WorkSpace {
    private ArrayList<Shape> objects;

    public WorkSpace() {
        this.objects = new ArrayList<Shape>();
    }

    public void add(Shape shape) {
        this.objects.add(shape);
    }

    public Shape get(int index) {
        return this.objects.get(index);
    }

    public int size() {
        return this.objects.size();
    }

    public List<Circle> getCircles() {
        ArrayList<Circle> circles = new ArrayList<Circle>();

        for (Shape object : objects) {
            if (object instanceof Circle) {
                circles.add((Circle) object);
            }
        }
        return circles;
    }

    public List<Rectangle> getRectangles() {
        ArrayList<Rectangle> rectangles = new ArrayList<Rectangle>();

        for (Shape object : objects) {
            if (object instanceof Rectangle) {
                rectangles.add((Rectangle) object);
            }
        }
        return rectangles;
    }

    public List<Triangle> getTriangles() {
        ArrayList<Triangle> triangles = new ArrayList<Triangle>();

        for (Shape object : objects) {
            if (object instanceof Triangle) {
                triangles.add((Triangle) object);
            }
        }
        return triangles;
    }

    public List<ConvexPolygon> getConvexPolygons() {
        ArrayList<ConvexPolygon> convexPolygons = new ArrayList<ConvexPolygon>();

        for (Shape object : objects) {
            if (object instanceof ConvexPolygon) {
                convexPolygons.add((ConvexPolygon) object);
            }
        }
        return convexPolygons;
    }

    public List<Shape> getShapesByColor(Color color) {
        ArrayList<Shape> shapes = new ArrayList<Shape>();

        for (Shape object : objects) {
            Color colorObj = object.getColor();
            if (color == colorObj) {
                shapes.add(object);
            }
        }
        return shapes;
    }

    public double getAreaOfAllShapes(){
        double sumArea = 0;
        for (Shape object : objects){
            sumArea += object.getArea();
        }
        return sumArea;
    }

    public double getPerimeterOfAllShapes(){
        double sumPerim = 0;
        for (Shape object : objects){
            sumPerim += object.getPerimeter();
        }
        return sumPerim;
    }
}