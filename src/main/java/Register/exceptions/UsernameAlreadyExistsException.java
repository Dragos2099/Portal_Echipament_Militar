package Register.exceptions;

public class UsernameAlreadyExistsException extends Exception {
    private String username;

    public UsernameAlreadyExistsException(String username)   {
        super(String.format("Numele %s este deja folosit", username));
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
