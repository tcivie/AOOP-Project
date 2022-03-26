package animals;

import mobility.Point;

public class Giraffe extends Animal {

    private double newckLength;
    public Giraffe(String name) {
        super(name, new Point(50,0));
        this.newckLength = 1.5;
    }

    public Giraffe(String name, double length) {
        super(name, new Point(50,0));
        this.newckLength = length;
    }

    @Override
    public void setName(String name) {

    }

    public void setNeckLength(double length) {
        this.newckLength = length;
    }
}
