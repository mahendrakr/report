package com.sapient.bid_report.service.impl;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opencsv.CSVWriter;
import com.sapient.bid_report.constant.Constant;
import com.sapient.bid_report.model.IncomeDetails;
import com.sapient.bid_report.service.ReportWriter;

public class CSVReportWriter implements ReportWriter<List<IncomeDetails>> {

private static final Logger log= LoggerFactory.getLogger(CSVReportWriter.class);
	@Override
	public void writeReport(String fileName, List<IncomeDetails> incomeDetails) {
	      CSVWriter writer=null;
		try {
			writer = new CSVWriter(new FileWriter(fileName));
			 //Create record
			List<String[]> record = toStringArray(incomeDetails);
		      //Write the record to file
		      writer.writeAll(record);
			
		} catch (IOException e) {
			log.error("Exception occur while writing the data to the file",e);	
		}
		finally {
			try {
				writer.close();
			} catch (IOException e) {
				log.error("Exception occur while closing the resource",e);	
			}	
		}
	        
	     
	       
	}

	private static List<String[]> toStringArray(List<IncomeDetails> incomeDetails) {
		List<String[]> records = new ArrayList<String[]>();

		// adding header record
		records.add(new String[] { Constant.CITY_COUNTRY, Constant.GENDER, Constant.CURRENCY, Constant.AVERAGE_INCOME });

		Iterator<IncomeDetails> it = incomeDetails.iterator();
		while (it.hasNext()) {
			IncomeDetails record = it.next();
			records.add(new String[] { record.getCountry(), record.getGender(), record.getCurrency(), String.valueOf(record.getAmount()) });
		}
		return records;
	}
	
}
