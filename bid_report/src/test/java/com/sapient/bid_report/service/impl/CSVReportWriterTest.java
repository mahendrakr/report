package com.sapient.bid_report.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import com.sapient.bid_report.model.IncomeDetails;
@RunWith(MockitoJUnitRunner.class)
public class CSVReportWriterTest {
@InjectMocks
private CSVReportWriter csvReportWriter;
	@Test
	public void testWriteReport() {
		csvReportWriter.writeReport("test.csv", getTestData());
	}
	private List<IncomeDetails> getTestData(){
		List<IncomeDetails> records=new ArrayList<>();
		records.add(new IncomeDetails("Patna", "IND", "F", BigDecimal.valueOf(1000), "INR"));
		records.add(new IncomeDetails("UP", "IND", "M", BigDecimal.valueOf(500), "INR"));
		records.add(new IncomeDetails("Patna", "IND", "M", BigDecimal.valueOf(1000), "INR"));
		records.add(new IncomeDetails("UP", "IND", "F", BigDecimal.valueOf(1000), "INR"));
		records.add(new IncomeDetails("Bostan", "USA", "F", BigDecimal.valueOf(3000), "USD"));
		records.add(new IncomeDetails("Bostan", "USA", "F", BigDecimal.valueOf(3000), "USD"));
		return records;
	}
}
