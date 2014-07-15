package org.lutra.ems.response;

import org.lutra.ems.Location;

import java.util.List;
import java.util.Map;

/**
 * 15.07.2014 at 12:32
 * Response of market-cpa project
 *
 * @author Dmitry V. (savraz [at] gmail.com)
 */
public class Response
{
	public String stat;
	public Err err;
	public String msg;
	public List<Location> locations;
	public String price;
	public String max_weight;
	public Map<String,String> term;
}
