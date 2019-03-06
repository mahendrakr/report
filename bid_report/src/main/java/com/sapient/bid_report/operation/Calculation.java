package com.sapient.bid_report.operation;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sapient.bid_report.constant.Currency;
import com.sapient.bid_report.model.IncomeDetails;

/**
 * 
 * This class represent business operation(calculation)
 *
 */
public class Calculation {
	private static final Logger log = LoggerFactory.getLogger(Calculation.class);

	/**
	 * Calculate average income and arrange them in group by country and gender sort
	 * them country wise if country is same then sort by average amount.
	 * 
	 * @param inocmeDetails {@link IncomeDetails}
	 * @return
	 */
	public List<IncomeDetails> calculateAvgIncome(List<IncomeDetails> inocmeDetails) {
		List<IncomeDetails> reportData = getReportData(groupIncomeDetailsByCountryAndGender(inocmeDetails),
				mapCountryAndCurrency(inocmeDetails));
		return reportData.stream()
				.sorted(Comparator.comparing(IncomeDetails::getCountry).thenComparing(IncomeDetails::getAmount))
				.collect(Collectors.toList());
	}

	/**
	 * Map country and their currency.
	 * 
	 * @param inocmeDetails {@link IncomeDetails}
	 * @return Map<String, String> map country and currency.
	 */
	private Map<String, String> mapCountryAndCurrency(List<IncomeDetails> inocmeDetails) {
		Map<String, String> mapCountryAndCurrency = new HashMap<>();
		inocmeDetails.stream().forEach(income -> {
			mapCountryAndCurrency.put(income.getCountry(), income.getCurrency());
		});
		return mapCountryAndCurrency;

	}

	/**
	 * Group Income details by country and gender.
	 * 
	 * @param inocmeDetails {@link IncomeDetails}
	 * @return Map<String, Map<String, Double>> map country to gender and gender to
	 *         average income.
	 */
	private Map<String, Map<String, Double>> groupIncomeDetailsByCountryAndGender(List<IncomeDetails> inocmeDetails) {
		Map<String, Map<String, Double>> groupedData = inocmeDetails.stream()
				.collect(Collectors.groupingBy(IncomeDetails::getCountry,
						Collectors.groupingBy(IncomeDetails::getGender, Collectors.averagingDouble(inoceDetials -> {
							return inoceDetials.getAmount().doubleValue();
						}))));
		return groupedData;
	}

	/**
	 * Simplify the report data. i.e convert group data to list of
	 * {@link IncomeDetails}
	 * 
	 * @param groupedData           Map<String, Map<String, Double>>
	 * @param mapCountryAndCurrency Map<String, String>
	 * @return List<IncomeDetails> list of {@link IncomeDetails}
	 */
	private List<IncomeDetails> getReportData(Map<String, Map<String, Double>> groupedData,
			Map<String, String> mapCountryAndCurrency) {
		log.info("Getreportdata is started");
		List<IncomeDetails> records = new ArrayList<>();
		groupedData.forEach((country, values) -> {
			values.forEach((gender, avgAmount) -> {
				IncomeDetails record = new IncomeDetails();
				record.setCountry(country);
				record.setGender(gender);
				record.setCurrency(mapCountryAndCurrency.get(country));
				record.setAmount(getUSDAmount(avgAmount, mapCountryAndCurrency.get(country)));
				records.add(record);
			});
		});
		log.info("Getreportdata  is finished");
		return records;
	}

	/**
	 * Convert currency amount to USD dollar.
	 * 
	 * @param amount   double
	 * @param currency String
	 * @return BigDecimal amount up to 2 decimal digit.
	 */
	private BigDecimal getUSDAmount(double amount, String currency) {
		BigDecimal usdAmount = BigDecimal.valueOf(amount);
		if (Currency.INR.getCurrency().equals(currency)) {
			usdAmount = usdAmount.multiply(BigDecimal.valueOf(0.014));

		} else if (Currency.GBP.getCurrency().equals(currency)) {
			usdAmount = usdAmount.multiply(BigDecimal.valueOf(1.31));
		} else {
		}
		return usdAmount.setScale(2, BigDecimal.ROUND_UP);
	}
}
