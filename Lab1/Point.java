public class Point{
    private double x1;
    private double y1;

    public Point(double x, double y){
        x1 = x;
        y1 = y; 
    }

    public double getX(){
        return x1;
    }

    public double getY(){
        return y1;
    }

    public double getRadius(){
        double ans = Math.sqrt(Math.pow(x1, 2) + Math.pow(y1, 2));
        return ans;
    }

    public double getAngle(){
        double ans =  Math.atan2(y1,x1);
        return ans;
    }

    public Point rotate90(){
		return new Point (y1 * -1, x1);
		}
	
}
