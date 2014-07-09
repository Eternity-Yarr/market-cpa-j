package org.lutra.cpa.model;

public enum OutletType
{
	RETAIL("Розничная точка продаж"),
	DEPOT("Склад"),
	MIXED("Точка и склад"),
	NOT_DEFINED("Неопределенный тип");

	final String desc;
	OutletType(String desc)
	{
		this.desc = desc;
	}
}
