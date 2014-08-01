package org.lutra.cpa.repository;

import org.apache.commons.codec.binary.Hex;
import org.lutra.cpa.Db;
import org.lutra.cpa.Helpers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Arrays;

/**
 * 31.07.2014 at 12:56
 * BitrixAuthorizationRepository of market-cpa project
 *
 * @author Dmitry V. (savraz [at] gmail.com)
 */
public class BitrixAuthorizationRepository implements AuthorizationRepository
{
	final private static Logger log = LoggerFactory.getLogger(BitrixAuthorizationRepository.class);
	final static SecureRandom r = new SecureRandom();
	final static MessageDigest md;
	static
	{
		MessageDigest m = null;
		try
		{
			m = MessageDigest.getInstance("MD5");
		}
		catch(Exception ex)
		{
			log.warn("Can't generate MD5, falling back to plain integers");
		}
		md = m;
	}

	private static AuthorizationRepository instance;
	public static AuthorizationRepository i()
	{
		if(instance == null)
			instance = new BitrixAuthorizationRepository();

		return instance;
	}

	@Override
	public String generateToken()
	{
		String hash;
		String sid  = String.valueOf(r.nextInt());
		if (md != null)
			hash = String.valueOf(Hex.encodeHex(md.digest(sid.getBytes(StandardCharsets.UTF_8))));
		else
			hash = String.valueOf(sid);

		return hash;
	}

	@Override
	public int authorized(String email, String password)
	{
		int ret = -1;
/*

SELECT id, password, group_id
FROM b_user AS bu
RIGHT JOIN b_user_group AS bug ON bu.id = bug.user_id
WHERE login = ? AND group_id = 1 AND active = 'Y'
*/
		String q =
			"SELECT id, password, group_id\n" +
				"FROM b_user AS bu\n" +
				"RIGHT JOIN b_user_group AS bug ON bu.id = bug.user_id\n" +
				"WHERE login = ? AND group_id = 1 AND active = 'Y'";
		Object[] params = {email};
		if(email != null && password != null)
			try
			(
				Connection con = Db.ds.getConnection();
				PreparedStatement ps = Helpers.createStatement(con, q, params);
				ResultSet rs = ps.executeQuery()
			)
			{
				if(rs.next())
				{
					String saltnhash = rs.getString("password");
					String salt = saltnhash.substring(0, 8);
					String hash_string = saltnhash.substring(8);
					byte[] hash = Hex.decodeHex(hash_string.toCharArray());
					boolean match = Arrays.equals(hash, (md.digest((salt + password).getBytes(StandardCharsets.UTF_8))));
					if(match)
						ret = rs.getInt("id");
				}
				else
					log.info("No such user {}", email);
			}
			catch(Exception e)
			{
				log.error(e.toString(), e);
			}
		else
			log.info("Not enough credentials to check authorization");

		return ret;
	}
}
