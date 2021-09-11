
package dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Refrigerator {
    private ArrayList<Food> foodsCollection;

    public Refrigerator() {
        this.foodsCollection = new ArrayList<>();
    }
    
    public List getFoodsCollection() {
        List<Food> newFoodsCollection = foodsCollection.stream().collect(Collectors.toList());
        return newFoodsCollection;
    }
    
    public boolean addFood(Food food) {
        if(food == null) return false;
        
        if(foodsCollection.contains(food)) return false;
        
        return foodsCollection.add(food);
    }
    
    public List searchFoodByName(String keyword) {
        if(keyword == null) return null;
        
        List<Food> filteredCollection = foodsCollection.stream()
                .filter(food -> food.getName()
                        .contains(keyword)).collect(Collectors.toList());
        
        if(filteredCollection.isEmpty()) return null;
        return filteredCollection;
    }
    
    public Food searchFoodById(int foodId) throws IllegalArgumentException{
        if(foodId <= 0) throw new IllegalArgumentException("Invalid Id");
        
        Food neededFood = foodsCollection.stream()
                .filter(food -> food.getId() == foodId)
                    .reduce(null, (acc, food) -> acc = food);
        
        return neededFood;
    }
    
    public boolean removeFood(Food food) {
        if(food == null) return false;
        
        return foodsCollection.remove(food);
    }
    
    public boolean removeFood(int removedFoodId) throws IllegalArgumentException{
        if(removedFoodId <= 0) throw new IllegalArgumentException("Invalid Id");
        
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
