package uz.pdp.appproblemsolver.exception;

public class TokenExpiredOrInvalid extends RuntimeException {
    public TokenExpiredOrInvalid(String message) {
        super(message);
    }
}
