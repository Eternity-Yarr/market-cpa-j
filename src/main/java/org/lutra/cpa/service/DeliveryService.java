package org.lutra.cpa.service;

import org.lutra.cpa.model.DeliveryOption;
import org.lutra.cpa.repository.DeliveryRepository;
import org.lutra.cpa.request.post.Cart;
import org.lutra.cpa.service.delivery.AbstractConstraint;
import org.lutra.cpa.service.delivery.AmountConstraint;
import org.lutra.cpa.service.delivery.RegionConstraint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;

public class DeliveryService
{
	private final static Logger log = LoggerFactory.getLogger(DeliveryService.class);
	private static DeliveryService instance;

	static DeliveryRepository dr = new DeliveryRepository(); //TODO: interface
	private List<Class<? extends AbstractConstraint>> constraintClasses = new ArrayList<>();

	private DeliveryService()
	{
		addConstraint(RegionConstraint.class);
		addConstraint(AmountConstraint.class);
	}

	public static DeliveryService i()
	{
		if(instance == null)
			instance = new DeliveryService();

		return instance;
	}

	public static List<DeliveryOption> getAll()
	{
		return dr.getAll();
	}

	public void addConstraint(Class<? extends AbstractConstraint> constraint)
	{
		constraintClasses.add(constraint);
	}

	public List<DeliveryOption> getFeasible(Cart rx)
	{
		Set<DeliveryOption> ret = new HashSet<>(getAll());
		log.info("Filtering total {} options", ret.size());

		List<Set<DeliveryOption>> options = new ArrayList<>();
		for(Class<? extends AbstractConstraint> clazz : constraintClasses)
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
		for(Set<DeliveryOption> option : options)
			ret.retainAll(option);

		return new ArrayList<>(ret);
	}
}
