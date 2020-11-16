package com.blz.censusanalyser;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;

public class StateCensusAnalyser<T> {
	public int loadStateCensusData(String csvFilePath) throws CensusAnalyserException {
		try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
			Iterator<CSVStateCensus> censusCSVIterator = new OpenCSVBuilder().getCSVFileIterator(reader,
																								CSVStateCensus.class);
			return getCount(censusCSVIterator);
		} catch (IOException e) {
			throw new CensusAnalyserException(e.getMessage(),
					CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
		}
	}

	public int loadStateCodeData(String csvFilePath) throws CensusAnalyserException {
		try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
			Iterator<CSVStateCode> codeCSVIterator = new OpenCSVBuilder().getCSVFileIterator(reader,
																							CSVStateCode.class);
			return getCount(codeCSVIterator);
		} catch (IOException e) {
			throw new CensusAnalyserException(e.getMessage(),
				CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
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
}
