package com.wfs.landpricing.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.wfs.landpricing.model.enums.TaxType;

public class CsvStringToTaxSummaryTest {

	CSvStringToTaxSummaryType uut = new CSvStringToTaxSummaryType();
	
	@Test
	public void testConvertString() throws CsvDataTypeMismatchException, CsvConstraintViolationException {
		
		TaxType res = (TaxType) uut.convert("summary");
		assertEquals(TaxType.TAX_SUMMARY,res);
		
		System.out.println(" Reslut:"+ uut.convert(null));
	}

}
