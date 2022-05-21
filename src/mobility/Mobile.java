package mobility;
import java.lang.Math;

/**
 * @author Gleb Tcivie & Orel Dandeker
 * @Date 5/4/22
 */
public abstract class Mobile implements ILocatable {


    private Point location; // Current location
    private double totalDistance; // Distance the object traveled, [>0]

    /**
     * Ctor
     * @param point New point for the mobility
     */
    public Mobile(Point point) {
        this.location = point;
        this.totalDistance = 0;
    }

    /**
     * Adds to total distance that the animal traveled
     * @param dist Distance to add
     */
    public void addTotalDistance(double dist) {
        this.totalDistance += dist;
    }

    /**
     * Calculate distance between two points
     * @param point The second point to check
     * @return The distance that calculated
     */
    public double calcDistance(Point point) {
        double result = Math.sqrt(Math.pow(this.location.getX() - point.getX(),2) + Math.pow(this.location.getY() - point.getY(),2));
        return Math.round(result * 100) / 100.;
    }

    /**
     * Calculate distance between two points
     * @param x The x coord to check
     * @param y The Y coord to check
     * @return The distance that calculated
     */
    public double calcDistance(int x, int y) {
        double result = Math.sqrt(Math.pow(this.location.getX() - x,2) + Math.pow(this.location.getY() - y,2));
        return Math.round(result * 100) / 100.;
    }

    public abstract double move(Point point);

    /**
     * Gets the location of the animal
     * @return current location of the animal
     */
    @Override
    public Point getLocation() {
        return this.location;
    }

    /**
     * Sets a new location for the animal with the given point
     * @param point New location to set for the animal
     * @return True if the operation was successful / False otherwise
     */
    @Override
    public void setLocation(Point point) {
        this.location = point;
    }

    /**
     * Sets a new location for the animal with the given point
     * @param x New x coord for the animal
     * @param y New y coord for the animal
     */
    public void setLocation(int x, int y) {
        this.location.setX(x);
        this.location.setY(y);
    }
}
