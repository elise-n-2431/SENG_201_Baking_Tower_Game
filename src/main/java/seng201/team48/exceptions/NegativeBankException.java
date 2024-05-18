package seng201.team48.exceptions;

/**
 * Used in decreaseBank method in Inventory class.
 * Is thrown if amount to be subtracted is greater than current balance.
 */

public class NegativeBankException extends Exception {
    public NegativeBankException() {
        super();
    }

    public NegativeBankException(String message) {
        super(message);
    }
}
