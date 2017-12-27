public class Rectangle {
    private final Point topLeft;
    private final Point bottomRight;

    public Rectangle(Point topLeft, Point bottomRight) {
        this.topLeft = topLeft;
        this.bottomRight = bottomRight;
    }

    public Point getTopLeft() {
        return topLeft;
    }

    public Point getBottomRight() {
        return bottomRight;
    }

    public double perimeter() {

        Point topleft = getTopLeft();
        Point bottomRight = getBottomRight();

        double horizontalDistance = Math.abs(topLeft.getX() - bottomRight.getX());

        double verticalDistance = Math.abs(topLeft.getY() - bottomRight.getY());

        return (2 * horizontalDistance) + (2 * verticalDistance);
    }

}
