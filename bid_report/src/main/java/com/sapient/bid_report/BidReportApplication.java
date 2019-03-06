package com.sapient.bid_report;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.sapient.bid_report.constant.Constant;
import com.sapient.bid_report.model.IncomeDetails;
import com.sapient.bid_report.operation.Calculation;
import com.sapient.bid_report.service.ReportReader;
import com.sapient.bid_report.service.ReportWriter;
import com.sapient.bid_report.service.impl.CSVReportReader;
import com.sapient.bid_report.service.impl.CSVReportWriter;

@SpringBootApplication
public class BidReportApplication {

	public static void main(String[] args) {
		SpringApplication.run(BidReportApplication.class, args);
		ReportReader reportReader=new CSVReportReader();
	    List<IncomeDetails> incomeDetails = reportReader.readReport(Constant.inputFileName);
	    ReportWriter<List<IncomeDetails>> reportWriter=new CSVReportWriter();
	    Calculation calculation=new Calculation();
	    List<IncomeDetails> reportData = calculation.calculateAvgIncome(incomeDetails);
	    reportWriter.writeReport(Constant.outputFileName, reportData);
	}

}
