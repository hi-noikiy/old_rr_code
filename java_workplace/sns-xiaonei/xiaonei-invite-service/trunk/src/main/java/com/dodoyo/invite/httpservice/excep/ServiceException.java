package com.dodoyo.invite.httpservice.excep;

public class ServiceException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 6606972377102827727L;

	public ServiceException(String message) {
        super(message);
        ExceptionManager.getInstance().logServiceException(this, message, null);
    }

    public ServiceException() {
        super();
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
        ExceptionManager.getInstance().logServiceException(this, message, cause);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }

    public Throwable initCause(Throwable cause) {
        return cause;
    }
}