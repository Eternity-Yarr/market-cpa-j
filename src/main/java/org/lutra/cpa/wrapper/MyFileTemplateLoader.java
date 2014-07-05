package org.lutra.cpa.wrapper;

import com.github.jknack.handlebars.io.FileTemplateLoader;

public class MyFileTemplateLoader extends FileTemplateLoader
{
	public MyFileTemplateLoader()
	{
		super("./");
		this.setPrefix("assets/");
		this.setSuffix(".hbs");
	}
}
