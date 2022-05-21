package mobility;

/**
 * @author Gleb Tcivie & Orel Dandeker
 * @Date 5/4/22
 */
public class Point {
    private int x;
    private int y;

    /** CONST DIMENSIONS **/
    public static final int MAX_X = 800;
    public static final int MIN_X = 0;
    public static final int MAX_Y = 600;
    public static final int MIN_Y = 0;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Point(Point point) {
        this.x = point.getX();
        this.y = point.getY();
    }

    @Deprecated
    public static boolean checkBounderies(Point newLocation) {
        return (newLocation.x <= MAX_X && newLocation.x >= MIN_X
                && newLocation.y <= MAX_Y && newLocation.y >= MIN_Y);
    }

    /**
     * Checks if we are out of boundries with the padding
     * @param x X coordinate
     * @param y Y coordinate
     * @param padding The padding to check
     * @return True if in bounds
     */
    public static boolean checkBounderies(int x, int y, int padding) {
        return (x + padding <= MAX_X && x - padding >= MIN_X
                && y + padding <= MAX_Y && y - padding >= MIN_Y);
    }

//
//    /**
//     * Checks if the object is out of bounds and returns the location at which the error occurs
//     * [ 1 2 3 ]
//     * [ 4 5 6 ]
//     * [ 7 8 9 ]
//     * @param x X coordinate
//     * @param y Y coordinate
//     * @return int location from the matrix above
//     */
//    public static int checkBounderies(int x, int y) {
//        if (x <= MIN_X && y <= MIN_Y)
//            return 1;
//        if (x >= MIN_X && x <= MAX_X && y <= MIN_Y)
//            return 2;
//        if (x >= MAX_X && y <= MIN_Y)
//            return 3;
//        if (x <= MIN_X && y<= MAX_Y)
//            return 4;
//        if (x >= MIN_X && x <= MAX_X && y <= MAX_Y)
//            return 5;
//        if (x >= MAX_X && y <= MAX_Y)
//            return 6;
//        if (x <= MIN_X)
//            return 7;
//        if (x <= MAX_X)
//            return 8;
//        else
//            return 9;
//    }

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
