package org.lutra.cpa.wrapper;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Helper;
import com.github.jknack.handlebars.Options;
import org.lutra.cpa.Helpers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class MyHandlebars extends Handlebars
{
	private static Logger log = LoggerFactory.getLogger(MyHandlebars.class);
	private static NumberFormat nf = NumberFormat.getNumberInstance(Locale.forLanguageTag("RU"));
	private static DecimalFormat df = (DecimalFormat)nf;
	public MyHandlebars()
	{
		super(new MyFileTemplateLoader());
		this.registerHelper(
			"currency",
			new Helper<Double>()
			{
				public CharSequence apply(Double price, Options options)
				{

					return df.format(price);
				}
			});

		this.registerHelper(
			"css",
			new Helper<String>()
			{
				public CharSequence apply(String path, Options options)
				{
					String ret = "";
					try
					{
						ret = Helpers.readFile(String.format("./var%s", path), StandardCharsets.UTF_8);
					}
					catch(Exception e)
					{
						log.error(e.toString(), e);
					}

					if(!ret.isEmpty())
						ret = "<style>" + ret + "</style>";

					return ret;
				}
			});
	}

}
