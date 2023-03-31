package uz.pdp.appproblemsolver.exception;

public class UsernameOrEmailAlreadyExists extends RuntimeException {
    public UsernameOrEmailAlreadyExists(String message) {
        super(message);
    }
}