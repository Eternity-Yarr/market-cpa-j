package org.lutra.cpa.repository;

/**
 * 01.08.2014 at 19:06
 * AuthorizationRepository of market-cpa project
 *
 * @author Dmitry V. (savraz [at] gmail.com)
 */
public interface AuthorizationRepository
{
	/**
	 * @return hash-string to be used as cookie value
	 */
	String generateToken();
	/**
	 * Tries to authorize user with given credentials
	 * @param login login of user
	 * @param password password of user
	 * @return user id or -1 if user not found
	 */
	int authorized(String login, String password);
}
