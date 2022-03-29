package animals;

import diet.Carnivore;
import diet.Herbivore;
import diet.Omnivore;
import food.EFoodType;
import mobility.Point;
import utilities.MessageUtility;

import java.util.Arrays;

public class Bear extends Animal{

    private String furColor;
    private static String[] COLORS = {"BLACK","WHITE","GRAY"};

    public Bear(String name) {
        super(name, new Point(100,5));
        MessageUtility.logConstractor("Bear", name);
        setWeight(308.2);
        setFurColor("GRAY");
        setDiet(new Omnivore());
    }

    public Bear(String name, String color) {
        super(name, new Point(100,5));
        MessageUtility.logConstractor("Bear", name);
        setWeight(308.2);
        setFurColor(color);
        setDiet(new Omnivore());
    }

    public boolean setFurColor(String color) {
        boolean isSuccess = (Arrays.asList(COLORS).contains(color));
        if (isSuccess)
            this.furColor = color;
        else
            this.furColor = "GRAY";
        fireLog("logSetter","setFurColor",color,isSuccess);
        return isSuccess;
    }

    public String getFurColor() {
        fireLog("logGetter", "getFurColor", this.furColor);
        return this.furColor;
    }

    @Override
    public void makeSound() {
        roar();
    } //TODO: find better approach

    public void roar() {
        fireLog("logSound","Stands on its hind legs, roars and scratches its belly");
    }
}
