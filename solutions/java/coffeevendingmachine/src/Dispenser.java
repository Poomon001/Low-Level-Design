public class Dispenser {
    public void serve(Coffee recipe) {
        System.out.println(String.format("Dispensing: %s", recipe.getName()));
    }
}
