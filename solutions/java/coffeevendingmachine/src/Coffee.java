import java.util.ArrayList;
import java.util.Hashtable;
import java.util.concurrent.ConcurrentHashMap;

public class Coffee {
    private final String name; // espresso
    private final double price; // 11.97
    private ConcurrentHashMap<String, Integer> recipes; // {ingredient, needed quantity}


    public Coffee(String name, double price, ConcurrentHashMap<String, Integer> recipes) {
        this.name = name;
        this.price = price;
        this.recipes = recipes;
    }

    public String getName() {
        return this.name;
    }

    public double getPrice() {
        return this.price;
    }

    public ConcurrentHashMap<String, Integer> getRecipes() {
        return this.recipes;
    }
}
