package animals;

import diet.Carnivore;
import food.EFoodType;
import food.IEdible;
import mobility.Point;
import utilities.MessageUtility;

import java.util.Random;

public class Lion extends Animal {
    private int scarCount;

    /**
     * Ctor
     * @param name Lions name
     */
    public Lion(String name) {
        super(name, new Point(20,0));
        MessageUtility.logConstractor("Lion", name);
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
        MessageUtility.logConstractor("Lion", name);
        setWeight(408.20);
        setDiet(new Carnivore());
    }

    /**
     * Gets the number of scars on the lion
     * @return
     */
    public int getScarCount() {
        fireLog("logGetter", "getScarCount", this.scarCount);
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
        fireLog("logSetter", "setScarCount", scarCount, isSuccess);
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
        fireLog("logSound","Roars, then stretches and shakes its mane");
    }

    /**
     * Gets the food type
     * @return type of food from the Enum
     */
    @Override
    public EFoodType getFoodtype() {
        fireLog("logGetter", "getFoodType", EFoodType.NOTFOOD);
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
            if (random.nextInt(100) < 50)
                setScarCount(this.scarCount++);
            super.eat(food);
        }
        fireLog("logBooleanFunction", "eat", food, isSuccess);
        return true;
    }
}
