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

//        // DISPLAY AVAILABLE ANIMALS IN THE PACKAGE ANIMAL
//        String packageName = "animals";
//        List<Class<Animal>> commands = new ArrayList<Class<Animal>>(); // Create empty list of
//        URL root = Thread.currentThread().getContextClassLoader().getResource(packageName.replace(".", "/")); // Get the resources that are currently running on the thread
//
//        // Filter .class files.
//        File[] files = new File(root.getFile()).listFiles(new FilenameFilter() {
//            public boolean accept(File dir, String name) {
//                return name.endsWith(".class");
//            }
//        });
//
//        // Find classes implementing ICommand.
//        for (File file : files) {
//            String className = file.getName().replaceAll(".class$", "");
//            Class<?> cls = Class.forName(packageName + "." + className);
//            if (Animal.class.isAssignableFrom(cls)) {
//                commands.add((Class<Animal>) cls);
//                System.out.println(cls);
//            }
//        }

//        Class c;
//        ClassLoader cl = ClassLoader.getSystemClassLoader();
//        for (int i=0; i<zoo.length; i++) {
//            System.out.println("What animal do you want?");
//            String type = sc.next();
//            System.out.println("how would you like to call it?");
//            String name = sc.next();
//            c = cl.loadClass("animals." + type);
//            Constructor[] con = c.getConstructors();
//
//            System.out.println("Do you want to add additional parameters? [y/N]?");
//            String choice = sc.next();
//            if (choice.equals("y") || choice.equals("Y")) {
//                switch (type) {
//                    case "Bear":
//                            System.out.println("Please select fur color from the following list: [BLACK,WHITE,GRAY]");
//                            choice = sc.next();
//                            zoo[i] = (Animal) con[1].newInstance(name,new );
//                        break;
//                    case "Elephant":
//                        break;
//                    case "Giraffe":
//                        break;
//                    case "Lion":
//                        break;
//                    case "Turtle":
//                        break;
//                    default:
//                        break;
//                }
//            }
//            zoo[i] = (Animal) con[0].newInstance(name);
//        }


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
                    if (unknownNumber == (int) unknownNumber) // Check if the number is int or double
                        arr[k] = (int)unknownNumber; //TODO: Fix castign so that there will be stored int and not Integer or find another approach to issue on line 127
                    else
                        arr[k] = unknownNumber;
                } else
                    arr[k] = choice;

            }

            for (Constructor ctor : con) {
                Class<?>[] pType = ctor.getParameterTypes();
                boolean match = true;
                for (int j = 0; j < pType.length; j++) {
                    System.out.println(pType[j]);
                    System.out.println(arr[j].getClass());
                    if (!pType[j].isInstance(arr[j]) || pType.length != (choiceInt + 1)) { // Check if the parameters are of the same type
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



//        try {
//            Class<?> cArg = Class.forName(args[1]);
//
//            Class<?> c = Class.forName(args[0]);
//            Constructor[] allConstructors = c.getDeclaredConstructors();
//            for (Constructor ctor : allConstructors) {
//                Class<?>[] pType  = ctor.getParameterTypes();
//                for (int i = 0; i < pType.length; i++) {
//                    if (pType[i].equals(cArg)) {
//                        System.out.format("%s%n", ctor.toGenericString());
//
//                        Type[] gpType = ctor.getGenericParameterTypes();
//                        for (int j = 0; j < gpType.length; j++) {
//                            char ch = (pType[j].equals(cArg) ? '*' : ' ');
//                            System.out.format("%7c%s[%d]: %s%n", ch,
//                                    "GenericParameterType", j, gpType[j]);
//                        }
//                        break;
//                    }
//                }
//            }
//
//            // production code should handle this exception more gracefully
//        } catch (ClassNotFoundException x) {
//            x.printStackTrace();
//        }







//
//            Animal lio = new Lion("Simba");
//
//        // Creating class object from the object using
//        // getclass method
//        Class cls = lio.getClass();
//        System.out.println("The name of class is " +
//                cls.getName());
//
//        // Getting the constructor of the class through the
//        // object of the class
//        Constructor constructor = cls.getConstructor(String.class);
//        System.out.println("The name of constructor is " +
//                constructor.getName());
//
//        Animal lio2 = (Animal) constructor.newInstance("Simba2");
//        System.out.println(lio2);
//        System.out.println("The public methods of class are : ");
//
//        // Getting methods of the class through the object
//        // of the class by using getMethods
//        Method[] methods = cls.getMethods();
//
//        // Printing method names
//        for (Method method:methods)
//            System.out.println(method.getName());
//
//        // creates object of desired method by providing the
//        // method name and parameter class as arguments to
//        // the getDeclaredMethod
//        Method methodcall1 = cls.getDeclaredMethod("method2",
//                int.class);


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
