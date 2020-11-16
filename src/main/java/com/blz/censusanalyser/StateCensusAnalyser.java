package com.blz.censusanalyser;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import com.google.gson.Gson;

public class StateCensusAnalyser {
	static List<CSVStateCensus> censusCSVList;
	public int loadStateCensusData(String csvFilePath) throws CensusAnalyserException {
		try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
			ICSVBuilder csvBuilder = CSVBuilderFactory.creteCSVBuilder();
			censusCSVList= csvBuilder.getCSVFileList(reader, CSVStateCensus.class);
			return censusCSVList.size();
		} catch (IOException e) {
			throw new CensusAnalyserException(e.getMessage(),
					CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
		} catch (CSVBuilderException e) {
			throw new CensusAnalyserException(e.getMessage(), e.type.name());
		}
	}

	public int loadStateCodeData(String csvFilePath) throws CensusAnalyserException {
		try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
			ICSVBuilder csvBuilder = CSVBuilderFactory.creteCSVBuilder();
			List<CSVStateCode> codeCSVList = csvBuilder.getCSVFileList(reader, CSVStateCode.class);
			return codeCSVList.size();
		} catch (IOException e) {
			throw new CensusAnalyserException(e.getMessage(),
					CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
		} catch (CSVBuilderException e) {
			throw new CensusAnalyserException(e.getMessage(), e.type.name());
		}
	}

	private <E> int getCount(Iterator<E> iterator) {
		int numberOfRecords = 0;
		while (iterator.hasNext()) {
			numberOfRecords++;
			iterator.next();
		}
		return numberOfRecords;
	}

	public String getStateWiseSortedCensusData() {
		List<CSVStateCensus> sortByStateList = censusCSVList.stream()
				.sorted(Comparator.comparing(CSVStateCensus::getState)).collect(Collectors.toList());
		String sortByStateCensusJson = new Gson().toJson(sortByStateList);
		return sortByStateCensusJson;
	}
	

}
