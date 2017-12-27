import java.awt.Color;
import java.awt.Point;
import java.util.*;

public class ConvexPolygon implements Shape
{
    private Point[] vertices;
    private Color color;

    public ConvexPolygon(Point[] vertices, Color color){
        this.vertices = vertices;
        this.color = color;
    }

    public Point getVertex(int i){
        return this.vertices[i];
    }

    public Color getColor(){
        return this.color;
    }

    public void setColor(Color color){
        this.color = color;
    }

    public double getArea(){
        double a = 0;
        double ox = this.vertices[0].getX();
        double oy = this.vertices[0].getY();

        a += (this.vertices[this.vertices.length - 1].getX() + ox) * (this.vertices[this.vertices.length - 1].getY() - oy);


        for (Point point: this.vertices) {
            a += (ox + point.getX()) * (oy - point.getY());
            ox = point.getX();
            oy = point.getY();
        }

        return -(a/2);
    }

    public double getPerimeter(){
        double p = 0;
        double ox = this.vertices[0].getX();
        double oy = this.vertices[0].getY();

        p += Math.sqrt(Math.pow((this.vertices[this.vertices.length - 1].getX() - ox), 2) +
                Math.pow((this.vertices[this.vertices.length - 1].getY() - oy), 2));


        for (Point point: this.vertices){
            p += Math.sqrt(Math.pow((point.getX() - ox), 2) + Math.pow((point.getY() - oy), 2));
            ox = point.getX();
            oy = point.getY();
        }
        return p;
    }

    public void translate(Point point){
        Point[] newVertices = new Point[this.vertices.length];

        for (int i = 0; i < this.vertices.length; i++){
            Point newP = new Point((int)(this.vertices[i].getX() + point.getX()),
                    (int)(this.vertices[i].getY() + point.getY()));
            newVertices[i] = newP;
        }
        this.vertices = newVertices;
    }
}