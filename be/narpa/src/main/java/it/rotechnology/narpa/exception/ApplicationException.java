package it.rotechnology.narpa.exception;

import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

public class ApplicationException extends ResponseStatusException {

	private static final long serialVersionUID = -4891321462761471242L;

	public ApplicationException(HttpStatusCode status, String reason, Throwable cause, String messageDetailCode,
			Object[] messageDetailArguments) {
		super(status, reason, cause, messageDetailCode, messageDetailArguments);
	}

	public ApplicationException(HttpStatusCode status, String reason, Throwable cause) {
		super(status, reason, cause);
	}

	public ApplicationException(HttpStatusCode status, String reason) {
		super(status, reason);
	}

	public ApplicationException(HttpStatusCode status) {
		super(status);
	}

	public ApplicationException(int rawStatusCode, String reason, Throwable cause) {
		super(rawStatusCode, reason, cause);
	}

	
	
}
