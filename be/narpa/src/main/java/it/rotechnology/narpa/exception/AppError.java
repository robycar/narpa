package it.rotechnology.narpa.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

public enum AppError {

	PROFILE_NOT_EXISTS(HttpStatus.NOT_FOUND),
	
	ROLE_NOT_EXISTS(HttpStatus.NOT_FOUND)
	;
	
	private final HttpStatusCode defaultStatusCode;

	private AppError(HttpStatusCode defaultStatusCode) {
		this.defaultStatusCode = defaultStatusCode;
	}

	public HttpStatusCode getDefaultStatusCode() {
		return defaultStatusCode;
	}

}
