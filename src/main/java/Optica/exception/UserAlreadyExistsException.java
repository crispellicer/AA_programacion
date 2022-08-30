package Optica.exception;

public class UserAlreadyExistsException extends Exception {

    public UserAlreadyExistsException(String message) {
        super(message);
    }

    public UserAlreadyExistsException() { super("Ese username ya existe en la base de datos. Elige otro username"); }
}
