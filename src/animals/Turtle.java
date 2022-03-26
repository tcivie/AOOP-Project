package animals;

import mobility.Point;

public class Turtle extends Animal {

    private int Age;
    public Turtle(String name) {
        super(name, new Point(80,0));
        this.Age = 1;
    }

    public Turtle(String name, int age) {
        super(name, new Point(80,0));
        this.Age = age;
    }

    @Override
    public void setName(String name) {

    }

    public void setAge(int age) {
        this.Age = age;
    }

    public void chew() {

    }
}
