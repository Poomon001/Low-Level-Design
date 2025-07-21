public class CoffeeVendingMachineDemo {
    public static void main(String[] args) {
        CoffeeVendingMachine coffeeVendingMachine = CoffeeVendingMachine.getCoffeeVendingMachine();

        coffeeVendingMachine.refillIngredient("Water", 100);
        coffeeVendingMachine.refillIngredient("Milk", 100);
        coffeeVendingMachine.refillIngredient("Coffee", 100);
        coffeeVendingMachine.refillIngredient("Sugar", 100);

        coffeeVendingMachine.showIngredients();
        System.out.println("");

        coffeeVendingMachine.displayMenu();
        System.out.println("");

        // order cappuccino
        Coffee cappuccino =  coffeeVendingMachine.selectCoffee("Cappuccino");
        coffeeVendingMachine.dispenseCoffee(cappuccino, new Payment(10));
        System.out.println("");

        // show available ingredients
        coffeeVendingMachine.showIngredients();
        System.out.println("");

        Coffee espresso =  coffeeVendingMachine.selectCoffee("Espresso");
        coffeeVendingMachine.dispenseCoffee(espresso, new Payment(5));
        System.out.println("");

        coffeeVendingMachine.showIngredients();
        System.out.println("");

        coffeeVendingMachine.refillIngredient("Water", 200);

        coffeeVendingMachine.showIngredients();
        System.out.println("");
    }
}
