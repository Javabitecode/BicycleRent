package trokhimchuk.bicycle.exception;

public class BicycleDoesNotBelongUser extends Exception{
    public BicycleDoesNotBelongUser(String message) {
        super(message);
    }
}
