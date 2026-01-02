import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Storage {
    private final ConcurrentHashMap<Product, Integer> storage;

    public Storage() {
        this.storage = new ConcurrentHashMap<>();
    }

    public void add(Product product, int quantity) {
        if(this.storage.containsKey(product)) {
            throw new IllegalArgumentException("product already exists");
        }
        this.storage.put(product, quantity);
    }

    public void updateStock(Product product, int quantity) {
        if(!this.storage.containsKey(product)) {
            throw new IllegalArgumentException("product does not exists");
        }

        this.storage.put(product, this.storage.get(product) + quantity);
    }

    public void remove(Product product) {
        this.storage.remove(product);
    }

    public int getQuantity(Product product) {
        if (!this.storage.containsKey(product)) {
            throw new IllegalArgumentException("product does not exists");
        }

        return this.storage.get(product);
    }

    public Map<Product, Integer> getStorage() {
        return Map.copyOf(this.storage);
    }

    public boolean isAvailable(Product product) {
        if(!this.storage.containsKey(product)) {
            throw new IllegalArgumentException("product does not exists");
        }

        return storage.containsKey(product) && storage.get(product) > 0;
    }
}
