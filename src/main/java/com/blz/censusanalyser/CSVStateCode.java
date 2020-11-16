package com.blz.censusanalyser;

import com.opencsv.bean.CsvBindByName;

public class CSVStateCode {
	@CsvBindByName(column = "SrNo", required = true)
	private int srNo;

	@CsvBindByName(column = "State Name", required = true)
	private String stateName;

	@CsvBindByName(column = "TIN", required = true)
	private int tin;

	@CsvBindByName(column = "StateCode", required = true)
	private String stateCode;

	public String getStateCode() {
		return stateCode;
	}

	public String getStateName() {
		return stateName;
	}

	@Override
	public String toString() {
		return "IndiaStateCodeCSV{" + "SrNo='" + srNo + '\'' + ", State Name='" + stateName + '\'' + ", TIN='" + tin
				+ '\'' + ", State Code='" + stateCode + '\'' + '}';
	}
}