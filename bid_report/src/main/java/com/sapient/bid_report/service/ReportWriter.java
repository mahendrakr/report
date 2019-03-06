package com.sapient.bid_report.service;

public interface ReportWriter<T> {
void writeReport(String fileName,T data);
}
