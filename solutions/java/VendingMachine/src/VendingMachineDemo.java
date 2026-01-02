public class VendingMachineDemo {
    public static void main(String[] args) {
        VendingMachine vendingMachine = VendingMachine.getInstance();

        // Add product to the storage
        Product coke = vendingMachine.addProduct("Coke", 3.50, 20);
        Product pepsi = vendingMachine.addProduct("Pepsi", 3.50, 20);
        Product sprite = vendingMachine.addProduct("Sprite", 3.50, 20);
        Product soda = vendingMachine.addProduct("Soda", 1.00, 0);
        Product water = vendingMachine.addProduct("Water", 0.99, 100);

        System.out.println("\n === First order === \n");
        // Select product
        vendingMachine.selectProduct(coke);
        vendingMachine.selectProduct(water);

        // Insert coins
        vendingMachine.insertCoin(Coin.QUARTER);
        vendingMachine.insertCoin(Coin.QUARTER);

        // Insert notes
        vendingMachine.insertNote(Note.TEN);
        vendingMachine.insertNote(Note.TEN);

        // Dispense the product
        vendingMachine.dispenseProduct();

        // Return change
        vendingMachine.returnChanges();

        System.out.println("\n === Second order === \n");
        // Select product
        vendingMachine.selectProduct(water);

        // Insert coins
        vendingMachine.insertCoin(Coin.LOONIE);
        vendingMachine.insertCoin(Coin.LOONIE);

        // Dispense the product
        vendingMachine.dispenseProduct();

        // Return change
        vendingMachine.returnChanges();

        System.out.println("\n === Third order === \n");
        // Select product
        vendingMachine.selectProduct(soda); // sold out
        vendingMachine.selectProduct(coke);
        vendingMachine.selectProduct(water);

        // Insert notes
        vendingMachine.insertNote(Note.TWENTY);

        // Dispense the product
        vendingMachine.dispenseProduct();

        // Return change
        vendingMachine.returnChanges();

        System.out.println("\n === Storage check === \n");
        System.out.println("Coke: "+ vendingMachine.getStorage().getQuantity(coke));
        System.out.println("Pepsi: "+ vendingMachine.getStorage().getQuantity(pepsi));
        System.out.println("Sprite: "+ vendingMachine.getStorage().getQuantity(sprite));
        System.out.println("Soda: "+ vendingMachine.getStorage().getQuantity(soda));
        System.out.println("Water: "+ vendingMachine.getStorage().getQuantity(water));
    }
}
