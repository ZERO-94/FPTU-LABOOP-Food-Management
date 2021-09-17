package gui;

import utils.FileUtils;
import dto.Food;
import java.io.IOException;
import services.Refrigerator;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import utils.InputUtils;
import java.util.InputMismatchException;
import static utils.InputUtils.inputYesNo;

/**
 *
 * @author kiman
 */
public class UiDisplay {

    private Scanner sc = new Scanner(System.in);
    private Menu mainMenu = new Menu();
    private Refrigerator refrigerator;

    public UiDisplay(Refrigerator refrigerator) {
        this.refrigerator = refrigerator;
        mainMenu.addOption("Create new the refrigerator with 5 foods for testing");
        mainMenu.addOption("Add a new food");
        mainMenu.addOption("Search a food by name");
        mainMenu.addOption("Remove the food by ID");
        mainMenu.addOption("Print the food list in the descending order of expired date");
        mainMenu.addOption("Store the foods of refrigerator in binary file");
        mainMenu.addOption("Quit");
    }

    public UiDisplay() {
        this(new Refrigerator());
    }

    public void start() {
        int choice;
        do {
            choice = 0;
            System.out.println("Welcome to Food Management - @ 2021 by SE161002 - Tran Vu Kim Anh");
            mainMenu.displayMenu();
            this.sc = new Scanner(System.in);
            try {
                choice = InputUtils.inputInt("", 0, 6, false);
                switch (choice) {
                    case 0:
                        testingFoods();
                        break;
                    case 1:
                        addNewFood();
                        break;
                    case 2:
                        searchFood();
                        break;
                    case 3:
                        removeFood();
                        break;
                    case 4:
                        printFood();
                        break;
                    case 5:
                        storeInFile();
                        break;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (choice <= 5);
    }

    public void testingFoods() {
        refrigerator.addFood(new Food(2, "fish", 1.2, "seafood", "hks", LocalDate.parse("2020-05-12")));
        refrigerator.addFood(new Food(1, "beef", 1.0, "meat", "km", LocalDate.parse("2020-05-09")));
        refrigerator.addFood(new Food(4, "orange", 0.8, "fruits", "acj", LocalDate.parse("2020-06-17")));
        refrigerator.addFood(new Food(5, "apple", 0.4, "fruits", "oki", LocalDate.parse("2021-07-17")));
        refrigerator.addFood(new Food(8, "carrot", 2.0, "veggies", "hks", LocalDate.parse("2021-01-10")));
    }

    public void addNewFood() {
        sc = new Scanner(System.in);
        boolean stillContinue = false;
        do {
            try {
                Food newFood = null;
                newFood = createNewFood();
                if (refrigerator.searchFoodById(newFood.getId()) == null) {
                    String result = refrigerator.addFood(newFood) ? "Added successful!" : "Failed to add!";
                    System.out.println(result);
                } else {
                    System.out.println("This food's id already existed!");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("Failed to add!");
            } finally {
                stillContinue = InputUtils.inputYesNo("Continue to add new food?(Y/n)");
            }
        } while (stillContinue);
    }

    public void searchFood() {
        sc = new Scanner(System.in);
        boolean stillContinue = false;
        do {
            try {
                String keyword = InputUtils.inputString("Enter your keyword: ", 0, 20, true);

                List<Food> neededFoods = refrigerator.searchFoodByName(keyword);
                if (neededFoods != null) {
                    System.out.println("List of foods with the keyword " + keyword);
                    neededFoods.stream().forEach(food -> System.out.println(food));
                } else {
                    System.out.println("This food does not exist");
                }
            } catch (InputMismatchException e) {
                System.out.println("invalid input type");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            } finally {
                stillContinue = InputUtils.inputYesNo("Continue?(Y/n)");
            }
        } while (stillContinue);
    }

    public void removeFood() throws Exception {
        sc = new Scanner(System.in);
        int id = InputUtils.inputInt("Enter food's id: ", 1, 1000, true);

        Food neededFood = refrigerator.searchFoodById(id);
        if (neededFood == null) {
            System.out.println("This food doesn't exist");
            return;
        }

        System.out.println("The food you want to remove is: ");
        System.out.println(neededFood);

        boolean choice = InputUtils.inputYesNo("Are you sure you want to remove this food?(Y/n)");
        if (choice == false) {
            return;
        }

        String result = refrigerator.removeFood(neededFood) ? "Removed successful!" : "Failed to removed!";
        System.out.println(result);
    }

    public void printFood() {
        if (refrigerator.isEmpty()) {
            System.out.println("There isn't any food in refrigerator");
            return;
        }

        System.out.println("List of foods in refrigerator");
        refrigerator.getDescendFoodsCollection()
                .stream()
                .forEach(food -> System.out.println(food));
    }

    public void storeInFile() throws IllegalArgumentException, IOException, ClassNotFoundException {
        sc = new Scanner(System.in);
        String filename = "";
        
        filename = InputUtils.inputString("Enter your file name: ", 1, 20, true);
        if(filename.contains(".")) throw new IllegalArgumentException("invalid file name ( '.' is not allowed )");
        
        List<Food> foodsCollection = refrigerator.getFoodsCollection();
        FileUtils.writeBinaryFoods(filename, foodsCollection);

        List<Food> foodsInFile = FileUtils.readBinaryFoods(filename);
        foodsInFile.stream().forEach(food -> System.out.println(food));
    }

    public Food createNewFood() throws IllegalArgumentException{
        System.out.println("Enter new food information: ");

        int id = InputUtils.inputInt("Enter new food's id: ", 1, 1000, true);
        if(refrigerator.searchFoodById(id) != null) throw new IllegalArgumentException("This food already existed");

        String name = InputUtils.inputString("Enter new food's name: ", 1, 20, true);
        double weight = InputUtils.inputDouble("Enter new food's weight: ", 0.1, 1000, true);
        String type = InputUtils.inputString("Enter new food's type: ", 1, 20, true);
        String place = InputUtils.inputString("Enter new food's place: ", 1, 20, true);

        do {
        System.out.println("Enter new food's expired date ");
        System.out.print("Please enter with the format yyyy-MM-dd (Example: 2020-05-06): ");
        
        String expiredDateInString = "";
        LocalDate expiredDate = null;
        try{
            expiredDateInString = sc.nextLine();
            expiredDate = LocalDate.parse(expiredDateInString);
            return new Food(id, name, weight, type, place, expiredDate);
        } catch(DateTimeParseException e) {
            System.out.println("invalid date format");
                boolean check = inputYesNo("Continue to enter this field ?(Y/n)");
                if (check == false) {
                    throw new IllegalArgumentException("Failed to input");
                }
        }
        } while (true);
    }
}
