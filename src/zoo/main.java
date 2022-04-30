//package zoo;
//
//
//import animals.*;
//import mobility.Point;
//
//import java.io.File;
//import java.io.FilenameFilter;
//import java.lang.reflect.*;
//import java.net.URL;
//import java.util.*;
//
//public class main {
//    private static final int MIN_ARR_LEN = 3;
//
//    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, ClassNotFoundException {
//
//        Scanner sc = new Scanner(System.in);
//        // GET NUMBER OF ANIMALS IN THE ZOO
//        System.out.println("Please enter the number of animals in the zoo [Minimum :" + MIN_ARR_LEN + "] : ");
//        int num = sc.nextInt();
//        if (num < MIN_ARR_LEN) {
//            System.out.println("Err: The minimum number of possible animals in the zoo is: " + MIN_ARR_LEN + " Setting the number of animals in the zoo to the minimum");
//            num = MIN_ARR_LEN;
//        }
//        Animal[] zoo = new Animal[num];
//
//
//        for (int i=0; i<zoo.length; i++) {
//            System.out.println("""
//                    Select animal number from the list you would like to create:
//                    1 - [Bear]
//                    2 - [Elephant]
//                    3 - [Giraffe]
//                    4 - [Lion]
//                    5 - [Turtle]
//                    """);
//            num = sc.nextInt();
//            String name,color;
//            int x, y, age;
//            double length;
//            switch (num) {
//                case 1: // +++++ +++++ +++++ +++++ Bear +++++ +++++ +++++ +++++ +++++
//                    System.out.println("""
//                            +++++\s+++++\s+++++\s+++++\s[Bear] +++++\s+++++\s+++++\s+++++\s
//                            Chose ctor you would like to use [1-3]:
//                            1 - Name
//                            2 - Name, Fur Color
//                            3 - Name, X point, Y point
//                            """);
//                    num = sc.nextInt();
//                    switch (num) {
//                        case 1 -> { // Name
//                            System.out.println("Please enter the name of the bear:");
//                            name = sc.next();
//                            zoo[i] = new Bear(name);
//                        }
//                        case 2 -> { // Name, Fur Color
//                            System.out.println("Please enter the name of the bear:");
//                            name = sc.next();
//                            System.out.println("Enter the fur color [BLACK,WHITE,GRAY]:");
//                            color = sc.next();
//                            zoo[i] = new Bear(name, color);
//                        }
//                        case 3 -> { // Name, X point, Y point
//                            System.out.println("Please enter the name of the bear:");
//                            name = sc.next();
//                            System.out.println("X coordinates:");
//                            x = sc.nextInt();
//                            System.out.println("Y coordinates:");
//                            y = sc.nextInt();
//                            zoo[i] = new Bear(name, x, y);
//                        }
//                        default -> System.out.println("Error: no option found");
//                    }
//                    break;
//                case 2: // +++++ +++++ +++++ +++++ Elephant +++++ +++++ +++++ +++++
//                    System.out.println("""
//                            +++++\s+++++\s+++++\s+++++\s[Elephant] +++++\s+++++\s+++++\s+++++\s
//                            Chose ctor you would like to use [1-3]:
//                            1 - Name
//                            2 - Name, Trunk Length
//                            3 - Name, X point, Y point
//                            """);
//                    num = sc.nextInt();
//                    switch (num) {
//                        case 1 -> { // Name
//                            System.out.println("Please enter the name of the elephant:");
//                            name = sc.next();
//                            zoo[i] = new Elephant(name);
//                        }
//                        case 2 -> { // Name, Fur Color
//                            System.out.println("Please enter the name of the elephant:");
//                            name = sc.next();
//                            System.out.println("Enter the trunk length (Double):");
//                            length = sc.nextDouble();
//                            zoo[i] = new Elephant(name, length);
//                        }
//                        case 3 -> { // Name, X point, Y point
//                            System.out.println("Please enter the name of the elephant:");
//                            name = sc.next();
//                            System.out.println("X coordinates:");
//                            x = sc.nextInt();
//                            System.out.println("Y coordinates:");
//                            y = sc.nextInt();
//                            zoo[i] = new Elephant(name, x, y);
//                        }
//                        default -> System.out.println("Error: no option found");
//                    }
//                    break;
//                case 3: // +++++ +++++ +++++ +++++ Giraffe +++++ +++++ +++++ +++++
//                    System.out.println("""
//                            +++++\s+++++\s+++++\s+++++\s[Giraffe] +++++\s+++++\s+++++\s+++++\s
//                            Chose ctor you would like to use [1-3]:
//                            1 - Name
//                            2 - Name, Neck length
//                            3 - Name, X point, Y point
//                            """);
//                    num = sc.nextInt();
//                    switch (num) {
//                        case 1 -> { // Name
//                            System.out.println("Please enter the name of the giraffe:");
//                            name = sc.next();
//                            zoo[i] = new Giraffe(name);
//                        }
//                        case 2 -> { // Name, Fur Color
//                            System.out.println("Please enter the name of the giraffe:");
//                            name = sc.next();
//                            System.out.println("Enter the neck length (Double):");
//                            length = sc.nextDouble();
//                            zoo[i] = new Giraffe(name, length);
//                        }
//                        case 3 -> { // Name, X point, Y point
//                            System.out.println("Please enter the name of the giraffe:");
//                            name = sc.next();
//                            System.out.println("X coordinates:");
//                            x = sc.nextInt();
//                            System.out.println("Y coordinates:");
//                            y = sc.nextInt();
//                            zoo[i] = new Giraffe(name, x, y);
//                        }
//                        default -> System.out.println("Error: no option found");
//                    }
//                    break;
//                case 4: // +++++ +++++ +++++ +++++ Lion +++++ +++++ +++++ +++++
//                    System.out.println("""
//                            +++++\s+++++\s+++++\s+++++\s[Lion] +++++\s+++++\s+++++\s+++++\s
//                            Chose ctor you would like to use [1-3]:
//                            1 - Name
//                            2 - Name, X point, Y point
//                            """);
//                    num = sc.nextInt();
//                    switch (num) {
//                        case 1 -> { // Name
//                            System.out.println("Please enter the name of the lion:");
//                            name = sc.next();
//                            zoo[i] = new Lion(name);
//                        }
//                        case 2 -> { // Name, Fur Color
//                            System.out.println("Please enter the name of the lion:");
//                            name = sc.next();
//                            System.out.println("X coordinates:");
//                            x = sc.nextInt();
//                            System.out.println("Y coordinates:");
//                            y = sc.nextInt();
//                            zoo[i] = new Lion(name, x, y);
//                        }
//                        default -> System.out.println("Error: no option found");
//                    }
//                    break;
//                case 5: // +++++ +++++ +++++ +++++ Turtle +++++ +++++ +++++ +++++
//                    System.out.println("""
//                            +++++\s+++++\s+++++\s+++++\s[Turtle] +++++\s+++++\s+++++\s+++++\s
//                            Chose ctor you would like to use [1-3]:
//                            1 - Name
//                            2 - Name, Age
//                            3 - Name, X point, Y point
//                            """);
//                    num = sc.nextInt();
//                    switch (num) {
//                        case 1 -> { // Name
//                            System.out.println("Please enter the name of the turtle:");
//                            name = sc.next();
//                            zoo[i] = new Turtle(name);
//                        }
//                        case 2 -> { // Name, Fur Color
//                            System.out.println("Please enter the name of the turtle:");
//                            name = sc.next();
//                            System.out.println("Enter the age:");
//                            age = sc.nextInt();
//                            zoo[i] = new Turtle(name, age);
//                        }
//                        case 3 -> { // Name, X point, Y point
//                            System.out.println("Please enter the name of the turtle:");
//                            name = sc.next();
//                            System.out.println("X coordinates:");
//                            x = sc.nextInt();
//                            System.out.println("Y coordinates:");
//                            y = sc.nextInt();
//                            zoo[i] = new Turtle(name, x, y);
//                        }
//                        default -> System.out.println("Error: no option found");
//                    }
//                    break;
//                default: // ERR
//                    System.out.println("Error: The chosen value is incorrect please chose number between 1-5");
//                    i--;
//                    break;
//            }
//        }
//        System.out.println("Animals created successfully\nPrinting all the animals with their indexes:");
//        while (true) {
//            for (int i = 0; i < zoo.length; i++) {
//                System.out.println("[" + i + "]\t" + zoo[i]);
//            }
//            System.out.println("Chose animal you want to move [-1] for none");
//            num = sc.nextInt();
//            if (num == -1)
//                break; // EXIT LOOP
//
//            System.out.println(zoo[num] + " Current location: " + zoo[num].getLocation() + "\n" +
//                    "Where do you want the animal to go? [X coordinate] :");
//            int x = sc.nextInt();
//            System.out.println("[Y coordinate] :");
//            int y = sc.nextInt();
//            if (!ZooActions.move(zoo[num],new Point(x,y)))
//                System.out.println("Error: Couldn't move the animal to that point, Please try again");
//        }
//        System.out.println("Choosing two random animals from the list");
//        for (int i = 0; i < Math.floor(zoo.length/2.); i++) {
//            Random random = new Random();
//            int a = random.nextInt(0, zoo.length);
//            int b;
//            do {
//                b = random.nextInt(0, zoo.length);
//            } while (b == a);
//            ZooActions.eat(zoo[a],zoo[b]);
//        }
//
//    }
//}
