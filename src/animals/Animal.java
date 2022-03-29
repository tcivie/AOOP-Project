package animals;

import diet.IDiet;
import food.EFoodType;
import food.IEdible;
import mobility.Mobile;
import mobility.Point;
import utilities.MessageUtility;

import java.util.function.Function;

public abstract class Animal extends Mobile implements IEdible {

    private String name;
    private double weight;
    private IDiet diet;

    public Animal(String name, Point point) {
        super(point);
        MessageUtility.logConstractor("Animal", name);
        setName(name);
    }

    public void makeSound() {
        // TODO: add get sound method to each of the animals and make them include them all. (Maybe add interface or add method in the animal abstract class)
    }

    /**
     * Gets the food type
     *
     * @return type of food from the Enum
     */
    public EFoodType getFoodtype() {
        fireLog("logGetter", "getFoodType", EFoodType.MEAT);
        return EFoodType.MEAT;
    }

    public double getWeight() {
        fireLog("logGetter", "getWeight", this.weight);
        return this.weight;
    }

    public boolean setWeight(double weight) {
        boolean isSuccess = (weight >= 0);
        if (isSuccess)
            this.weight = weight;
        else
            this.weight = 0;
        fireLog("logSetter", "setWeight", weight, isSuccess);
        return isSuccess;
    }

    public IDiet getDiet() {
        fireLog("logGetter", "getDiet", this.diet);
        return this.diet;
    }

    public boolean setDiet(IDiet diet) {
        this.diet = diet;
        fireLog("logSetter", "setDiet", diet, true);
        return true; //TODO: Check if there is some thing to check here
    }

    public boolean setName(String name) {
        this.name = name;
        fireLog("logSetter", "setName", name, true);
        return true;
    }

    public String getName() {
        fireLog("logGetter", "getName", this.name);
        return this.name;
    }

    public boolean eat(IEdible food) {
        boolean isSuccess = (this.diet.canEat(food.getFoodtype()));
        if (isSuccess)
            this.weight = this.diet.eat(this,food);
        fireLog("logBooleanFunction", "eat", food, isSuccess);
        return true;
    }

    @Override
    public String toString() {
        return "[" + this.getClass().getSimpleName() + "] ";
    }

    public void fireLog(String methodName , String funcName, Object value, boolean isSuccess) {
        switch (methodName) {
            case "logBooleanFunction":
                MessageUtility.logBooleanFunction(this.name, funcName, value, isSuccess);
                break;
            case "logSetter":
                MessageUtility.logSetter(this.name, funcName, value, isSuccess);
                break;
        }
    }

    public void fireLog(String methodName , String funcName, Object value) {
        switch (methodName) {
            case "logGetter":
                MessageUtility.logGetter(this.name, funcName, value);
                break;
        }
    }

    public void fireLog(String methodName , String funcName) {
        switch (methodName) {
            case "logSound":
                MessageUtility.logSound(this.name,funcName);
                break;
        }
    }
}
