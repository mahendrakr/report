package com.sapient.bid_report.constant;

public enum Country {
	IND("IND"), USA("USA"), UK("UK");
	private String country;

	Country(String country) {
		this.country = country;
	}

	public String getCountry() {
		return country;
	}

}
