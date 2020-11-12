package com.blz.censusanalyser;

import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class StateCensusAnalyserTest {
	private static final String INDIA_CENSUS_CSV_FILE_PATH = "./src/test/resources/IndiaStateCensusData.csv";
	private static final String WRONG_CSV_FILE_PATH = "./src/main/resources/IndiaStateCensusData.csv";
	private static final String WRONG_CSV_FILE_TYPE = "./src/test/resources/IndiaStateCensusData - pdf.pdf";
	private static final String WRONG_CSV_FILE_DELIMITER = "./src/test/resources/IndiaStateCensusData-pipe delimiter.csv";
	private static final String WRONG_CSV_FILE_HEADER = "./src/test/resources/IndiaStateCensusData - diff header.csv";

	static StateCensusAnalyser stateCensusAnalyser;

	@BeforeClass
	public static void createObj() {
		stateCensusAnalyser = new StateCensusAnalyser();
	}

	@AfterClass
	public static void nullObj() {
		stateCensusAnalyser = null;
	}

	@Test
	public void givenIndiaStateCensusCSVFile_ShouldReturnCorrectRecords() throws CensusAnalyserException {
		int numOfRecords = stateCensusAnalyser.loadStateCensusData(INDIA_CENSUS_CSV_FILE_PATH);
		assertEquals(29, numOfRecords);
	}

	@Test
	public void givenIndiaStateCensusCSV_WhenWrongFile_ShouldThrowException() {
		try {
			ExpectedException exceptionRule = ExpectedException.none();
			exceptionRule.expect(CensusAnalyserException.class);
			stateCensusAnalyser.loadStateCensusData(WRONG_CSV_FILE_PATH);
		} catch (CensusAnalyserException e) {
			assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
		}
	}

	@Test
	public void givenIndiaStateCensusCSV_WhenWrongFileType_ShouldThrowException() throws CensusAnalyserException{

		int numOfRecords;
		try {
			numOfRecords = stateCensusAnalyser.loadStateCensusData(WRONG_CSV_FILE_TYPE);
			assertEquals(29, numOfRecords);
		} catch (RuntimeException e) {
			e.printStackTrace();
		}

	}

	@Test
	public void givenIndiaStateCensusCSV_WhenWrongDelimiter_ShouldThrowException() throws CensusAnalyserException {
		try {
			int numOfRecords = stateCensusAnalyser.loadStateCensusData(WRONG_CSV_FILE_DELIMITER);
			assertEquals(29, numOfRecords);
		}catch(RuntimeException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void givenIndiaStateCensusCSV_WhenIncorrectHeader_ShouldThrowException() throws CensusAnalyserException  {
		try {
			int numOfRecords = stateCensusAnalyser.loadStateCensusData(WRONG_CSV_FILE_HEADER);
			assertEquals(29, numOfRecords);
		}catch(RuntimeException e) {
			e.printStackTrace();
		}
		
	}

}