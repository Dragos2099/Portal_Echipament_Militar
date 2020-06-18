package Cerere.exceptions;

public class CerereAlreadyExistsException extends Exception {
    public CerereAlreadyExistsException()   {
        super(String.format("Cererea exista deja!"));

    }
}
