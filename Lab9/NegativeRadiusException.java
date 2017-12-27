
public class NegativeRadiusException extends CircleException {

    private double rad;

    public NegativeRadiusException(Double rad){
        super("negative radius");
        this.rad = rad;
    }

    public double radius(){
        return rad;
    }
}
