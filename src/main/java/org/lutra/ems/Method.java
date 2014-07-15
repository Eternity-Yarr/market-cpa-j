package org.lutra.ems;

/**
 * 15.07.2014 at 11:52
 * Method of market-cpa project
 *
 * @author Dmitry V. (savraz [at] gmail.com)
 */
public enum Method
{
	TEST("ems.test.echo"),
	LOCATIONS("ems.get.locations"),
	MAX_WEIGHT("ems.get.max.weight"),
	CALCULATE("ems.calculate");

	Method(String identifier)
	{
		this.identifier = identifier;
	}

	final String identifier;
}
