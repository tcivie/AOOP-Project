package mobility;
import java.lang.Math;

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
    public boolean setLocation(Point point) {
        this.location = point;
        return true;
    }
}
