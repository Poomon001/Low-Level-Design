class IdleState implements VendingMachineStates {
    private final VendingMachine vendingMachine;

    public IdleState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void selectProduct(Product product) {
        if(!vendingMachine.getStorage().isAvailable(product)) {
            System.out.println("Product is not available: " + product.getName() );
        } else {
            System.out.println("Selected: " + product.getName() + " - $" + product.getPrice());
            vendingMachine.setProduct(product);
            vendingMachine.setState(vendingMachine.getReadyState()); // move to the next state
        }
    }

    @Override
    public void insertCoin(Coin coin) {
        System.out.println("Select a product before inserting coins.");
    }

    @Override
    public void insertNote(Note note) {
        System.out.println("Select a product before inserting notes.");
    }

    @Override
    public void dispenseProduct(){
        System.out.println("No product to dispense.");
    }

    @Override
    public void returnChanges() {
        System.out.println("No changes to return.");
    }
}
