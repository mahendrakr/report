package com.sapient.bid_report.service;

import java.util.List;

import com.sapient.bid_report.model.IncomeDetails;

/**
 * This act as contract between Report reader and it's implementation classes.
 *
 */
public interface ReportReader {
	/**
	 * Read report take file name and read it.
	 * @param fileName
	 * @return List<IncomeDetails> Income details
	 */
	List<IncomeDetails> readReport(String fileName);
}
