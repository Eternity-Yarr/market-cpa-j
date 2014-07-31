package org.lutra.cpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * 31.07.2014 at 14:29
 * Mt of market-cpa project
 *
 * @author Dmitry V. (savraz [at] gmail.com)
 */
public class Mt
{
	final private static Logger log = LoggerFactory.getLogger(Mt.class);
	private static Executor ex = Executors.newFixedThreadPool(20);

	public static void execute(Runnable task)
	{
		ex.execute(task);
		log.info("Freed webbit thread");
	}
	private Mt(){} // No instances
}
