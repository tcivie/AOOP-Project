package zoo;


import animals.Animal;
import animals.Lion;

import java.io.File;
import java.io.FilenameFilter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Constructor;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class main {
    private static final int MIN_ARR_LEN = 3;

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        double unknownNumber;
        Scanner sc = new Scanner(System.in);
        // GET NUMBER OF ANIMALS IN THE ZOO
        System.out.println("Please enter the number of animals in the zoo [Minimum :" + MIN_ARR_LEN + "] : ");
        int num = sc.nextInt();
        if (num < MIN_ARR_LEN) {
            System.out.println("Err: The minimum number of possible animals in the zoo is: " + MIN_ARR_LEN + " Setting the number of animals in the zoo to the minimum");
            num = MIN_ARR_LEN;
        }
        Animal[] zoo = new Animal[num];

        Class c;
        ClassLoader cl = ClassLoader.getSystemClassLoader();
        for (int i=0; i<zoo.length; i++) {
            System.out.println("What animal do you want?");
            String type = sc.next();
            System.out.println("how would you like to call it?");
            String name = sc.next();
            c = cl.loadClass("animals." + type);
            Constructor[] con = c.getConstructors();

            // gather all the args

            System.out.println("How many arguments are you going to give? ");
            int choiceInt = sc.nextInt();
            Object[] arr = new Object[choiceInt + 1];
            arr[0] = name;
            for (int k = 1; k < choiceInt + 1; k++) {
                System.out.println("Enter argument No " + k + ": ");
                String choice = sc.next();
                if (isNumeric(choice)) {
                    unknownNumber = Double.parseDouble(choice);
                    if (unknownNumber == (int) unknownNumber) {// Check if the number is int or double
                        int tempNum = (int) unknownNumber;
                        arr[k] = tempNum; //TODO: Fix castign so that there will be stored int and not Integer or find another approach to issue on line 127
                    } else {
                        double tempDouble = unknownNumber;
                        arr[k] = tempDouble;
                    }
                } else
                    arr[k] = choice;

            }

            for (Constructor ctor : con) {
                Class<?>[] pType = ctor.getParameterTypes();
                boolean match = true;
                int j = 0;
                for (; j < pType.length; j++) {
                    System.out.println(pType[j]);
                    System.out.println(arr[j].getClass());
                    if (!(((Object)pType[j]).getClass().isInstance(arr[j].getClass())) || pType.length != (choiceInt + 1)) { // Check if the parameters are of the same type
                        match = false;
                        break;
                    }
                }
                if (match) { // Found the right ctor
                    zoo[i] = (Animal) ctor.newInstance(arr);
                    break;
                }
            }
        }
    }

    public static boolean isNumeric(String string) {
        double intValue;
        if(string == null || string.equals("")) {
            return false;
        }

        try {
            intValue = Double.parseDouble(string);
            return true;
        } catch (NumberFormatException e) {
            System.out.println("Input String cannot be parsed to Integer.");
        }
        return false;
    }
}
