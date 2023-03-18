package ru.liderIt.SportTeamStorage.exceptions;

public class ConflictException extends RuntimeException {
    public ConflictException(String s) {
        super(s);
    }
}