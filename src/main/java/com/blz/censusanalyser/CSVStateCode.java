package com.blz.censusanalyser;

import com.opencsv.bean.CsvBindByName;

public class CSVStateCode {
	@CsvBindByName(column = "SrNo", required = true)
	public int srNo;

	@CsvBindByName(column = "State Name", required = true)
	public String stateName;

	@CsvBindByName(column = "TIN", required = true)
	public int tin;

	@CsvBindByName(column = "StateCode", required = true)
	public int stateCode;

	@Override
	public String toString() {
		return "IndiaStateCodeCSV{" + "SrNo='" + srNo + '\'' + ", State Name='" + stateName + '\'' + ", TIN='" + tin
				+ '\'' + ", State Code='" + stateCode + '\'' + '}';
	}
}