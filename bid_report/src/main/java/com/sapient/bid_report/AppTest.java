package com.sapient.bid_report;

import java.util.List;

import com.sapient.bid_report.constant.Constant;
import com.sapient.bid_report.model.IncomeDetails;
import com.sapient.bid_report.operation.Calculation;
import com.sapient.bid_report.service.ReportReader;
import com.sapient.bid_report.service.ReportWriter;
import com.sapient.bid_report.service.impl.CSVReportReader;
import com.sapient.bid_report.service.impl.CSVReportWriter;

public class AppTest {
public static void main(String[] args) {
	ReportReader reportReader=new CSVReportReader();
    List<IncomeDetails> incomeDetails = reportReader.readReport(Constant.inputFileName);
    ReportWriter<List<IncomeDetails>> reportWriter=new CSVReportWriter();
    Calculation calculation=new Calculation();
    List<IncomeDetails> reportData = calculation.calculateAvgIncome(incomeDetails);
    reportWriter.writeReport(Constant.outputFileName, reportData);
}
}
