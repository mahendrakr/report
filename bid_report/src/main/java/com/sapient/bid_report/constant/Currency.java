package com.sapient.bid_report.constant;

public enum Currency {
INR("INR"),USD("USD"),GBP("GBP");
	private String currency;
	Currency(String currency){
		this.currency=currency;
	}
	public String getCurrency() {
		return currency;
	}
	
	
}
