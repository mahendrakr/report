package com.sapient.bid_report.service;
/**
 * This is contract between ReportWriter and it's implementation classes.
 *
 * @param <T>
 */
public interface ReportWriter<T> {
	/**
	 * Write report data to the file.
	 * @param fileName {@link String}
	 * @param data T type
	 */
void writeReport(String fileName,T data);
}
