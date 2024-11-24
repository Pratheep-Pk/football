package com.example.football_standings.exception;

public class ApiException extends RuntimeException {

	private static final long serialVersionUID = 771587977366802762L;

	public ApiException(String message) {
        super(message);
    }

    public ApiException(String message, Throwable cause) {
        super(message, cause);
    }
}