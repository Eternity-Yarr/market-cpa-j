package org.lutra.cpa.model;

public class Pager
{
	public int total;
	public int from;
	public int to;
	public int pageSize;
	public int pagesCount;
	public int currentPage;

	public boolean isFirst()
	{
		return currentPage == 1;
	}

	public boolean isLast()
	{
		return currentPage == pagesCount;
	}

	public int nextPage()
	{
		return currentPage + 1 <= pagesCount ? currentPage + 1 : currentPage;
	}

	public int previousPage()
	{
		return currentPage - 1 >= 1  ? currentPage - 1 : 1;
	}
}
