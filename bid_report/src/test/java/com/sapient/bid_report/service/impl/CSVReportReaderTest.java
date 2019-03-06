package com.sapient.bid_report.service.impl;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import com.sapient.bid_report.constant.Constant;
import com.sapient.bid_report.model.IncomeDetails;
@RunWith(MockitoJUnitRunner.class)
public class CSVReportReaderTest {
@InjectMocks
private CSVReportReader csvReportReader;
	@Test
	public void testReadReport() {
		List<IncomeDetails> result = csvReportReader.readReport(Constant.inputFileName);
		Assert.assertNotNull(result);
		Assert.assertEquals(result.get(0).getCity(), "Chennai");
	}

}
