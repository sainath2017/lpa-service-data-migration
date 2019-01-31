package com.wfs.landpricing.util;

import static org.junit.Assert.assertTrue;

import java.util.EnumSet;
import java.util.List;

import org.junit.Test;

import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.wfs.landpricing.model.enums.PnrMethod;

public class CsvStringJsonToSetPnrMethodConverterTest {

	CsvStringJsonToSetPnrMethodConverter uut = new CsvStringJsonToSetPnrMethodConverter();
	
	@Test
	public void testConvertString() throws CsvDataTypeMismatchException, CsvConstraintViolationException {
		EnumSet<PnrMethod>  expectedResult =EnumSet.of(PnrMethod.EMAIL,PnrMethod.DTN);
		String input = "{'EMAIL','DTN'}";
		
	List<PnrMethod>	 result =(List<PnrMethod>) uut.convert(input);
	
	result.forEach(el -> System.out.println(el.toString()));
	
	assertTrue(expectedResult.containsAll(result));
	}

	
	@Test
	public void testCaseConvertString() throws CsvDataTypeMismatchException, CsvConstraintViolationException {
		EnumSet<PnrMethod>  expectedResult =EnumSet.of(PnrMethod.EMAIL,PnrMethod.DTN);
		String input = "{'Email','Dtn'}";
		
	List<PnrMethod>	 result =(List<PnrMethod>) uut.convert(input);
	
	result.forEach(el -> System.out.println(el.toString()));
	
	assertTrue(expectedResult.containsAll(result));
	}
}
