package org.lutra.cpa.service;

import org.junit.Test;
import org.lutra.cpa.Db;
import org.lutra.cpa.Helpers;
import org.lutra.cpa.Main;
import org.lutra.cpa.model.DeliveryOption;
import org.lutra.cpa.request.post.Cart;
import org.lutra.cpa.request.post.CartRequest;
import org.lutra.cpa.service.delivery.RegionConstraint;

import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Set;

/**
 * 24.07.2014 at 14:20
 * DeliveryOptionsTest of market-cpa project
 *
 * @author Dmitry V. (savraz [at] gmail.com)
 */
public class DeliveryOptionsTest
{
	@Test
	public void deliveryTest() throws Exception
	{
		Db.setupDriver();
		URL url = DeliveryOptionsTest.class.getResource("../request/post/cartRequest.json");
		String json = Helpers.readFile(url, StandardCharsets.UTF_8);
		Cart c = Main.g.fromJson(json, CartRequest.class).uw();
		Set<DeliveryOption> xs = new RegionConstraint(c).call();
		System.out.println(xs);
	}
}
