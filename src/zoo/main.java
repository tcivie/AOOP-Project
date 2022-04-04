package zoo;


import animals.Animal;
import animals.Lion;
import mobility.Point;

import java.io.File;
import java.io.FilenameFilter;
import java.lang.reflect.*;
import java.net.URL;
import java.util.*;

public class main {
    private static final int MIN_ARR_LEN = 3;

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        double unknownNumber;
        // HELPER DICTIONARY (PRIMITIVE TO WRAPPER) TODO: FIND DIFFERENT APPROACH LATER
        Dictionary dict = new Hashtable();
        dict.put("char", "Character");
        dict.put("byte", "Byte");
        dict.put("short", "Short");
        dict.put("int", "Integer");
        dict.put("long", "Long");
        dict.put("float", "Float");
        dict.put("double", "Double");
        dict.put("boolean", "Boolean");



        Scanner sc = new Scanner(System.in);
        // GET NUMBER OF ANIMALS IN THE ZOO
        System.out.println("Please enter the number of animals in the zoo [Minimum :" + MIN_ARR_LEN + "] : ");
        int num = sc.nextInt();
        if (num < MIN_ARR_LEN) {
            System.out.println("Err: The minimum number of possible animals in the zoo is: " + MIN_ARR_LEN + " Setting the number of animals in the zoo to the minimum");
            num = MIN_ARR_LEN;
        }
        Animal[] zoo = new Animal[num];

        // GET THE CLASS NAME AND THE NAME OF THE ANIMAL
        Class c;
        ClassLoader cl = ClassLoader.getSystemClassLoader();
        for (int i=0; i<zoo.length; i++) {
            System.out.println("What animal do you want?");
            String type = sc.next();
            System.out.println("how would you like to call it?");
            String name = sc.next();
            c = cl.loadClass("animals." + type);
            Constructor[] con = c.getConstructors();

            // TODO: Print the ctors and their arguments for the user to know what are the options

            // GATHER THE ARGUMENTS FOR THE CLASS
            System.out.println("How many arguments are you going to give? ");
            int choiceInt = sc.nextInt();
            Object[] arr = new Object[choiceInt + 1];
            arr[0] = name;
            for (int k = 1; k < choiceInt + 1; k++) {
                System.out.println("Enter argument No " + k + ": ");
                String choice = sc.next();

                // CHANGE THE STRING TO THE RIGHT TYPE
                if (isNumeric(choice)) {
                    unknownNumber = Double.parseDouble(choice);
                    if (unknownNumber == (int) unknownNumber) {// Check if the number is int or double
                        int tempNum = (int) unknownNumber;
                        arr[k] = tempNum; //TODO: Fix casting so that there will be stored int and not Integer or find another approach to issue on line 127
                    } else {
                        double tempDouble = unknownNumber;
                        arr[k] = tempDouble;
                    }
                } else
                    arr[k] = choice;
            }

            // SEARCH FOR THE RIGHT CTOR
            boolean match = false;
            for (Constructor ctor : con) {
                Class<?>[] pType = ctor.getParameterTypes();
                int j = 1;
                Field[] fields = ctor.getClass().getDeclaredFields();
                for (; j < pType.length; j++) {
                    match = true;
                    System.out.println(pType[j].getSimpleName());
                    System.out.println(arr[j].getClass().getSimpleName());

                    try { // USE THE DICTIONARY TO CHECK IF THE TYPE IS PRIMITIVE
                        if (!(dict.get(pType[j].getSimpleName()).equals(arr[j].getClass().getSimpleName()))||
                                pType.length != (choiceInt + 1)) { // Check if the parameters are of the same type
                            match = false;
                            break;
                        }
                    } catch (NullPointerException e) {
                        if (!(pType[j].getSimpleName().equals(arr[j].getClass().getSimpleName()))||
                                pType.length != (choiceInt + 1)) { // Check if the parameters are of the same type
                            match = false;
                            break;
                        }
                    }
                }
                if (match) { // Found the right ctor
                    zoo[i] = (Animal) ctor.newInstance(arr);
                    break;
                }
            }
            if (!match) {
                System.out.println("No Ctor found for the given parameters. Please try again.");
                i--;
            }
        }
        System.out.println("Animals created successfully\nPrinting all the animals with their indexes:");
        while (true) {
            for (int i = 0; i < zoo.length; i++) {
                System.out.println("[" + i + "]\t" + zoo[i]);
            }
            System.out.println("Chose animal you want to move [-1] for none");
            num = sc.nextInt();
            if (num == -1)
                break; // EXIT LOOP

            System.out.println(zoo[num] + " Current location: " + zoo[num].getLocation() + "\n" +
                    "Where do you want the animal to go? [X coordinate] :");
            int x = sc.nextInt();
            System.out.println("[Y coordinate] :");
            int y = sc.nextInt();
            if (!ZooActions.move(zoo[num],new Point(x,y)))
                System.out.println("Error: Couldn't move the animal to that point, Please try again");
        }
        System.out.println("Choosing two random animals from the list");
        for (int i = 0; i < Math.floor(zoo.length/2.); i++) {
            Random random = new Random();
            int a = random.ints(zoo.length).findFirst().getAsInt();
            int b;
            do {
                b = random.ints(zoo.length).findFirst().getAsInt();
            } while (b == a);
            ZooActions.eat(zoo[a],zoo[b]);
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
