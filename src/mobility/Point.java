package mobility;

/**
 * @author Gleb Tcivie
 * @Date 5/4/22
 */
public class Point {
    private int x;
    private int y;

    /** CONST DIMENSIONS **/
    private static final int MAX_X = 800;
    private static final int MIN_X = 0;
    private static final int MAX_Y = 600;
    private static final int MIN_Y = 0;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Point(Point point) {
        this.x = point.getX();
        this.y = point.getY();
    }

    public static boolean checkBounderies(Point newLocation) {
        return (newLocation.x <= MAX_X && newLocation.x >= MIN_X
                && newLocation.y <= MAX_Y && newLocation.y >= MIN_Y);
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        if (y <= MAX_Y && y >= MIN_Y)
            this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        if (x <= MAX_X && x >= MIN_X)
            this.x = x;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "(" + this.x + "," + this.y + ")";
    }
}
