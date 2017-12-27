public class Bigger {

    public static double whichIsBigger(Circle circle, Rectangle rectangle, Polygon polygon){

        double circlePeri = Util.perimeter(circle);
        double rectPeri = Util.perimeter(rectangle);
        double polyPeri = Util.perimeter(polygon);

        if (circlePeri > rectPeri){

            if (circlePeri > polyPeri){
                return circlePeri;

            }

        } else if (rectPeri > polyPeri){
            return rectPeri;
        }

        return polyPeri;
    }

}


