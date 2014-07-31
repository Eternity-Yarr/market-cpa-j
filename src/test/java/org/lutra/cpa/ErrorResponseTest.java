package org.lutra.cpa;

import org.junit.Test;
import org.lutra.cpa.response.get.OrderResponse;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * 31.07.2014 at 19:27
 * ErrorResponseTest of market-cpa project
 *
 * @author Dmitry V. (savraz [at] gmail.com)
 */
public class ErrorResponseTest
{
	@Test
	public void testErrorResponseDeserialization()
	{
		String jsonError = "{\"error\":{\"code\":400,\"message\":\"Order 108913 with status CANCELLED is not allowed for status CANCELLED\"}}";
		String jsonNoError = "{\"order\":{\"id\":108913,\"status\":\"CANCELLED\",\"substatus\":\"USER_UNREACHABLE\",\"creationDate\":\"31-07-2014 18:02:36\",\"currency\":\"RUR\",\"itemsTotal\":2330,\"total\":2520,\"paymentType\":\"POSTPAID\",\"paymentMethod\":\"CASH_ON_DELIVERY\",\"fake\":false,\"items\":[{\"feedId\":97,\"offerId\":\"4993\",\"feedCategoryId\":\"239\",\"offerName\":\"Внешний HDD Western Digital My Passport Ultra WDBJNZ0010BRD-EEUE [USB 3.0/1 Tb/красный]\",\"price\":2330,\"count\":1}]}}";

		OrderResponse orErr = Main.g.fromJson(jsonError, OrderResponse.class);
		OrderResponse orNoErr = Main.g.fromJson(jsonNoError, OrderResponse.class);

		assertEquals("Code is correct", orErr.error.code, 400);
		assertEquals("Message is correct", orErr.error.message, "Order 108913 with status CANCELLED is not allowed for status CANCELLED");
		assertTrue("Erroneous object hasError is true", orErr.hasError());
		assertNull("Error is null on correct object", orNoErr.error);
		assertTrue("hasError is false on correct object",!orNoErr.hasError());
	}
}
