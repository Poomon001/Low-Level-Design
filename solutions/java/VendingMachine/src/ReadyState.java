class ReadyState implements VendingMachineStates {
    private final VendingMachine vendingMachine;

    public ReadyState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void selectProduct(Product product){
        System.out.println("Product is selected. Need to complete payment.");
    }

    @Override
    public void insertCoin(Coin coin){
        vendingMachine.addTotalPayment(coin.getValue());
        System.out.println("Inserted a coin: " + coin);
        checkSufficientPayment();
    }

    @Override
    public void insertNote(Note note){
        vendingMachine.addTotalPayment(note.getValue());
        System.out.println("Inserted a coin: " + note);
        checkSufficientPayment();
    }

    @Override
    public void dispenseProduct(){
        System.out.println("Need to complete payment.");
    }

    @Override
    public void returnChanges(){
        System.out.println("No changes to return.");
    }

    private void checkSufficientPayment() {
        if(vendingMachine.getTotalPayment() >= vendingMachine.getProduct().getPrice()) {
            vendingMachine.setState(vendingMachine.getDispenseState()); // move to the next state if the payment is sufficient
        }
    }
}
