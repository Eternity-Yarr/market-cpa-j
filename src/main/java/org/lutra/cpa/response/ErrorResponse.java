package org.lutra.cpa.response;

/**
 * 30.07.2014 at 18:09
 * Error of market-cpa project
 *
 * @author Dmitry V. (savraz [at] gmail.com)
 */
public class ErrorResponse
{
	public Error error;
	public boolean hasError()
	{
		return error == null;
	}
}
