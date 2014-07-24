package org.lutra.cpa.service;

import org.lutra.cpa.model.DeliveryOption;
import org.lutra.cpa.repository.DeliveryRepository;
import org.lutra.cpa.request.post.Cart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;

public class DeliveryService
{
	private final Logger log = LoggerFactory.getLogger(DeliveryService.class);
	static DeliveryRepository dr = new DeliveryRepository(); //TODO: interface

	List<Class<Callable<Set<DeliveryOption>>>> constraintClasses;

	public static List<DeliveryOption> getAll()
	{
		return dr.getAll();
	}

	public void addConstraint(Class<Callable<Set<DeliveryOption>>> constraint)
	{
		constraintClasses.add(constraint);
	}

	public List<DeliveryOption> filterFeasible(Cart rx)
	{
		Set<DeliveryOption> ret = new HashSet<>(getAll());

		List<Set<DeliveryOption>> options = new ArrayList<>();
		for(Class<Callable<Set<DeliveryOption>>> clazz : constraintClasses)
		{
			try
			{
				Callable<Set<DeliveryOption>> constraint = clazz
					.getConstructor(rx.getClass())
					.newInstance(rx);
				options.add(constraint.call());
			}
			catch(Exception e)
			{
				log.error(e.toString(), e);
			}
		}
		for(Set<DeliveryOption> option : options)
		{
			ret.retainAll(option);
		}

		return new ArrayList<>(ret);
	}
}
