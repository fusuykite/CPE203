public class Point{
    private double x;
    private double y;
    private double z;

    public Point(double x, double y, double z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public String toString(){
        return ("Point(" + x + ", " + y + ", " + z + ")");
    }

    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }

    public double getZ() { return z; }

    public double getRadius(){
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2));
    }

    public double getAngle(){
        return Math.atan(z/(Math.sqrt(Math.pow(x, 2) + Math.pow(y,2))));
    }

    public Point translate(Point point) {
        return new Point((this.x + point.getX()),
                (this.y + point.getY()),
                (this.z + point.getZ()));
    }

    public Point scale(double scale){
        double newX = getX() * scale;
        double newY = getY() * scale;
        double newZ = getZ() * scale;

        return new Point(newX, newY, newZ);
    }

//    public Point rotate90(){
//        return new Point(-y1, x1);
//    }
}