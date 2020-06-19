package Oferta.exceptions;

public class OfertaAlreadyExistsException extends Exception{

    public OfertaAlreadyExistsException()   {
        super(String.format("Oferta exista deja!"));

    }
}
