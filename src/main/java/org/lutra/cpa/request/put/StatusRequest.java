package org.lutra.cpa.request.put;

public class StatusRequest
{
	private Status status;
	public Status uw()
	{
		return status;
	}

	public StatusRequest setStatus(Status status)
	{
		this.status = status;

		return this;
	}
}
