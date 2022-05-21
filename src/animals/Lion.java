package animals;

import diet.Carnivore;
import food.EFoodType;
import food.IEdible;
import mobility.Point;

import java.io.IOException;
import java.util.Random;

/**
 * @author Gleb Tcivie & Orel Dandeker
 * @Date 5/4/22
 */
public class Lion extends Animal {

    private int scarCount;
    private static final String PICTURE_PATH = "src/graphics/assignment2_pictures/lio_n_1.png";
    private static final String PICTURE_PATH2 = "src/graphics/assignment2_pictures/lio_n_2.png";

    /**
     * Ctor
     * @param name Lions name
     * @param x x point
     * @param y y point
     */
    public Lion(String name, int x, int y, int size, String col, int horSpeed, int verSpeed, double weight) throws IOException {
        super(name, new Point(x,y), size, col, horSpeed, verSpeed, convertFromFilename(PICTURE_PATH), convertFromFilename(PICTURE_PATH2), weight);
//        MessageUtility.logConstractor("Lion", name);
        setWeight(weight);
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
    @Deprecated
    public void makeSound() {
        roar();
    } //TODO: find better approach

    /**
     * Plays the roar sound with the help of the Firelog method in Animal
     */
    @Deprecated
    public void roar() {
        fireLog("logSound","Roars, then stretches and shakes its mane");
    }

    /**
     * Gets the food type
     * @return type of food from the Enum
     */
    @Override
    public EFoodType getFoodtype() {
        return EFoodType.NOTFOOD;
    }

    /**
     * Eat method of the animal - checks and feeds the animal the relevant food
     * @param food What the animal will eat
     * @return True if the animal has been fed successfully / False otherwise
     */
    @Override
    public boolean eat(IEdible food) {
        boolean isSuccess = ((this.getDiet().canEat(food.getFoodtype())) &&
                (getWeight() >= food.getWeight() * 2) && (getSize() > calcDistance(food.getLocation())));
        Random random = new Random();
        if (isSuccess) {
            if (random.nextInt(0,100) < 50)
                setScarCount(++this.scarCount);
            super.eat(food);
        }
        return isSuccess;
    }

    public static String getPATH() {
        return PICTURE_PATH;
    }


}
