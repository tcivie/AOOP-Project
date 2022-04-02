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

    /**
     * Ctor
     * @param name Name of the bear
     */
    public Bear(String name) {
        super(name, new Point(100,5));
        MessageUtility.logConstractor("Bear", name);
        setWeight(308.2);
        setFurColor("GRAY");
        setDiet(new Omnivore());
    }

    /**
     * Ctor
     * @param name Name of the bear
     * @param color Color of the bear from the list of available colors : {"BLACK","WHITE","GRAY"}
     */
    public Bear(String name, String color) {
        super(name, new Point(100,5));
        MessageUtility.logConstractor("Bear", name);
        setWeight(308.2);
        setFurColor(color);
        setDiet(new Omnivore());
    }

    /**
     * Changes the fur color of the bear
     * @param color Color from the list of available colors {"BLACK","WHITE","GRAY"}
     * @return True if the operation was successful / False otherwise
     */
    public boolean setFurColor(String color) {
        boolean isSuccess = (Arrays.asList(COLORS).contains(color));
        if (isSuccess)
            this.furColor = color;
        else
            this.furColor = "GRAY";
        fireLog("logSetter","setFurColor",color,isSuccess);
        return isSuccess;
    }

    /**
     * Returns the current fur color
     * @return Fur color of the bear
     */
    public String getFurColor() {
        fireLog("logGetter", "getFurColor", this.furColor);
        return this.furColor;
    }


    @Override
    public void makeSound() {
        roar();
    } //TODO: find better approach

    /**
     * Plays the roar sound with the help of the Firelog method in Animal
     */
    public void roar() {
        fireLog("logSound","Stands on its hind legs, roars and scratches its belly");
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return super.toString();
    }
}
