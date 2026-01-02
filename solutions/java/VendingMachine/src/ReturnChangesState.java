public class ReturnChangesState implements VendingMachineStates {
    private final VendingMachine vendingMachine;

    public ReturnChangesState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void selectProduct(Product product){
        System.out.println("Product is selected. Please take your changes.");
    }

    @Override
    public void insertCoin(Coin coin){
        System.out.println("Payment is completed. Please take your changes.");
    }

    @Override
    public void insertNote(Note note){
        System.out.println("Payment is completed. Please take your changes.");
    }

    @Override
    public void dispenseProduct(){
        System.out.println("Dispensed products. Please take your changes.");
    }

    @Override
    public void returnChanges(){
        Product product = vendingMachine.getProduct();
        double changes = vendingMachine.getTotalPayment() - product.getPrice();
        if(changes > 0) {
            System.out.println("Pick up your changes: $" + changes);
        } else {
            System.out.println("No changes");
        }
        vendingMachine.setState(vendingMachine.getIdleState());
        vendingMachine.reset();
    }
}
