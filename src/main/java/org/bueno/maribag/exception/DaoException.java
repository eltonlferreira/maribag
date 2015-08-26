package org.bueno.maribag.exception;

@SuppressWarnings("serial")
public class DaoException extends Exception {
	/**
	 * Método que gera uma DaoException
	 * 
	 * @param message
	 *            Mensagem em texto da descrição do erro
	 */
	public DaoException(String message) {
		super(message);
	}

	/**
	 * Método que gera uma DaoException
	 * 
	 * @param message
	 *            Mensagem em texto da descrição do erro
	 * @param cause
	 *            Exception que para anexar a nova excetion, mantendo o
	 *            stacktrace
	 */
	public DaoException(String message, Throwable cause) {
		super(message, cause);
	}
}
