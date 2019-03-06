package com.sapient.bid_report.service;

import java.util.List;

import com.sapient.bid_report.model.IncomeDetails;

public interface ReportReader {
 List<IncomeDetails> readReport(String fileName);
}
