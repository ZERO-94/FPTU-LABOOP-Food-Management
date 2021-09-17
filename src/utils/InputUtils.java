package utils;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author kiman
 */
public class InputUtils {

    public static String inputString(String message, int min, int max, boolean loop) throws IllegalArgumentException {
        do {
            try {
                Scanner sc = new Scanner(System.in);
                System.out.print(message);
                String input = sc.nextLine();
                String exceptionMessage = "input length must be from " + min + " to " + max;
                if (input.trim().length() > max || input.trim().length() < min) {
                    throw new IllegalArgumentException(exceptionMessage);
                }
                return input;
            } catch (Exception e) {
                if(loop == false ) throw e;
                System.out.println(e.getMessage());
                boolean check = inputYesNo("Continue to enter this field ?(Y/n)");
                if (check == false) {
                    throw new IllegalArgumentException("Failed to input");
                }
            }
        } while (true);
    }

    public static int inputInt(String message, int min, int max, boolean loop) throws IllegalArgumentException, InputMismatchException{
        do {
            try {
                Scanner sc = new Scanner(System.in);
                System.out.print(message);
                int input = 0;
                try {
                    input = sc.nextInt();
                } catch (InputMismatchException | NumberFormatException e) {
                    throw new InputMismatchException("invalid input type");
                }
                String exceptionMessage = "input value must be from " + min + " to " + max;
                if (input > max || input < min) {
                    throw new IllegalArgumentException(exceptionMessage);
                }
                return input;
            } catch (Exception e) {
                if(loop == false ) throw e;
                System.out.println(e.getMessage());
                boolean check = inputYesNo("Continue to enter this field ?(Y/n)");
                if (check == false) {
                    throw new IllegalArgumentException("Failed to input");
                }
            }
        } while (true);
    }

    public static double inputDouble(String message, double min, double max, boolean loop) throws IllegalArgumentException, InputMismatchException{
        do {
            try {
                Scanner sc = new Scanner(System.in);
                System.out.print(message);
                double input = 0;
                try {
                    input = sc.nextDouble();
                } catch (InputMismatchException | NumberFormatException e) {
                    throw new InputMismatchException("invalid input type");
                }
                String exceptionMessage = "input value must be from " + min + " to " + max;
                if (input > max || input < min) {
                    throw new IllegalArgumentException(exceptionMessage);
                }
                return input;
            } catch (Exception e) {
                if(loop == false ) throw e;
                System.out.println(e.getMessage());
                boolean check = inputYesNo("Continue to enter this field ?(Y/n)");
                if (check == false) {
                    throw new IllegalArgumentException("Failed to input");
                }
            }
        } while (true);
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
