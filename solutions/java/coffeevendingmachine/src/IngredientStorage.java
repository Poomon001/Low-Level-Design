/**
    Key - need 'synchronized' if:
    0. interact with database
    1. Check-Then-Act
    2. Multi-Key Transactions
    3. Iteration with Modification
    4. Atomic Operations Across Multiple Data Structures
    5. Read-Modify-Write with External Dependencies
**/
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class IngredientStorage {
    // {ingredient, quantity}
    private final Map<String, Integer> storage = new ConcurrentHashMap<>(); // Allow async read/write

    // sync to prevent race condition when consume/refill
    public void refill(String ingredient, int quantity) {
        this.storage.put(ingredient, this.storage.getOrDefault(ingredient, 0) + quantity);
    }

    // need explicit synchronized because of check-then-act and iteration with Modification
    public synchronized void consume(Map<String, Integer> consumption) {
        for(Map.Entry<String, Integer> entry : consumption.entrySet()) {
            String ingredient = entry.getKey();
            int deduction = entry.getValue();
            validateIngredient(ingredient);
            validateDeduction(deduction);
            this.storage.put(ingredient, this.storage.get(ingredient) - deduction);
        }
    }

    // need explicit synchronized because of check-then-act and iteration with Modification
    public synchronized boolean hasEnoughIngredient(Map<String, Integer> consumption) {
        for(Map.Entry<String, Integer> entry : consumption.entrySet()) {
            String ingredient = entry.getKey();
            int deduction = entry.getValue();
            validateIngredient(ingredient);
            validateDeduction(deduction);
            if(this.storage.get(ingredient) < deduction) {
                return false;
            }
        }
        return true;
    }

    public int getQuantity(String ingredient) {
        return this.storage.getOrDefault(ingredient, 0);
    }

    public Map<String, Integer> getStorage() {
        return Map.copyOf(this.storage); // return a compy to ensure that the original storage will not be modified
    }

    private void validateIngredient(String ingredient) {
        if(!this.storage.containsKey(ingredient)) {
            throw new IllegalArgumentException(
                    String.format(
                            "Cannot find [%s] in the storage",
                            ingredient
                    )
            );
        }
    }

    private void validateDeduction(int deduction) {
        if(deduction < 0) {
            throw new IllegalArgumentException(
                    String.format(
                            "Cannot have negative deduction"
                    )
            );
        }
    }
}
