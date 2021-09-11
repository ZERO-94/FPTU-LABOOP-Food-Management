
package gui;

import dto.CreateFile;
import dto.Food;
import dto.Refrigerator;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import dto.Validator;
import java.util.InputMismatchException;

/**
 *
 * @author kiman
 */
public class UserInterface {
    private Scanner sc = new Scanner(System.in);
    private Refrigerator refrigerator;
        
    public UserInterface(Refrigerator refrigerator) {
        this.refrigerator = refrigerator;
    }
    
    public UserInterface() {
        this(new Refrigerator());
    }
    
    public void start() {
        int choice;
        do {
            choice = 0;
            System.out.println("Welcome to Food Management - @ 2021 by SE161002 - Tran Vu Kim Anh");
            System.out.println("Select the options below:");
            System.out.println("0. Create new the refrigerator with 5 foods for testing");
            System.out.println("1. Add a new food");
            System.out.println("2. Search a food by name");
            System.out.println("3. Remove the food by ID");
            System.out.println("4. Print the food list in the descending order of expired date");
            System.out.println("5. Store the foods of refrigerator in binary file");
            System.out.println("6. Quit");
            
            System.out.println("Enter your choice: ");
            this.sc = new Scanner(System.in);
            try {
                choice = inputChoice(0,6);
                switch(choice) {
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
        } while(choice<=5);
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
                System.out.println("Enter new food information: ");
                int id = Validator.readInt("Enter new food's id: ", 1, 1000);
                if(refrigerator.searchFoodById(id) != null) {
                    System.out.println("This food's id already existed!");
                    continue;
                }
                String name = Validator.readString("Enter new food's name: ", 1, 20);
                double weight = Validator.readDouble("Enter new food's weight: ", 0.1, 1000);
                String type = Validator.readString("Enter new food's type: ", 1, 20);
                String place = Validator.readString("Enter new food's place: ", 1, 20);
                
                System.out.println("Enter new food's expired date ");
                System.out.print("Please enter with the format yyyy-MM-dd (Example: 2020-05-06): ");
                String expiredDate = sc.nextLine();
                
                Food newFood = new Food(id, name, weight, type, place, expiredDate);
                if(refrigerator.addFood(newFood)) System.out.println("Added successful!");
                else System.out.println("Failed to add!");
            } catch (InputMismatchException e) {
                System.out.println("invalid input type");
            }catch (Exception e) {
                System.out.println(e);
            } finally {
                stillContinue = stillContinue = Validator.readYesNo("Continue?(Y/n)");
            }
        } while(stillContinue);
    }
    
    public void searchFood() {
        sc = new Scanner(System.in);
        boolean stillContinue = false;
        do {
            try {
                String keyword = Validator.readString("Enter your keyword: ", 0, 20);

                List<Food> neededFoods = refrigerator.searchFoodByName(keyword);
                if(neededFoods == null) System.out.println("This food does not exist");
                else {
                    System.out.println("List of foods with the keyword " +keyword);
                    neededFoods.stream().forEach(food -> System.out.println(food));
                }
            }catch (InputMismatchException e) {
                System.out.println("invalid input type");
            } 
            catch(Exception e) {
                System.out.println(e.getMessage());
            } finally {
                stillContinue = Validator.readYesNo("Continue?(Y/n)");
            }
        } while(stillContinue);
    }
    
    public void removeFood() {
        sc = new Scanner(System.in);
        try {
            int id = Validator.readInt("Enter new food's id: ", 1, 1000);

            Food neededFood = refrigerator.searchFoodById(id);
            if(neededFood != null) {
                System.out.println("The food you want to remove is: ");
                System.out.println(neededFood);
                
                boolean choice = Validator.readYesNo("Are you sure you want to remove this food?(Y/n)");
                if(choice == true) {
                    if(refrigerator.removeFood(neededFood)) System.out.println("Removed successful!");
                    else System.out.println("Failed to removed!");
                }
            } else {
                System.out.println("This food's id doesn't exist");
            }
        }catch (InputMismatchException e) {
                System.out.println("invalid input type");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void printFood() {
        sc = new Scanner(System.in);
        System.out.println("List of foods in refrigerator");
        refrigerator.getDescendFoodsCollection()
                .stream()
                    .forEach(food -> System.out.println(food));
    }
    
    public void storeInFile() {
        sc = new Scanner(System.in);
        String filename = "";
        try {
            filename = Validator.readString("Enter your file name: ", 1, 20);
            List<Food> foodsCollection = refrigerator.getFoodsCollection();
            CreateFile.writeBinaryFoods(filename, foodsCollection);
        } catch (InputMismatchException e) {
                System.out.println("invalid input type");
        } 
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        try {
            List<Food> foodsInFile = CreateFile.readBinaryDogs(filename);
            foodsInFile.stream()
                    .forEach(food -> System.out.println(food));
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public int inputChoice(int min,int max) throws IllegalArgumentException{
        sc = new Scanner(System.in);
        int userChoice = sc.nextInt();
        if(userChoice <min || userChoice > max) throw new IllegalArgumentException("invalid input");
        return userChoice;
    }
}
