package mobility;

public class Point {
    private int x;
    private int y;

    // TODO: add the necessary requests
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static boolean cheackBounderies(Point newLocation) {
        // TODO: add implementation
        return true;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }
}
