package mobility;


public class Mobile implements ILocatable {
    private Point location; // Current location
    private double totalDistance; // Distance the object traveled, [>0]

    public Mobile() {
        // TODO: add ctor
    }

    public Mobile(Point point) {
        // TODO: add ctor
    }

    public void addTotalDistance(double dist) {
        // TODO: add method implementation
    }

    public double calcDistance(Point point) {
        // TODO: same as HW1
        return 0;
    }

    public double move(Point point) {
        // TODO: add implementation
        return 0;
    }

    @Override
    public Point getLocation() {
        // TODO: add implementation
        return null;
    }

    @Override
    public boolean setLocation(Point point) {
        // TODO: add implementation
        return false;
    }
}
