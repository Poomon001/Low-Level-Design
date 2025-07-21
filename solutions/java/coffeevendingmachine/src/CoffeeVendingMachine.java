// This is the main CoffeeVendingMachine class
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CoffeeVendingMachine {
    private static final CoffeeVendingMachine coffeeMachine = new CoffeeVendingMachine(); // Singleton pattern
    private final ConcurrentHashMap<String, Coffee> coffeeMenu = new ConcurrentHashMap<>(); // {Coffee type, Coffee}
    private final IngredientStorage ingredientStorage;
    private final Dispenser dispenser;
    private final PaymentProcessor paymentProcessor;

    private CoffeeVendingMachine() {
        // Singleton pattern
        System.out.println("\n === init Coffee Vending Machine === \n");
        this.ingredientStorage = new IngredientStorage();
        this.dispenser = new Dispenser();
        this.paymentProcessor = new PaymentProcessor();
        this.addRecipe("Espresso", 2.5, new ConcurrentHashMap(Map.of(
                "Water", 50,
                "Coffee", 20
        )));
        this.addRecipe("Latte", 4.5, new ConcurrentHashMap(Map.of(
                "Water", 50,
                "Coffee", 30,
                "Milk", 30
        )));
        this.addRecipe("Cappuccino", 4, new ConcurrentHashMap(Map.of(
                "Water", 40,
                "Coffee", 30,
                "Milk", 40
        )));
    }

    private void addRecipe(String name, double price, ConcurrentHashMap<String, Integer> recipes) {
        this.coffeeMenu.put(name, new Coffee(name, price, recipes));
    }

    public void displayMenu() {
        System.out.println("Coffee Menu:");
        for(Map.Entry<String, Coffee> entry : this.coffeeMenu.entrySet()) {
            String coffee = entry.getKey();
            double price = entry.getValue().getPrice();
            System.out.println(coffee + " - $" + price);
        }
    }

    // sensitive for racing
    public Coffee selectCoffee(String coffee) {
        if(!this.coffeeMenu.containsKey(coffee)) {
            throw new RuntimeException("Invalid coffee recipe: " + coffee);
        }
        return this.coffeeMenu.get(coffee);
    }

    public void dispenseCoffee(Coffee coffee, Payment payment) {
        // process payment
        double change = this.paymentProcessor.process(coffee.getPrice(), payment.getAmount());

        // deduce ingredient
        if(!this.ingredientStorage.hasEnoughIngredient(coffee.getRecipes())) {
            throw new RuntimeException("Insufficient ingredients to make " + coffee.getName());
        }

        this.ingredientStorage.consume(coffee.getRecipes());

        // dispense coffee
        this.dispenser.serve(coffee);

        System.out.println("Please collect your change: $" + change);
    }

    public void refillIngredient(String ingredient, int quantity) {
        this.ingredientStorage.refill(ingredient, quantity);
    }

    public void showIngredients() {
        System.out.println("Ingredient Levels:");
        for(Map.Entry<String, Integer> entry : this.ingredientStorage.getStorage().entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    public static CoffeeVendingMachine getCoffeeVendingMachine() {
        return coffeeMachine;
    }
}
