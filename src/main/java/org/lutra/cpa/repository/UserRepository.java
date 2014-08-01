package org.lutra.cpa.repository;

import org.lutra.cpa.model.User;

/**
 * 01.08.2014 at 19:07
 * UserRepository of market-cpa project
 *
 * @author Dmitry V. (savraz [at] gmail.com)
 */
public interface UserRepository
{
	/**
	 * @param user_id user id
	 * @return user for given id
	 */
	User get(int user_id);
}
