import java.util.Map;

public class VendingMachine {
    private static VendingMachine vendingMachine; // shared singleton instance
    private final Storage storage;
    private final IdleState idleState;
    private final ReadyState readyState;
    private final DispenseState dispenseState;
    private final ReturnChangesState returnChangesState;
    private VendingMachineStates state;
    private Product product;
    private double totalPayment;
    
    private VendingMachine() {
        this.storage = new Storage();
        this.idleState = new IdleState(this);
        this.readyState = new ReadyState(this);
        this.dispenseState = new DispenseState(this);
        this.returnChangesState = new ReturnChangesState(this);
        this.state = idleState;
        this.product = null;
        this.totalPayment = 0;
    }

    public Product addProduct(String name, double price, int quantity) {
        Product product = new Product(name, price);
        this.storage.add(product, quantity);
        return product;
    }

    public void displayProduct() {
        System.out.println("Products:");
        for(Map.Entry<Product, Integer> entry : this.storage.getStorage().entrySet()) {
            System.out.println("name: " + entry.getKey().getName() + " - " + entry.getKey().getPrice()+ "Dollars");
        }
    }

    public void selectProduct(Product product) {
        this.state.selectProduct(product);
    }

    public void insertCoin(Coin coin) {
        this.state.insertCoin(coin);
    }

    public void insertNote(Note note) {
        this.state.insertNote(note);
    }

    public void dispenseProduct() {
        this.state.dispenseProduct();
    }

    public void returnChanges() {
        this.state.returnChanges();
    }

    public void addTotalPayment(double amount) {
        this.totalPayment += amount;
    }

    public Storage getStorage() {
        return this.storage;
    }

    public double getTotalPayment() {
        return this.totalPayment;
    }

    public Product getProduct() {
        return this.product;
    }

    void setProduct(Product product) {
        this.product = product;
    }

    void setState(VendingMachineStates state) {
        this.state = state;
    }

    VendingMachineStates getIdleState(){
        return this.idleState;
    }

    VendingMachineStates getReadyState(){
        return this.readyState;
    }

    VendingMachineStates getDispenseState(){
        return this.dispenseState;
    }

    VendingMachineStates getReturnChangesState(){
        return this.returnChangesState;
    }

    public void reset() {
        if(this.state == getIdleState() || this.state == getReadyState()) {
            this.state = getIdleState();
            this.product = null;
            this.totalPayment = 0;
        } else {
            System.out.println("Cannot longer reset.");
        }
    }

    public static synchronized VendingMachine getInstance() {
        if(vendingMachine == null) {
            vendingMachine = new VendingMachine();
        }
        return vendingMachine;
    }
}
