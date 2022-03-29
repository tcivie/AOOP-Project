package animals;

import diet.Carnivore;
import diet.Herbivore;
import food.EFoodType;
import mobility.Point;
import utilities.MessageUtility;

public class Turtle extends Animal {

    private int Age;

    public Turtle(String name) {
        super(name, new Point(80,0));
        this.Age = 1;
        setDiet(new Herbivore());
        MessageUtility.logConstractor("Turtle", name);
    }
    public Turtle(String name, int age) {
        super(name, new Point(80,0));
        this.Age = age;
        setDiet(new Herbivore());
        MessageUtility.logConstractor("Turtle", name);
    }

    public int getAge() {
        fireLog("logGetter", "getAge", this.Age);
        return Age;
    }

    public boolean setAge(int age) {
        boolean isSuccess = (0 <= age && age <= 500);
        if (isSuccess)
            this.Age = age;
        fireLog("logSetter", "setAge", age, isSuccess);
        return isSuccess;
    }

    public void chew() {

    }
}
