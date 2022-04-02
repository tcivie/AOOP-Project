package mobility;
import java.lang.Math;

public abstract class Mobile implements ILocatable {


    private Point location; // Current location
    private double totalDistance; // Distance the object traveled, [>0]

    public Mobile(Point point) {
        this.location = point;
        this.totalDistance = 0;
    }

    public void addTotalDistance(double dist) {
        this.totalDistance += dist;
    }

    public double calcDistance(Point point) {
        double result = Math.sqrt(Math.pow(this.location.getX() - point.getX(),2) + Math.pow(this.location.getY() - point.getY(),2));
        return Math.round(result * 100) / 100.;
    }

    public abstract double move(Point point);

    @Override
    public Point getLocation() {
        return this.location;
    }

    @Override
    public boolean setLocation(Point point) {
        this.location = point;
        return true;
    }
}
