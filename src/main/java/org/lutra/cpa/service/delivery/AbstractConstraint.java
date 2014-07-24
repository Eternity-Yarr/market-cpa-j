package org.lutra.cpa.service.delivery;

import org.lutra.cpa.model.DeliveryOption;
import org.lutra.cpa.request.post.Cart;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Callable;

/**
 * 24.07.2014 at 17:05
 * AbstractConstraint of market-cpa project
 *
 * @author Dmitry V. (savraz [at] gmail.com)
 */
public class AbstractConstraint  implements Callable<Set<DeliveryOption>>
{
	final protected Cart rx;
	public AbstractConstraint(Cart rx)
	{
		this.rx = rx;
	}

	@Override
	public Set<DeliveryOption> call() throws Exception
	{
		return new HashSet<>();
	}
}
