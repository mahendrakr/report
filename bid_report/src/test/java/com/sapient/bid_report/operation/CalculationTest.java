package com.sapient.bid_report.operation;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import com.sapient.bid_report.model.IncomeDetails;
@RunWith(MockitoJUnitRunner.class)
public class CalculationTest {
@InjectMocks
private Calculation calculation;
	@Test
	public void testCalculateAvgIncome() {
    List<IncomeDetails> result = calculation.calculateAvgIncome(getTestData());
    Assert.assertNotNull(result);
    Assert.assertEquals(result.size() ,3);
    Assert.assertEquals(result.get(0).getCountry(), "IND");
    Assert.assertEquals(result.get(0).getGender(),"M");
    
	}
	
	private List<IncomeDetails> getTestData(){
		List<IncomeDetails> records=new ArrayList<>();
		records.add(new IncomeDetails("Patna", "IND", "F", BigDecimal.valueOf(1000), "INR"));
		records.add(new IncomeDetails("UP", "IND", "M", BigDecimal.valueOf(500), "INR"));
		records.add(new IncomeDetails("Patna", "IND", "M", BigDecimal.valueOf(1000), "INR"));
		records.add(new IncomeDetails("UP", "IND", "F", BigDecimal.valueOf(1000), "INR"));
		records.add(new IncomeDetails("Bostan", "USA", "F", BigDecimal.valueOf(3000), "INR"));
		records.add(new IncomeDetails("Bostan", "USA", "F", BigDecimal.valueOf(3000), "INR"));
		return records;
	}

}
