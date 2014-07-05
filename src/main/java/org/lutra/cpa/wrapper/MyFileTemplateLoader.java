package org.lutra.cpa.wrapper;

import com.github.jknack.handlebars.io.FileTemplateLoader;

/**
 * 21.05.2014 at 15:06
 * mobile.Overrides.MyFileTemplateLoader of m.c2y project
 *
 * @author Dmitry V. (savraz [at] gmail.com)
 */
public class MyFileTemplateLoader extends FileTemplateLoader
{
	public MyFileTemplateLoader()
	{
		super("./");
		this.setPrefix("assets/");
		this.setSuffix(".hbs");
	}
}
