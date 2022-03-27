package animals;

import diet.Carnivore;
import diet.Herbivore;
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
        this.furColor = "GRAY";
        setDiet(new Carnivore());
    }

    public Bear(String name, String color) {
        super(name, new Point(100,5));
        MessageUtility.logConstractor("Bear", name);
        this.furColor = color;
        setDiet(new Carnivore());
    }

    public boolean setFurColor(String color) {
        boolean isSuccess = (Arrays.asList(COLORS).contains(color));
        if (isSuccess)
            this.furColor = color;
        else
            this.furColor = "GRAY";
        MessageUtility.logSetter(this.getClass().getSimpleName(), "setFurColor", color, isSuccess);
        return isSuccess;
    }

    public String getFurColor() {
        MessageUtility.logGetter(this.getClass().getSimpleName(), "getFurColor", this.furColor);
        return this.furColor;
    }

    public void roar() {

    }

    /**
     * Gets the food type
     *
     * @return type of food from the Enum
     */
    @Override
    public EFoodType getFoodtype() {
        return null;
    }
}
