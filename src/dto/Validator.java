
package dto;

import java.util.Scanner;

/**
 *
 * @author kiman
 */
public class Validator {
    public static String readString(String message, int min, int max) throws Exception{
        Scanner sc = new Scanner(System.in);
        System.out.print(message);
        String input = sc.nextLine();
        String exceptionMessage = "input length must be from " + min + " to " + max;
        if(input.length() > max || input.length() < min) throw new Exception(exceptionMessage);
        return input;
    }
    
    public static int readInt(String message, int min, int max) throws Exception{
        Scanner sc = new Scanner(System.in);
        System.out.print(message);
        int input = sc.nextInt();
        String exceptionMessage = "input value must be from " + min + " to " + max;
        if(input > max || input < min) throw new Exception(exceptionMessage);
        return input;
    }
    
    public static double readDouble(String message, double min, double max) throws Exception{
        Scanner sc = new Scanner(System.in);
        System.out.print(message);
        double input = sc.nextDouble();
        String exceptionMessage = "input value must be from " + min + " to " + max;
        if(input > max || input < min) throw new Exception(exceptionMessage);
        return input;
    }
    
    public static boolean readYesNo(String message) {
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println(message);
            String choice = sc.nextLine();
            if(choice.toLowerCase().equals("y")) return true;
            else if(choice.toLowerCase().equals("n")) return false;
        } while(true);
    }
}
