package animals;

import mobility.Point;

public class Elephant extends Animal{

    private double trunkLength;

    public Elephant(String name) {
        super(name, new Point(50,90));
        this.trunkLength = 1;
    }

    public Elephant(String name, double length) {
        super(name, new Point(50,90));
        this.trunkLength = length;
    }

    @Override
    public void setWeight(int i) {

    }

    @Override
    public void setName(String name) {

    }

    public void chew() {
        
    }

    public void settrunkLength(double length) {
        this.trunkLength = length;
    }
}
