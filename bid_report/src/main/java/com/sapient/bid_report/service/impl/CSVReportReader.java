package com.sapient.bid_report.service.impl;

import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opencsv.CSVReader;
import com.sapient.bid_report.constant.Constant;
import com.sapient.bid_report.model.IncomeDetails;
import com.sapient.bid_report.service.ReportReader;

public class CSVReportReader implements ReportReader {
	private static final Logger log= LoggerFactory.getLogger(CSVReportWriter.class);
	

	@Override
	public List<IncomeDetails> readReport(String fileName) {
		CSVReader reader = null;
		List<IncomeDetails> records = new ArrayList<>();
		try {
			reader = new CSVReader(new FileReader(fileName), Constant.COMMA, Constant.QUOTECHAR, 1);
			String[] nextLine;
			while ((nextLine = reader.readNext()) != null) {
				IncomeDetails record = new IncomeDetails();
				if (nextLine != null) {
					record.setCity(nextLine[0]);
					if (nextLine[1].length()==0) {
						record.setCountry(nextLine[0]);
					} else {
						record.setCountry(nextLine[1]);
					}
					record.setGender(nextLine[2]);
					record.setCurrency(nextLine[3]);
					record.setAmount(convertStringToBigDecimal(nextLine[4]));
					records.add(record);

				}
			}
		} catch (IOException e) {
			log.error("Exception occur while reading the file: {}",e);
		} finally {
			if (reader != null)
				try {
					reader.close();
				} catch (IOException e) {
					log.error("Exception occuring file closing the resource: {}",e);
				}
		}
		return records;
	}

	private BigDecimal convertStringToBigDecimal(String amount) {
		double incomeAmount = Double.valueOf(amount);
		BigDecimal amt = BigDecimal.valueOf(incomeAmount);
		return amt.setScale(2, BigDecimal.ROUND_UP);
	}

//	public static void main(String[] args) {
//		List<IncomeDetails> readReport = new CSVReportReader().readReport(Constant.inputFileName);
//		readReport.stream().forEach(record -> System.out
//				.println(record.getCity() + " " + record.getCity() + " " + record.getGender() + " "+record.getCurrency()+" "+record.getAmount()));
//
//	ReportWriter<List<IncomeDetails>> writer= new CSVReportWriter();
//	writer.writeReport(Constant.outputFileName, readReport);
//	}

}
