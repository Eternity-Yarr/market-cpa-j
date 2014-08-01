package org.lutra.cpa.service;

import org.lutra.cpa.model.User;
import org.lutra.cpa.repository.BitrixUserRepository;
import org.lutra.cpa.repository.UserRepository;

/**
 * 29.07.2014 at 14:48
 * UserService of market-cpa project
 *
 * @author Dmitry V. (savraz [at] gmail.com)
 */
public class UserService
{
	// final private static Logger log = LoggerFactory.getLogger(UserService.class);
	final private static UserRepository ur = BitrixUserRepository.i();
	private static UserService instance;

	public static UserService i()
	{
		if(instance == null)
			instance = new UserService();

		return instance;
	}

	public User get(int user_id)
	{
		return ur.get(user_id);
	}
}
