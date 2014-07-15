package org.lutra.ems;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * 15.07.2014 at 11:48
 * emsTest of market-cpa project
 *
 * @author Dmitry V. (savraz [at] gmail.com)
 */
public class emsTest
{
	EMSService es;

	@Before
	public void setUp()
	{
		es = new EMSService();
	}

	@Test
	public void connectionTest()
	{
		Assert.assertTrue(es.testConnection());
	}

	@Test
	public void deliveryTest()
	{
		Location from = es.findLocation("Москва");
		Location to = es.findLocation("Воронеж");
		int price = es.getPrice(from, to, 5);
		Assert.assertEquals(price, 790);
	}


}
