package it.rotechnology.narpa.service;

import java.util.Locale;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatusCode;

import it.rotechnology.narpa.exception.AppError;
import it.rotechnology.narpa.exception.ApplicationException;

public class AbstractService {

	@Autowired
	private MessageSource messageSource;

	protected ApplicationException makeError(AppError error, Object... args) {
		return makeError(null, null, error, args);
	}
	
	protected ApplicationException makeError(Logger logger, AppError error, Object... args) {
		return makeError(logger, null, error, args);
	}
	
	protected ApplicationException makeError(Logger logger, HttpStatusCode httpStatusCode, AppError error, Object... args) {
		//String errorCode = getMessageSource().getMessage(error.name() + ".code", null, error.name(), Locale.getDefault());
		String errorMessage = errorMessage(error, args);
		String errorLogMessage = errorLogMessage(error, args);
		if (logger != null) {
			logger.error(errorLogMessage == null ? errorMessage : errorLogMessage);
		}
		
		return new ApplicationException(httpStatusCode, error, args, null);
		
	}

	protected String errorMessage(AppError error, Object... args) {
		String errorCode = getMessageSource().getMessage(error.name() + ".code", null, error.name(), LocaleContextHolder.getLocale());
		String message = getMessageSource().getMessage(error.name(), args, null, Locale.getDefault());
		if (message == null) {
			return "[[" + errorCode + "]]";
		}
		return message;
	}
	
	protected String errorLogMessage(AppError error, Object... args) {
		 return getMessageSource().getMessage(error.name() + ".log", args, null, Locale.getDefault());
	}
	
	protected MessageSource getMessageSource() {
		return messageSource;
	}

}
