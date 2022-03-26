package animals;

import mobility.Point;

public class Bear extends Animal{

    private String furColor;

    public Bear(String name) {
        super(name, new Point(100,5));
        this.furColor = "GRAY";
    }

    public Bear(String name, String color) {
        super(name, new Point(100,5));
        this.furColor = color;
    }

    @Override
    public void setWeight(int i) {

    }

    @Override
    public void setName(String name) {

    }

    public void setFurColor(String color) {
        this.furColor = color;
    }

    public void roar() {

    }
}
