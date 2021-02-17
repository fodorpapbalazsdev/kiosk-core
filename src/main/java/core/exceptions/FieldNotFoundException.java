package core.exceptions;

public class FieldNotFoundException extends Exception {
    public FieldNotFoundException(String fieldName) {
        super("Field with name " + fieldName + " not found.");
    }
}
