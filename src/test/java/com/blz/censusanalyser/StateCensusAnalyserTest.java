package com.blz.censusanalyser;

import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.google.gson.Gson;

public class StateCensusAnalyserTest {
	private static final String INDIA_CENSUS_CSV_FILE_PATH = "./src/test/resources/IndiaStateCensusData.csv";
	private static final String CENSUS_WRONG_CSV_FILE_PATH = "./src/main/resources/IndiaStateCensusData.csv";
	private static final String CENSUS_WRONG_CSV_FILE_TYPE = "./src/test/resources/IndiaStateCensusData - pdf file.pdf";
	private static final String CENSUS_WRONG_CSV_FILE_DELIMITER = "./src/test/resources/IndiaStateCensusData-pipe delimiter.csv";
	private static final String CENSUS_WRONG_CSV_FILE_HEADER = "./src/test/resources/IndiaStateCensusData - diff header.csv";

	private static final String INDIA_STATE_CODE_CSV_FILE_PATH = "./src/test/resources/IndiaStateCode.csv";
	private static final String CODE_WRONG_CSV_FILE_PATH = "./src/main/resources/IndiaStateCode.csv";
	private static final String CODE_WRONG_CSV_FILE_TYPE = "./src/test/resources/IndiaStateCode - pdf.pdf";
	private static final String CODE_WRONG_CSV_FILE_DELIMITER = "./src/test/resources/IndiaStateCode - pipe delimiter.csv";
	private static final String CODE_WRONG_CSV_FILE_HEADER = "./src/test/resources/IndiaStateCode - diff header.csv";

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
			stateCensusAnalyser.loadStateCensusData(CENSUS_WRONG_CSV_FILE_PATH);
		} catch (CensusAnalyserException e) {
			assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
		}
	}

	@Test
	public void givenIndiaStateCensusCSV_WhenWrongFileType_ShouldThrowException() throws CensusAnalyserException {

		int numOfRecords;
		try {
			numOfRecords = stateCensusAnalyser.loadStateCensusData(CENSUS_WRONG_CSV_FILE_TYPE);
			assertEquals(29, numOfRecords);
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void givenIndiaStateCensusCSV_WhenWrongDelimiter_ShouldThrowException() throws CensusAnalyserException {
		try {
			int numOfRecords = stateCensusAnalyser.loadStateCensusData(CENSUS_WRONG_CSV_FILE_DELIMITER);
			assertEquals(29, numOfRecords);
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void givenIndiaStateCensusCSV_WhenIncorrectHeader_ShouldThrowException() throws CensusAnalyserException {
		try {
			int numOfRecords = stateCensusAnalyser.loadStateCensusData(CENSUS_WRONG_CSV_FILE_HEADER);
			assertEquals(29, numOfRecords);
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void givenIndiaStateCodeCSVFile_ShouldReturnCorrectRecords() throws CensusAnalyserException {
		int numOfRecords = stateCensusAnalyser.loadStateCodeData(INDIA_STATE_CODE_CSV_FILE_PATH);
		assertEquals(37, numOfRecords);
	}

	@Test
	public void givenIndiaStateCodeCSV_WhenWrongFile_ShouldThrowException() {
		try {
			ExpectedException exceptionRule = ExpectedException.none();
			exceptionRule.expect(CensusAnalyserException.class);
			stateCensusAnalyser.loadStateCodeData(CODE_WRONG_CSV_FILE_PATH);
		} catch (CensusAnalyserException e) {
			assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
		}
	}

	@Test
	public void givenIndiaStateCodeCSV_WhenWrongFileType_ShouldThrowException() throws CensusAnalyserException {

		int numOfRecords;
		try {
			numOfRecords = stateCensusAnalyser.loadStateCodeData(CODE_WRONG_CSV_FILE_TYPE);
			assertEquals(35, numOfRecords);
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void givenIndiaStateCodeCSV_WhenWrongDelimiter_ShouldThrowException() throws CensusAnalyserException {
		try {
			int numOfRecords = stateCensusAnalyser.loadStateCodeData(CODE_WRONG_CSV_FILE_DELIMITER);
			assertEquals(35, numOfRecords);
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void givenIndiaStateCodeCSV_WhenIncorrectHeader_ShouldThrowException() throws CensusAnalyserException {
		try {
			int numOfRecords = stateCensusAnalyser.loadStateCodeData(CODE_WRONG_CSV_FILE_HEADER);
			assertEquals(35, numOfRecords);
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void givenIndiaStateCensusCSV_WhenSortByStates_ShouldReturnSortedList() {
		try {
			stateCensusAnalyser.loadStateCensusData(INDIA_CENSUS_CSV_FILE_PATH);
			String sortedCensusData = stateCensusAnalyser.getStateWiseSortedCensusData();
			CSVStateCensus[] censusCSV = new Gson().fromJson(sortedCensusData, CSVStateCensus[].class);
			assertEquals("Andhra Pradesh", censusCSV[0].state);
			assertEquals("West Bengal", censusCSV[28].state);
		} catch (CensusAnalyserException e) {
		}
	}

	@Test
	public void givenIndiaStateCodeCSV_WhenSortByStateCode_ShouldReturnSortedListOfStates() {
		try {
			stateCensusAnalyser.loadStateCodeData(INDIA_STATE_CODE_CSV_FILE_PATH);
			String sortedCodeData = stateCensusAnalyser.getStateCodeWiseSortedData();
			CSVStateCode[] codeCSV = new Gson().fromJson(sortedCodeData, CSVStateCode[].class);
			assertEquals("Andhra Pradesh New", codeCSV[0].getStateName());
			assertEquals("West Bengal", codeCSV[36].getStateName());
		} catch (CensusAnalyserException e) {
		}
	}

	@Test
	public void givenIndiaStateCensusCSV_WhenSortByPopulation_ShouldReturnSortedList() {
		try {
			stateCensusAnalyser.loadStateCensusData(INDIA_CENSUS_CSV_FILE_PATH);
			String reverseSortedCensusByPopulationData = stateCensusAnalyser.getPopulationWiseSortedCensusData();
			CSVStateCensus[] censusPopulationCSV = new Gson().fromJson(reverseSortedCensusByPopulationData,
					CSVStateCensus[].class);
			assertEquals("Uttar Pradesh", censusPopulationCSV[0].state);
			assertEquals("Sikkim", censusPopulationCSV[28].state);
		} catch (CensusAnalyserException e) {
		}
	}

	@Test
	public void givenIndiaStateCensusCSV_WhenSortByDensity_ShouldReturnSortedList() {
		try {
			stateCensusAnalyser.loadStateCensusData(INDIA_CENSUS_CSV_FILE_PATH);
			String reverseSortedCensusByDensityPopulationData = stateCensusAnalyser.getDensityWiseSortedCensusData();
			CSVStateCensus[] censusDensityCSV = new Gson().fromJson(reverseSortedCensusByDensityPopulationData,CSVStateCensus[].class);
			assertEquals("Bihar", censusDensityCSV[0].state);
			assertEquals("Arunachal Pradesh", censusDensityCSV[28].state);
		} catch (CensusAnalyserException e) {
		}
	}
}
