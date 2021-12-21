package com.exception;

import org.springframework.security.core.AuthenticationException;

public class AuthenException extends AuthenticationException{

	/**
	 * Constructs a <code>UsernameNotFoundException</code> with the specified message.
	 * @param msg the detail message.
	 */
	public AuthenException(String msg) {
		super(msg);
	}

	/**
	 * Constructs a {@code UsernameNotFoundException} with the specified message and root
	 * cause.
	 * @param msg the detail message.
	 * @param cause root cause
	 */
	public AuthenException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
