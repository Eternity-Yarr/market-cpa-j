package org.lutra.cpa.request.put;

public class StatusRequest
{
	private Status order;
	public Status uw()
	{
		return order;
	}

	public StatusRequest setStatus(Status order)
	{
		this.order = order;

		return this;
	}
}
