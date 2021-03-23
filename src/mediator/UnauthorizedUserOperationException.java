package mediator;

public class UnauthorizedUserOperationException extends Exception {
    public UnauthorizedUserOperationException() {
        super("Product is not found in inventories!!");
    }
    public UnauthorizedUserOperationException(String message) {
        super(message);
    }
	
}
