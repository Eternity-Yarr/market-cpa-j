package org.lutra.cpa.request.post;

import org.junit.Test;
import org.lutra.cpa.Db;
import org.lutra.cpa.Helpers;
import org.lutra.cpa.Main;
import org.lutra.cpa.response.CartResponse;
import org.lutra.cpa.service.CartService;

import java.net.URL;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.assertNotNull;

/**
 * Serialize/deserialize test of POST /cart request object
 */
public class CartTest
{
	@Test
	public void testCartRequestDeserialize() throws Exception
	{
			URL url = CartTest.class.getResource("cartRequest.json");
			String json = Helpers.readFile(url, StandardCharsets.UTF_8);
			Cart c = Main.g.fromJson(json, CartRequest.class).uw();
			assertNotNull(c);
	}

	@Test
	public void testCartRequestProcess1() throws Exception
	{
		Db.setupDriver();
		URL url = CartTest.class.getResource("realCartRequest1.json");
		String json = Helpers.readFile(url, StandardCharsets.UTF_8);
		CartRequest rx = Main.g.fromJson(json, CartRequest.class);
		CartResponse response = CartService.process(rx);
		System.out.println(Main.g.toJson(response));
	}

	@Test
	public void testCartRequestProcess2() throws Exception
	{
		Db.setupDriver();
		URL url = CartTest.class.getResource("realCartRequest2.json");
		String json = Helpers.readFile(url, StandardCharsets.UTF_8);
		CartRequest rx = Main.g.fromJson(json, CartRequest.class);
		CartResponse response = CartService.process(rx);
		System.out.println(Main.g.toJson(response));
	}

	@Test
	public void testCartRequestProcess3() throws Exception
	{
		Db.setupDriver();
		URL url = CartTest.class.getResource("realCartRequest3.json");
		String json = Helpers.readFile(url, StandardCharsets.UTF_8);
		CartRequest rx = Main.g.fromJson(json, CartRequest.class);
		CartResponse response = CartService.process(rx);
		System.out.println(Main.g.toJson(response));
	}
}