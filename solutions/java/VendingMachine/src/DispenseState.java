public class DispenseState implements VendingMachineStates {
    private final VendingMachine vendingMachine;

    public DispenseState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void selectProduct(Product product){
        System.out.println("Product is selected. Processing order.");
    }

    @Override
    public void insertCoin(Coin coin){
        System.out.println("Payment is completed. Processing order.");
    }

    @Override
    public void insertNote(Note note){
        System.out.println("Payment is completed. Processing order.");
    }

    @Override
    public void dispenseProduct(){
        Product product = vendingMachine.getProduct();
        vendingMachine.getStorage().updateStock(product, -1);
        System.out.println("Please pick up: " + vendingMachine.getProduct().getName());
        vendingMachine.setState(vendingMachine.getReturnChangesState());
    }

    @Override
    public void returnChanges(){
        System.out.println("Waiting. Processing order.");
    }
}
