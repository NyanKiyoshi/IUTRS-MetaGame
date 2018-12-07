package models.Exceptions;

/**
 * The requested character was not found.
 */
public class PersonneInexistanteException extends Exception {
    public PersonneInexistanteException(String message) {
        super(message);
    }
}
