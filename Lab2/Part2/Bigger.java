public class Bigger {

    public static double whichIsBigger(Circle circle, Rectangle rectangle, Polygon polygon){

        double circlePeri = circle.perimeter();
        double rectPeri = rectangle.perimeter();
        double polyPeri = polygon.perimeter();

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

