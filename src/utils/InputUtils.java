package utils;

import dto.Food;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author kiman
 */
public class InputUtils {
    public static String inputString(String message, int min, int max, boolean loop) throws IllegalArgumentException {
        Scanner sc = new Scanner(System.in);
        String input;
        String exceptionMessage = "input length must be from " + min + " to " + max;
        do {

            System.out.print(message);
            input = sc.nextLine();
            if (input.trim().length() <= max && input.trim().length() >= min) {
                return input;
            }
            handleInvalidInput(loop, exceptionMessage);
        } while (true);
    }

    public static int inputInt(String message, int min, int max, boolean loop) throws IllegalArgumentException {
        Scanner sc = new Scanner(System.in);
        String exceptionMessage;
        do {
            int input = 0;
            sc = new Scanner(System.in); //reset input stream
            System.out.print(message);

            try {
                input = sc.nextInt();
            } catch (InputMismatchException | NumberFormatException e) {
                exceptionMessage = "Invalid inpyt type";
                handleInvalidInput(loop, exceptionMessage);
                continue;
            }

            if (input <= max && input >= min) {
                return input;
            }
            exceptionMessage = "input value must be from " + min + " to " + max;
            handleInvalidInput(loop, exceptionMessage);
        } while (true);
    }

    public static double inputDouble(String message, double min, double max, boolean loop) throws IllegalArgumentException {
        Scanner sc = new Scanner(System.in);
        String exceptionMessage;
        do {
            double input = 0;
            sc = new Scanner(System.in); //reset input stream
            System.out.print(message);
            try {
                input = sc.nextDouble();
            } catch (InputMismatchException | NumberFormatException e) {
                exceptionMessage = "invalid input type";
                handleInvalidInput(loop, exceptionMessage);
                continue;
            }

            if (input <= max && input >= min) {
                return input;
            }

            exceptionMessage = "input value must be from " + min + " to " + max;
            handleInvalidInput(loop, exceptionMessage);
        } while (true);
    }

    public static LocalDate inputDate(String message, boolean loop) throws IllegalArgumentException {
        Scanner sc = new Scanner(System.in);
        String dateInString;
        LocalDate date;
        String exceptionMessage = "invalid date format";
        do {
            System.out.println(message);
            System.out.print("Please enter with the format yyyy-MM-dd (Example: 2020-05-06): ");
            dateInString = sc.nextLine();
            try {
                date = LocalDate.parse(dateInString);
                return date;
            } catch (DateTimeParseException e) {
                handleInvalidInput(loop, exceptionMessage);
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
            
            System.out.println("Please enter 'y' or 'n' only");
        } while (true);
    }
    
    private static void handleInvalidInput(boolean isLoop, String exceptionMessage) throws IllegalArgumentException {
        if (isLoop == false) {
            throw new IllegalArgumentException(exceptionMessage);
        }
        System.out.println(exceptionMessage);
        boolean isContinue = inputYesNo("Continue to enter this field ?(Y/n)");
        if (!isContinue) {
            throw new IllegalArgumentException("Failed to input");
        }
    }
}
