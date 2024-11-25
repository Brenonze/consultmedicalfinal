package br.com.brenofranco.medicalconsult.exception;

public class ExceptionDataIntegrityViolation extends RuntimeException{
    public ExceptionDataIntegrityViolation(String message) {
        super(message);
    }
}
