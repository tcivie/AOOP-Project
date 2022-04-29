package animals;

import diet.Carnivore;
import food.EFoodType;
import food.IEdible;
import mobility.Point;

import javax.swing.*;
import java.util.Random;

/**
 * @author Gleb Tcivie & Orel Dandeker
 * @Date 5/4/22
 */
public class Lion extends Animal {

    private int scarCount;
    private static final String PICTURE_PATH = "src/graphics/assignment2_pictures/lio_n_1.png";

    /**
     * Ctor
     * @param name Lions name
     */
    public Lion(String name) {
        super(name, new Point(20,0));
        setWeight(408.20);
        setDiet(new Carnivore());
    }

    /**
     * Ctor
     * @param name Lions name
     * @param x x point
     * @param y y point
     */
    public Lion(String name, int x, int y) {
        super(name, new Point(x,y));
        setWeight(408.20);
        setDiet(new Carnivore());
    }

    /**
     * Gets the number of scars on the lion
     * @return
     */
    public int getScarCount() {
        return this.scarCount;
    }

    /**
     * Sets new scar for the lion
     * @param scarCount
     * @return
     */
    public boolean setScarCount(int scarCount) {
        boolean isSuccess = (scarCount >= 0);
        if (isSuccess)
            this.scarCount = scarCount;
        else
            this.scarCount = 0;
        return isSuccess;
    }

    @Override
    public void makeSound() {
        roar();
    } //TODO: find better approach

    /**
     * Plays the roar sound with the help of the Firelog method in Animal
     */
    public void roar() {
    }

    /**
     * Gets the food type
     * @return type of food from the Enum
     */
    @Override
    public EFoodType getFoodtype() {
        return EFoodType.NOTFOOD;
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

    /**
     * Eat method of the animal - checks and feeds the animal the relevant food
     * @param food What the animal will eat
     * @return True if the animal has been fed successfully / False otherwise
     */
    @Override
    public boolean eat(IEdible food) {
        boolean isSuccess = (this.getDiet().canEat(food.getFoodtype()));
        Random random = new Random();
        if (isSuccess) {
            if (random.nextInt(0,100) < 50)
                setScarCount(this.scarCount++);
            super.eat(food);
        }
        return isSuccess;
    }

    /**
     * Creates the card for the ctor of the animal
     * @return Card with the relevant data added
     */
    public static JPanel createCard() {
        return Animal.createPanel(PICTURE_PATH);
    }
}
