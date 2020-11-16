package com.blz.censusanalyser;

public class CSVBuilderFactory {
	public static ICSVBuilder creteCSVBuilder() {
		return new OpenCSVBuilder();
	}
}
