package services;

import dto.Food;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Refrigerator {

    private List<Food> foodsCollection = new ArrayList<>();

    public List getFoodsCollection() {
        List<Food> newFoodsCollection = foodsCollection.stream().collect(Collectors.toList());
        return newFoodsCollection;
    }

    public boolean isEmpty() {
        return foodsCollection.isEmpty();
    }

    public boolean addFood(Food food) {
        if (foodsCollection.contains(food)) {
            return false;
        }

        return foodsCollection.add(food);
    }

    public List searchFoodByName(String keyword) {
        if (keyword == null) {
            return null;
        }

        List<Food> filteredCollection = foodsCollection.stream()
                .filter(food -> food.getName()
                .contains(keyword)).collect(Collectors.toList());

        if (filteredCollection.isEmpty()) {
            return null;
        }
        return filteredCollection;
    }

    public Food searchFoodById(int foodId) {
        Food neededFood = foodsCollection.stream()
                .filter(food -> food.getId() == foodId)
                .reduce(null, (acc, food) -> acc = food);

        return neededFood;
    }

    public boolean removeFood(Food food) {
        return foodsCollection.remove(food);
    }

    public boolean removeFood(int removedFoodId) {
        Food removedFood = searchFoodById(removedFoodId);
        return foodsCollection.remove(removedFood);
    }

    public List getDescendFoodsCollection() {
        List<Food> cloneFoodsCollection = getFoodsCollection();
        Collections.sort(cloneFoodsCollection);
        return cloneFoodsCollection;
    }

    @Override
    public String toString() {
        String output = foodsCollection.stream()
                .map(food -> food.toString())
                .reduce("", (acc, foodString) -> acc + foodString + "\n");
        return output;
    }
}
