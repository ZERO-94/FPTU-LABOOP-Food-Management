package utils;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author kiman
 */
public class InputUtils {

    public static String inputString(String message, int min, int max) throws IllegalArgumentException {
        Scanner sc = new Scanner(System.in);
        System.out.print(message);
        String input = sc.nextLine();
        String exceptionMessage = "input length must be from " + min + " to " + max;
        if (input.length() > max || input.length() < min) {
            throw new IllegalArgumentException(exceptionMessage);
        }
        return input;
    }

    public static int inputInt(String message, int min, int max) throws IllegalArgumentException, InputMismatchException {
        Scanner sc = new Scanner(System.in);
        System.out.print(message);
        int input = sc.nextInt();
        String exceptionMessage = "input value must be from " + min + " to " + max;
        if (input > max || input < min) {
            throw new IllegalArgumentException(exceptionMessage);
        }
        return input;
    }

    public static double inputDouble(String message, double min, double max) throws IllegalArgumentException, InputMismatchException {
        Scanner sc = new Scanner(System.in);
        System.out.print(message);
        double input = sc.nextDouble();
        String exceptionMessage = "input value must be from " + min + " to " + max;
        if (input > max || input < min) {
            throw new IllegalArgumentException(exceptionMessage);
        }
        return input;
    }

    public static boolean inputYesNo(String message) {
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println(message);
            String choice = sc.nextLine();
            if (choice.toLowerCase().equals("y")) {
                return true;
            } else if (choice.toLowerCase().equals("n")) {
                return false;
            }
        } while (true);
    }
}
