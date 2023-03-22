package it.rotechnology.narpa.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

public enum AppError {

	PROFILE_NOT_EXISTS(HttpStatus.NOT_FOUND),
	ROLE_NOT_EXISTS(HttpStatus.NOT_FOUND),
	USER_NOT_EXISTS(HttpStatus.NOT_FOUND),
	PROFILE_ALREADY_EXISTS(HttpStatus.BAD_REQUEST),
	;
	
	private final HttpStatusCode defaultStatusCode;

	private AppError(HttpStatusCode defaultStatusCode) {
		this.defaultStatusCode = defaultStatusCode;
	}

	public HttpStatusCode getDefaultStatusCode() {
		return defaultStatusCode;
	}

}
